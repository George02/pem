/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui;

import javafx.scene.layout.AnchorPane;

/**
 *
 * @author george
 */
public interface SceneController {

    SceneManager getSceneManager();

    SceneOrchestrator getSceneOrchestrator();

    AnchorPane getRootPane();

    void onEnd(CallbackVoid callback);

    void onError(CallbackVoid callback);

    void setRootPane(AnchorPane rootPane);
    
    void initialize(Object[] args);
}
