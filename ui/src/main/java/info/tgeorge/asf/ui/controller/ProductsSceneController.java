/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import info.tgeorge.asf.api.EntityService;
import info.tgeorge.asf.api.EntityServiceFactory;
import info.tgeorge.asf.common.models.ProductType;
import info.tgeorge.asf.ui.BaseSceneController;
import info.tgeorge.asf.ui.CallbackVoid;
import info.tgeorge.asf.ui.Constants;
import info.tgeorge.asf.ui.MyScene;
import info.tgeorge.asf.ui.SceneManager;
import info.tgeorge.asf.ui.SceneOrchestrator;
import info.tgeorge.asf.ui.ScenePromise;
import info.tgeorge.asf.ui.SceneType;
import info.tgeorge.asf.ui.filters.BaseUIFilter;
import info.tgeorge.asf.ui.filters.UIFiltersState;
import info.tgeorge.asf.ui.filters.UIBooleanFilter;
import info.tgeorge.asf.ui.filters.UIColorFilter;
import info.tgeorge.asf.ui.filters.UIDateRangeFilter;
import info.tgeorge.asf.ui.filters.UIStringFilter;
import info.tgeorge.asf.ui.filters.UIPriceFilter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import info.tgeorge.asf.ui.filters.UIFiltersWatcher;
import info.tgeorge.asf.ui.product.ProductFetcher;
import info.tgeorge.asf.ui.task.FetchProductsCountTask;
import info.tgeorge.asf.ui.view.PaginatorView;
import info.tgeorge.asf.ui.view.ProductTableModel;
import info.tgeorge.asf.ui.view.ProductTableView;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.StackPane;

/**
 *
 * @author george
 */
public class ProductsSceneController extends BaseSceneController {

    @FXML
    private JFXTextField jtfName;

    @FXML
    private StackPane spMain;

    @FXML
    private JFXButton jfxBtnEditTableItem;

    @FXML
    private JFXButton jfxBtnCreateTableItem;

    @FXML
    private JFXButton jfxBtnDeleteTableItem;

    @FXML
    private JFXButton jfxBtnDeleteSelectedTableItems;

    @FXML
    private JFXComboBox<Label> cbPriceOperator;

    @FXML
    private JFXTextField jtfPrice;

    @FXML
    private JFXColorPicker jfxColorPicker;

    @FXML
    private JFXDatePicker jdpExpirationDateFrom;

    @FXML
    private JFXDatePicker jdpExpirationDateTo;

    @FXML
    private JFXCheckBox chkbInStock;

    @FXML
    private JFXComboBox<Label> cbTipProdus;

    @FXML
    private JFXComboBox<Label> cbItemsPerPage;

    @FXML
    private JFXButton jfxBtnSearch;

    @FXML
    private JFXCheckBox chkbAutoFilter;

    @FXML
    private AnchorPane apMain;

    @FXML
    private AnchorPane apPaginatorWrapper;

    @FXML
    private AnchorPane apTableWrapper;

    private UIStringFilter uiNameFilter;
    private UIPriceFilter uiPriceFilter;
    private UIColorFilter uiColorFilter;
    private UIDateRangeFilter uiDateRangeFilter;
    private UIBooleanFilter uiInStocFilter;

    private List<BaseUIFilter> uiFilters;

    private UIFiltersWatcher uiFiltersWatcher;
    private UIFiltersState uiFiltersState;
    private PaginatorView paginatorView;
    private JFXTreeTableView<ProductTableModel> productTableView;

    private final EntityServiceFactory entityServiceFactory;
    private final EntityService entityService;
    private final ProductFetcher productFetcher;

    public ProductsSceneController() {
        entityServiceFactory = new EntityServiceFactory();
        entityService = entityServiceFactory.createEntityService();
        productFetcher = new ProductFetcher(entityService);

        uiFilters = new ArrayList<>();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupSceneManagement();
        initFilterActionControls();
        initComboBoxControls();
        initAndWatchFilters();
        initTableActionControls();
        initPaginator(() -> {
            displayProducts();
        });
    }

    private void setupSceneManagement() {
        sceneManager = new SceneManager();
        sceneOrchestrator = new SceneOrchestrator(spMain);

        sceneManager.registerScene(new MyScene(Constants.SCENE_PRODUCT, SceneType.CHILD_SCENE, Constants.SCENE_PATH_PRODUCT));
    }

    private void displayProducts() {
        productFetcher.setStartPosition(paginatorView.getStartPosition());
        productFetcher.setMaxResults(paginatorView.getMaxResults());

        initPaginator(() -> {
            productFetcher.fetch(uiFiltersState, (products) -> {
                productTableView = ProductTableView.createTableView(products);

                AnchorPane.setBottomAnchor(productTableView, 0.0);
                AnchorPane.setLeftAnchor(productTableView, 0.0);
                AnchorPane.setTopAnchor(productTableView, 0.0);
                AnchorPane.setRightAnchor(productTableView, 0.0);

                apTableWrapper.getChildren().setAll(productTableView);
            });
        });
    }

    private void initPaginator(CallbackVoid callback) {
        FetchProductsCountTask fetchProductsCountTask = new FetchProductsCountTask(entityService);
        fetchProductsCountTask.setFilters(uiFiltersState.getFilters());
        fetchProductsCountTask.setOnSucceeded((ev) -> {
            int productsCount = fetchProductsCountTask.getValue();

            paginatorView = new PaginatorView();
            paginatorView.load(productsCount, (bpPaginator) -> {
                apPaginatorWrapper.getChildren().setAll(bpPaginator);
                callback.call();
            });
            paginatorView.setPaginatorViewChangesListener((changes) -> {
                displayProducts();
            });
        });

        Thread fetchProductsCountThread = new Thread(fetchProductsCountTask);
        fetchProductsCountThread.start();
    }

    private void initAndWatchFilters() {
        uiFiltersState = new UIFiltersState();
        uiFiltersWatcher = new UIFiltersWatcher();

        uiNameFilter = new UIStringFilter(jtfName);
        uiPriceFilter = new UIPriceFilter(cbPriceOperator, jtfPrice);
        uiColorFilter = new UIColorFilter(jfxColorPicker);
        uiDateRangeFilter = new UIDateRangeFilter(jdpExpirationDateFrom, jdpExpirationDateTo);
        uiInStocFilter = new UIBooleanFilter(chkbInStock);

        uiFilters.add(uiNameFilter);
        uiFilters.add(uiPriceFilter);
        uiFilters.add(uiColorFilter);
        uiFilters.add(uiDateRangeFilter);
        uiFilters.add(uiInStocFilter);

        uiFiltersState.registerFilters(uiFilters);

        uiFiltersWatcher.watchAll(uiFilters);

        uiFiltersWatcher.setOnFilterValueChangeListener((BaseUIFilter item) -> {
            uiFiltersState.updateFilter(item);
            displayProducts();
        });

    }

    private void initComboBoxControls() {
        cbTipProdus.setOnMouseClicked((event) -> {
            if (cbTipProdus.getItems().isEmpty()) {
                List<ProductType> productTypes = entityService.getProductTypes();
                productTypes.forEach((productType) -> {
                    cbTipProdus.getItems().add(new Label(productType.getName()));
                });
            }
        });

        //Remove padding from label or fix layout constraints
        cbPriceOperator.getItems().add(new Label(Constants.PRICE_EQUAL_TO));
        cbPriceOperator.getItems().add(new Label(Constants.PRICE_LOWER_THAN));
        cbPriceOperator.getItems().add(new Label(Constants.PRICE_GREATER_THAN));

        cbPriceOperator.getSelectionModel().selectFirst();
    }

    private void initFilterActionControls() {
        chkbAutoFilter.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue == true) {
                uiFiltersWatcher.on();
                jfxBtnSearch.disableProperty().set(true);
            } else {
                uiFiltersWatcher.off();
                jfxBtnSearch.disableProperty().set(false);
            }
        });

        jfxBtnSearch.setOnAction((ev) -> {
            uiFiltersState.registerFilters(uiFilters);

            displayProducts();
        });
    }

    private void initTableActionControls() {
        jfxBtnEditTableItem.setOnAction((ev) -> {
            TreeItem<ProductTableModel> product = productTableView.getSelectionModel().getSelectedItem();
            ScenePromise promise
                    = sceneManager.requestScene(Constants.SCENE_PRODUCT, sceneOrchestrator, product.getValue());
        });

        jfxBtnDeleteTableItem.setOnAction((ev) -> {

        });

        jfxBtnDeleteSelectedTableItems.setOnAction((ev) -> {

        });

        jfxBtnCreateTableItem.setOnAction((ev) -> {

        });
    }
}
