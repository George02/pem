/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui.controller;

import com.jfoenix.controls.JFXButton;
import info.tgeorge.asf.ui.BaseSceneController;
import info.tgeorge.asf.ui.Constants;
import info.tgeorge.asf.ui.MenuItemType;
import info.tgeorge.asf.ui.SceneManager;
import info.tgeorge.asf.ui.SceneOrchestrator;
import info.tgeorge.asf.ui.ScenePromise;
import info.tgeorge.asf.ui.controller.parts.MenuController;
import java.io.IOException;
import java.net.MalformedURLException;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import info.tgeorge.asf.ui.CallbackVoid;
import info.tgeorge.asf.ui.MyScene;
import info.tgeorge.asf.ui.SceneType;

/**
 * FXML Controller class
 *
 * @author george
 */
public class AppSceneController extends BaseSceneController {

    @FXML
    private AnchorPane apMain;

    @FXML
    private StackPane spMenu;

    @FXML
    private StackPane spMain;

    private CallbackVoid sceneEndCallback;
    private CallbackVoid sceneErrorCallback;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setClip();

        sceneManager = new SceneManager();
        sceneOrchestrator = new SceneOrchestrator(spMain);

        sceneManager.registerScene(new MyScene(Constants.SCENE_LOGIN, SceneType.PARENT_SCENE, Constants.SCENE_PATH_LOGIN));
        sceneManager.registerScene(new MyScene(Constants.SCENE_DASHBOARD, SceneType.PARENT_SCENE, Constants.SCENE_PATH_DASHBOARD));
        sceneManager.registerScene(new MyScene(Constants.SCENE_PRODUCTS, SceneType.PARENT_SCENE, Constants.SCENE_PATH_PRODUCTS));
        sceneManager.registerScene(new MyScene(Constants.SCENE_PRODUCT_TYPES, SceneType.PARENT_SCENE, Constants.SCENE_PATH_PRODUCT_TYPES));

        ScenePromise promise = sceneManager.requestScene(Constants.SCENE_LOGIN, sceneOrchestrator);
        promise.then(() -> {
            MenuController menuController = loadAndDisplayMenu();

            sceneManager.requestScene(Constants.SCENE_DASHBOARD, sceneOrchestrator);

            setMenuActions(menuController);
        });
    }

    private void setClip() {
        Rectangle menuClipRect = new Rectangle(spMenu.getWidth(), spMenu.getHeight());

        menuClipRect.heightProperty().bind(spMenu.heightProperty());
        menuClipRect.widthProperty().bind(spMenu.widthProperty());

        spMenu.setClip(menuClipRect);

        Rectangle mainClipRect = new Rectangle(spMain.getWidth(), spMain.getHeight());

        mainClipRect.heightProperty().bind(spMain.heightProperty());
        mainClipRect.widthProperty().bind(spMain.widthProperty());

        spMain.setClip(mainClipRect);
    }

    private MenuController loadAndDisplayMenu() {
        try {
            URL menuLayoutLocation = getClass().getClassLoader().getResource(Constants.PARTIAL_LAYOUT_MENU);
            FXMLLoader loader = new FXMLLoader(menuLayoutLocation);

            loader.load();

            HBox hbMenu = (HBox) loader.getRoot();

            hbMenu.setTranslateX(-spMenu.getWidth());
            spMenu.getChildren().add(hbMenu);

            TranslateTransition menuTransition = new TranslateTransition(Duration.millis(600), hbMenu);
            menuTransition.setFromX(-spMenu.getWidth());
            menuTransition.setToX(0);
            menuTransition.play();

            return loader.getController();
            //TODO: handle this errors - don't return null - log on files or somewhere else.
        } catch (MalformedURLException ex) {
            Logger.getLogger(AppSceneController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AppSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private void setMenuActions(MenuController menuController) {
        JFXButton btnDashboard = menuController.getMenuItem(MenuItemType.DASHBOARD);
        JFXButton btnProducts = menuController.getMenuItem(MenuItemType.PRODUCTS);
        JFXButton btnProductTypes = menuController.getMenuItem(MenuItemType.PRODUCT_TYPES);

        btnDashboard.setOnAction((event) -> {
            String currentSceneName = sceneOrchestrator.getCurrentSceneName().getSceneName();
            if (!currentSceneName.equals(Constants.SCENE_DASHBOARD)) {
                sceneManager.requestScene(Constants.SCENE_DASHBOARD, sceneOrchestrator);
            }
        });

        btnProducts.setOnAction((event) -> {
            String currentSceneName = sceneOrchestrator.getCurrentSceneName().getSceneName();
            if (!currentSceneName.equals(Constants.SCENE_PRODUCTS)) {
                sceneManager.requestScene(Constants.SCENE_PRODUCTS, sceneOrchestrator);
            }
        });

        btnProductTypes.setOnAction((event) -> {
            String currentSceneName = sceneOrchestrator.getCurrentSceneName().getSceneName();
            if (!currentSceneName.equals(Constants.SCENE_PRODUCT_TYPES)) {
                sceneManager.requestScene(Constants.SCENE_PRODUCT_TYPES, sceneOrchestrator);
            }
        });
    }

    @Override
    public AnchorPane getRootPane() {
        return apMain;
    }

}
