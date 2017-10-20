/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui.controller.parts;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import info.tgeorge.asf.ui.model.Paginator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 *
 * @author george
 */
public class PaginatorController implements Initializable {

    private BorderPane bpPaginator;

    @FXML
    private HBox hBoxBtnPageNumber;

    @FXML
    private JFXComboBox<Label> cbItemsPerPage;

    private final SimpleIntegerProperty maxResultsProperty;
    private final SimpleIntegerProperty startPositionProperty;

    private final Paginator paginator;

    public PaginatorController() {
        paginator = new Paginator();
        maxResultsProperty = new SimpleIntegerProperty(5);
        startPositionProperty = new SimpleIntegerProperty(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbItemsPerPage.getItems().add(new Label("5"));
        cbItemsPerPage.getItems().add(new Label("20"));
        cbItemsPerPage.getItems().add(new Label("50"));
        cbItemsPerPage.getItems().add(new Label("99"));

        cbItemsPerPage.valueProperty().addListener((ObservableValue<? extends Label> observable, Label oldLabel, Label newLabel) -> {
            int itemsPerPage = Integer.valueOf(newLabel.getText());
            paginator.setItemsPerPage(itemsPerPage);
            createPagination();
            maxResultsProperty.set(itemsPerPage);
        });

        cbItemsPerPage.getSelectionModel().selectFirst();
    }

    public void setItemsCount(int itemsCount) {
        paginator.setItemsCount(itemsCount);
        createPagination();
    }

    private void createPagination() {
        hBoxBtnPageNumber.getChildren().clear();

        int nrOfPages = paginator.getNrOfPages();
        if (nrOfPages > 5) {
            for (int i = 1; i <= 3; i++) {
                hBoxBtnPageNumber.getChildren().add(createPageNumberButton(String.valueOf(i), i));
            }

            hBoxBtnPageNumber.getChildren().add(createPageNumberButton("...", -1));
            hBoxBtnPageNumber.getChildren().add(createPageNumberButton(String.valueOf(nrOfPages), nrOfPages));
        } else {
            for (int i = 1; i <= nrOfPages; i++) {
                hBoxBtnPageNumber.getChildren().add(createPageNumberButton(String.valueOf(i), i));
            }
        }
    }

    private JFXButton createPageNumberButton(String text, int pageIndex) {
        JFXButton btnPage = new JFXButton(text);
        btnPage.getStyleClass().add("jfx-btn-page-number");
        btnPage.setMaxHeight(Double.MAX_VALUE);
        btnPage.setPrefWidth(40);
        btnPage.setMinWidth(40);
        HBox.setHgrow(btnPage, Priority.ALWAYS);

        if (pageIndex != -1) {
            btnPage.setOnAction(e -> onPageClick(pageIndex));
        }

        return btnPage;
    }

    private void onPageClick(int pageIndex) {
        int startPosition = (pageIndex - 1) * paginator.getMaxResults();
        if (startPosition == 0) {
            startPosition = 1;
        }

        startPositionProperty.set(startPosition);
    }

    public void setRootPane(BorderPane bpPaginator) {
        this.bpPaginator = bpPaginator;
    }

    public BorderPane getRootPane() {
        return this.bpPaginator;
    }

    public SimpleIntegerProperty getMaxResultsProperty() {
        return maxResultsProperty;
    }

    public SimpleIntegerProperty getStartPositionProperty() {
        return startPositionProperty;
    }

}
