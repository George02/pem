/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui.controller;

import info.tgeorge.asf.ui.BaseSceneController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author george
 */
public class DashboardSceneController extends BaseSceneController {

    @FXML
    private AnchorPane apMain;

    @Override
    public AnchorPane getRootPane() {
        return apMain;
    }

}
