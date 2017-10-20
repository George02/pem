/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import info.tgeorge.asf.ui.BaseSceneController;
import info.tgeorge.asf.ui.SceneManager;
import info.tgeorge.asf.ui.SceneOrchestrator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import info.tgeorge.asf.ui.CallbackVoid;

/**
 * FXML Controller class
 *
 * @author george
 */
public class LoginSceneController extends BaseSceneController {

    @FXML
    private AnchorPane apMain;

    @FXML
    private JFXTextField jtfUsername;

    @FXML
    private JFXTextField jtfPassword;

    @FXML
    private JFXButton jbtnLogin;

    @FXML
    private JFXButton jbtnRo;

    @FXML
    private JFXButton jbtnEn;

    @FXML
    private Label lblVersion;

    private CallbackVoid endCallback;
    private CallbackVoid errorCallback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jbtnLogin.setOnAction((ActionEvent event) -> {
            endCallback.call();
        });
    }

    @Override
    public AnchorPane getRootPane() {
        return apMain;
    }

    @Override
    public void onEnd(CallbackVoid callback) {
        this.endCallback = callback;
    }

    @Override
    public void onError(CallbackVoid callback) {
        this.errorCallback = callback;
    }

}
