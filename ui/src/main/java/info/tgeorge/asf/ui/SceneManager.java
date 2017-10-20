/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui;

import java.util.HashMap;

/**
 *
 * @author george
 */
public class SceneManager {

    private final HashMap<String, MyScene> scenes;

    public SceneManager() {
        scenes = new HashMap<>();
    }

    public void registerScene(MyScene scene) {
        scenes.put(scene.getSceneName(), scene);
    }

    public ScenePromise requestScene(String sceneName, SceneOrchestrator sceneOrchestrator, Object... args) {
        if (!scenes.containsKey(sceneName)) {
            throw new IllegalArgumentException("The scene " + sceneName + " is not registered");
        }

        return sceneOrchestrator.orchestrate(scenes.get(sceneName), args);
    }

}
