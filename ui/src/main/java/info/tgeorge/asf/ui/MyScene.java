/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui;

/**
 *
 * @author george
 */
public class MyScene {

    private String sceneName;
    private SceneType sceneType;
    private String scenePath;

    public MyScene(String sceneName, SceneType sceneType, String scenePath) {
        this.sceneName = sceneName;
        this.sceneType = sceneType;
        this.scenePath = scenePath;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public SceneType getSceneType() {
        return sceneType;
    }

    public void setSceneType(SceneType sceneType) {
        this.sceneType = sceneType;
    }

    public String getScenePath() {
        return scenePath;
    }

    public void setScenePath(String scenePath) {
        this.scenePath = scenePath;
    }

}
