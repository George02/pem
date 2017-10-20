/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui.task;

import info.tgeorge.asf.ui.controller.parts.PaginatorController;
import java.net.URL;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author george
 */
public class PaginatorLoaderTask extends Task<PaginatorController> {
    
    public PaginatorLoaderTask() {
    }

    @Override
    protected PaginatorController call() throws Exception {
        URL paginatorLayoutURL = getClass().getResource("/fxml/parts/PaginatorLayout.fxml");
        FXMLLoader loader = new FXMLLoader(paginatorLayoutURL);

        BorderPane bpPaginator = (BorderPane) loader.load();
        AnchorPane.setTopAnchor(bpPaginator, 0.0);
        AnchorPane.setRightAnchor(bpPaginator, 0.0);
        AnchorPane.setLeftAnchor(bpPaginator, 0.0);
        AnchorPane.setBottomAnchor(bpPaginator, 0.0);

        PaginatorController paginatorController = (PaginatorController) loader.getController();
        paginatorController.setRootPane(bpPaginator);

        return paginatorController;
    }

}
