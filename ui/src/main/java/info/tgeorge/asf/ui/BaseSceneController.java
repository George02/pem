/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author george
 */
public abstract class BaseSceneController implements Initializable, SceneController {

    protected AnchorPane rootPane;
    protected SceneManager sceneManager;
    protected SceneOrchestrator sceneOrchestrator;

    @Override
    public SceneManager getSceneManager() {
        return sceneManager;
    }

    @Override
    public SceneOrchestrator getSceneOrchestrator() {
        return sceneOrchestrator;
    }

    @Override
    public AnchorPane getRootPane() {
        return rootPane;
    }

    @Override
    public void setRootPane(AnchorPane rootPane) {
        this.rootPane = rootPane;
    }

    @Override
    public void onEnd(CallbackVoid callback) {

    }

    @Override
    public void onError(CallbackVoid callback) {

    }

    @Override
    public void initialize(Object[] args) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
