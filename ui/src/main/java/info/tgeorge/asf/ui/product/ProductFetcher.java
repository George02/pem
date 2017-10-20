/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui.product;

import info.tgeorge.asf.ui.filters.UIFiltersState;
import info.tgeorge.asf.api.EntityService;
import info.tgeorge.asf.common.models.Product;
import info.tgeorge.asf.ui.Callback;
import info.tgeorge.asf.ui.task.FetchProductsTask;
import java.util.List;

/**
 *
 * @author george
 */
public class ProductFetcher {

    private final EntityService es;
    private int startPosition = 0;
    private int maxResults = 5;

    public ProductFetcher(EntityService es) {
        this.es = es;
    }

    public void fetch(UIFiltersState uiFiltersState, Callback<List<Product>> callback) {
        FetchProductsTask fetchProductsTask = new FetchProductsTask(es);
        fetchProductsTask.setStartPosition(startPosition);
        fetchProductsTask.setMaxResults(maxResults);
        fetchProductsTask.setFilters(uiFiltersState.getFilters());
        fetchProductsTask.setOnSucceeded((ev) -> {
            List<Product> products = fetchProductsTask.getValue();
            callback.call(products);
        });
        fetchProductsTask.setOnFailed((ev) -> {
            throw new RuntimeException("Fetch products failed. Reason: ", fetchProductsTask.getException());
        });

        Thread fetchProductsThread = new Thread(fetchProductsTask);
        fetchProductsThread.start();
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

}
