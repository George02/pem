/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui.controller;

import info.tgeorge.asf.ui.BaseSceneController;
import info.tgeorge.asf.ui.view.ProductTableModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author george
 */
public class ProductSceneController extends BaseSceneController {

    @FXML
    private Label lblMain;

    @Override
    public void initialize(Object[] args) {
        ProductTableModel ptm = (ProductTableModel) args[0];
        lblMain.setText(ptm.getName().get());
    }

}
