/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui;

import info.tgeorge.asf.ui.task.SceneLoaderTask;
import java.util.HashMap;
import javafx.concurrent.WorkerStateEvent;
import javafx.scene.layout.StackPane;

/**
 *
 * @author george
 */
public class SceneOrchestrator {

    private final OrchesterStage stage;

    private final HashMap<String, SceneController> orchestrations;

    private MyScene currentOrchestredScene;

    public SceneOrchestrator(StackPane scenePane) {
        stage = new OrchesterStage(scenePane);
        orchestrations = new HashMap<>();
    }

    public ScenePromise orchestrate(MyScene scene, Object... args) {
        final ScenePromise promise = new ScenePromise();
        currentOrchestredScene = scene;

        if (orchestrations.containsKey(scene.getSceneName())) {
            SceneController sceneController = orchestrations.get(scene.getSceneName());
            sceneController.initialize(args);

            stage.rePlayScene(sceneController.getRootPane(), scene.getSceneType());

            promise.run((resolve, reject) -> {
                sceneController.onEnd(resolve::call);
                sceneController.onError(reject::call);
            });
        } else {
            SceneLoaderTask sceneLoadTask = new SceneLoaderTask(scene);
            sceneLoadTask.setOnSucceeded((event) -> {
                SceneController sceneController = sceneLoadTask.getValue();
                sceneController.initialize(args);
                orchestrations.put(scene.getSceneName(), sceneController);

                stage.playScene(sceneController.getRootPane(), scene.getSceneType());

                promise.run((resolve, reject) -> {
                    sceneController.onEnd(resolve::call);
                    sceneController.onError(reject::call);
                });
            });
            sceneLoadTask.setOnFailed((WorkerStateEvent event) -> {
                throw new SceneLoaderException("Cannot load scene.", sceneLoadTask.getException());
            });

            Thread th = new Thread(sceneLoadTask);
            th.start();
        }

        return promise;
    }

    public MyScene getCurrentSceneName() {
        return currentOrchestredScene;
    }

}
