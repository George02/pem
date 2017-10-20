/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui.controller.parts;

import com.jfoenix.controls.JFXButton;
import info.tgeorge.asf.ui.MenuItemType;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author george
 */
public class MenuController implements Initializable {

    @FXML
    private JFXButton jBtnDashboard;

    @FXML
    private JFXButton jBtnProducts;

    @FXML
    private JFXButton jBtnProductTypes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public JFXButton getMenuItem(MenuItemType menuItemType) {
        switch (menuItemType) {
            case DASHBOARD:
                return jBtnDashboard;
            case PRODUCTS:
                return jBtnProducts;
            case PRODUCT_TYPES:
                return jBtnProductTypes;
            default:
                return null;
        }
    }
}
