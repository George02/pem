/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui.view;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import info.tgeorge.asf.common.models.Product;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TextFieldTreeTableCell;

/**
 *
 * @author george
 */
public class ProductTableView {

    private static JFXTreeTableView<ProductTableModel> productTableView;
    private static List<JFXTreeTableColumn<ProductTableModel, String>> productTableViewColumns;

    public static JFXTreeTableView<ProductTableModel> createTableView(List<Product> products) {
        if (productTableView == null) {
            productTableView = new JFXTreeTableView<>();
            productTableView.setShowRoot(false);
            productTableView.setEditable(false);
            //productTableView.setContextMenu(createContextMenu());

            if (productTableViewColumns == null) {
                productTableViewColumns = new ArrayList<>();
                createTableViewColumn();
            }

            productTableView.getColumns().setAll(productTableViewColumns);
        }

        TreeItem<ProductTableModel> root = new RecursiveTreeItem<>(createTableData(products), RecursiveTreeObject::getChildren);
        productTableView.setRoot(root);

        return productTableView;
    }

//    private static ContextMenu createContextMenu() {
//        MenuItem menuItemEdit = new MenuItem("Editeaza");
//        menuItemEdit.setOnAction((ActionEvent event) -> {
//            TreeItem<ProductTableModel> item = productTableView.getSelectionModel().getSelectedItem();
//        });
//
//        MenuItem menuItemDelete = new MenuItem("Sterge");
//        menuItemDelete.setOnAction((ActionEvent event) -> {
//            TreeItem<ProductTableModel> item = productTableView.getSelectionModel().getSelectedItem();        
//        });
//
//        ContextMenu cm = new ContextMenu(menuItemEdit, menuItemDelete);
//        return cm;
//    }

    private static ObservableList<ProductTableModel> createTableData(List<Product> products) {
        ObservableList<ProductTableModel> productsTableItems = FXCollections.observableArrayList();
        products.forEach((p) -> {
            ProductTableModel productTableModel = new ProductTableModel();

            productTableModel.setId(new SimpleIntegerProperty(p.getProductId()));
            productTableModel.setName(new SimpleStringProperty(p.getName()));
            productTableModel.setPrice(new SimpleDoubleProperty(p.getPrice()));
            productTableModel.setColor(new SimpleStringProperty(p.getColor()));
            String expirationDate = new SimpleDateFormat("MM-dd-yyyy").format(p.getExpirationDate());
            productTableModel.setExpirationDate(new SimpleStringProperty(expirationDate));
            productTableModel.setInStoc(new SimpleBooleanProperty(p.isInStoc()));
            productTableModel.setProductTypeId(new SimpleIntegerProperty(p.getProductTypeId()));

            productsTableItems.add(productTableModel);
        });

        return productsTableItems;
    }

    private static void createTableViewColumn() {
        JFXTreeTableColumn<ProductTableModel, String> idColumn = new JFXTreeTableColumn<>("#");
        idColumn.setPrefWidth(60);
        idColumn.setCellFactory((TreeTableColumn<ProductTableModel, String> param) -> new TextFieldTreeTableCell<ProductTableModel, String>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(null);
                setText(empty ? null : getIndex() + 1 + "");
            }
        });

        JFXTreeTableColumn<ProductTableModel, String> nameColumn = new JFXTreeTableColumn<>("Nume");
        nameColumn.setPrefWidth(140);
        nameColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProductTableModel, String> param) -> {
            if (nameColumn.validateValue(param)) {
                return param.getValue().getValue().getName();
            } else {
                return nameColumn.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<ProductTableModel, String> priceColumn = new JFXTreeTableColumn<>("Pret");
        priceColumn.setPrefWidth(80);
        priceColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProductTableModel, String> param) -> {
            return param.getValue().getValue().getPrice().asString("%.3f%n");
        });

        JFXTreeTableColumn<ProductTableModel, String> inStocColumn = new JFXTreeTableColumn<>("Pe stoc");
        inStocColumn.setPrefWidth(80);
        inStocColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProductTableModel, String> param) -> {
            boolean inStoc = param.getValue().getValue().getInStoc().get();
            if (inStoc) {
                return new SimpleStringProperty("Da");
            } else {
                return new SimpleStringProperty("Nu");
            }
        });

        JFXTreeTableColumn<ProductTableModel, String> colorColumn = new JFXTreeTableColumn<>("Culoare");
        colorColumn.setPrefWidth(100);
        colorColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProductTableModel, String> param) -> {
            return param.getValue().getValue().getColor();
        });

        JFXTreeTableColumn<ProductTableModel, String> expirationDateColumn = new JFXTreeTableColumn<>("Data expirarii");
        expirationDateColumn.setPrefWidth(200);
        expirationDateColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProductTableModel, String> param) -> {
            return param.getValue().getValue().getExpirationDate();
        });

        JFXTreeTableColumn<ProductTableModel, String> productTypeColumn = new JFXTreeTableColumn<>("Tip produs");
        productTypeColumn.setPrefWidth(160);
        productTypeColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProductTableModel, String> param) -> {
            return param.getValue().getValue().getProductTypeId().asString();
        });

        productTableViewColumns.add(idColumn);
        productTableViewColumns.add(nameColumn);
        productTableViewColumns.add(priceColumn);
        productTableViewColumns.add(inStocColumn);
        productTableViewColumns.add(colorColumn);
        productTableViewColumns.add(expirationDateColumn);
        productTableViewColumns.add(productTypeColumn);
    }
}
