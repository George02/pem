/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui.view;

import info.tgeorge.asf.ui.Callback;
import info.tgeorge.asf.ui.controller.parts.PaginatorController;
import info.tgeorge.asf.ui.task.PaginatorLoaderTask;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author george
 */
public class PaginatorView {

    private PaginatorController paginatorController;
    private Callback<PaginatorViewChanges> paginatorViewChangesListener;

    public PaginatorView() {

    }

    public void load(int productsCount, Callback<BorderPane> callback) {
        if (paginatorController == null) {
            PaginatorLoaderTask paginatorLoaderTask = new PaginatorLoaderTask();
            paginatorLoaderTask.setOnSucceeded((ev) -> {
                paginatorController = (PaginatorController) paginatorLoaderTask.getValue();
                paginatorController.setItemsCount(productsCount);

                paginatorController.getMaxResultsProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                    paginatorViewChangesListener.call(new PaginatorViewChanges(getStartPosition(), getMaxResults()));
                });

                paginatorController.getStartPositionProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                    paginatorViewChangesListener.call(new PaginatorViewChanges(getStartPosition(), getMaxResults()));
                });

                callback.call(paginatorController.getRootPane());
            });

            paginatorLoaderTask.setOnFailed((ev) -> {
                throw new RuntimeException("Paginator can't be loaded!", paginatorLoaderTask.getException());
            });

            Thread paginatorLoaderThread = new Thread(paginatorLoaderTask);
            paginatorLoaderThread.start();
        } else {
            paginatorController.setItemsCount(productsCount);

            callback.call(paginatorController.getRootPane());
        }
    }

    public void setPaginatorViewChangesListener(Callback<PaginatorViewChanges> paginatorViewChangesListener) {
        this.paginatorViewChangesListener = paginatorViewChangesListener;
    }

    public int getStartPosition() {
        return paginatorController.getStartPositionProperty().get();
    }

    public int getMaxResults() {
        return paginatorController.getMaxResultsProperty().get();
    }

    public static class PaginatorViewChanges {

        public final int startPosition;
        public final int maxResults;

        public PaginatorViewChanges(int startPosition, int maxResults) {
            this.startPosition = startPosition;
            this.maxResults = maxResults;
        }
    }

}
