/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author george
 */
public class OrchesterStage {

    private final static int TRANSITION_DURATION = 600;

    private final StackPane rootPane;
    private AnchorPane previousScenePane;
    private AnchorPane currentScenePane;

    public OrchesterStage(StackPane stackPane) {
        this.rootPane = stackPane;
    }

    void playScene(AnchorPane scenePane, SceneType sceneType) {
        scenePane.setManaged(true);
        if (!rootPane.getChildren().isEmpty()) {
            addScenePaneToRootPane(scenePane);

            ObservableList<Node> rootPaneChildrens = rootPane.getChildrenUnmodifiable();
            AnchorPane currentPane = (AnchorPane) rootPaneChildrens.get(rootPaneChildrens.size() - 2);

            TranslateTransition translateCurrent = new TranslateTransition(Duration.millis(TRANSITION_DURATION), currentPane);
            if (sceneType == SceneType.PARENT_SCENE) {
                translateCurrent.setFromY(0);
                translateCurrent.setToY(-rootPane.getHeight());
            } else {
                translateCurrent.setFromX(0);
                translateCurrent.setToX(-rootPane.getWidth());
            }

            TranslateTransition translateNext = new TranslateTransition(Duration.millis(TRANSITION_DURATION), scenePane);
            if (sceneType == SceneType.PARENT_SCENE) {
                translateNext.setFromY(rootPane.getHeight());
                translateNext.setToY(0);
            } else {
                translateNext.setFromX(rootPane.getWidth());
                translateNext.setToX(0);
            }

            ParallelTransition parallelTransition = new ParallelTransition();
            parallelTransition.getChildren().addAll(translateCurrent, translateNext);
            parallelTransition.setOnFinished((ActionEvent event) -> {
                previousScenePane = currentPane;
                currentScenePane = scenePane;
                watchHeightAndtranslateY();
            });

            parallelTransition.play();
        } else {
            addScenePaneToRootPane(scenePane);

            previousScenePane = null;
            currentScenePane = scenePane;
            watchHeightAndtranslateY();
        }
    }

    private void addScenePaneToRootPane(AnchorPane scenePane) {
        scenePane.setPrefWidth(rootPane.getWidth());
        scenePane.setPrefHeight(rootPane.getHeight());

        rootPane.heightProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            scenePane.setPrefHeight(newValue.doubleValue());
        });

        rootPane.widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            scenePane.setPrefWidth(newValue.doubleValue());
        });

        rootPane.getChildren().add(scenePane);
    }

    private void watchHeightAndtranslateY() {
        rootPane.heightProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if (previousScenePane != null) {
                previousScenePane.setTranslateY(-newValue.doubleValue());
            }

            currentScenePane.setTranslateY(0);
        });
    }

    void rePlayScene(AnchorPane scenePane, SceneType sceneType) {
        TranslateTransition translateCurrent = new TranslateTransition(Duration.millis(TRANSITION_DURATION), currentScenePane);
        if (sceneType == SceneType.PARENT_SCENE) {
            translateCurrent.setFromY(0);
            translateCurrent.setToY(-scenePane.getHeight());
        } else {
            translateCurrent.setFromX(0);
            translateCurrent.setToX(-scenePane.getWidth());
        }

        TranslateTransition translateNext = new TranslateTransition(Duration.millis(TRANSITION_DURATION), scenePane);
        if (sceneType == SceneType.PARENT_SCENE) {
            translateNext.setFromY(scenePane.getHeight());
            translateNext.setToY(0);
        } else {
            translateNext.setFromX(scenePane.getWidth());
            translateNext.setToX(0);
        }

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(translateCurrent, translateNext);
        parallelTransition.setOnFinished((ActionEvent event) -> {
            previousScenePane = currentScenePane;
            currentScenePane = scenePane;
            watchHeightAndtranslateY();
        });

        parallelTransition.play();
    }
}
