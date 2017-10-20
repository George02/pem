/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui.task;

import info.tgeorge.asf.ui.MyScene;
import info.tgeorge.asf.ui.SceneController;
import java.net.URL;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author george
 */
public class SceneLoaderTask extends Task<SceneController> {

    private final MyScene scene;

    public SceneLoaderTask(MyScene scene) {
        this.scene = scene;
    }

    @Override
    protected SceneController call() throws Exception {
        URL url = getClass().getResource(scene.getScenePath());
        FXMLLoader loader = new FXMLLoader(url);
        AnchorPane root = (AnchorPane) loader.load();
        SceneController controller = (SceneController) loader.getController();
        controller.setRootPane(root);
        return controller;
    }

}
