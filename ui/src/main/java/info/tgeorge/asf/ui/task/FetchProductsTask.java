/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui.task;

import info.tgeorge.asf.api.EntityService;
import info.tgeorge.asf.common.filters.Filter;
import info.tgeorge.asf.common.models.Product;
import java.util.List;
import javafx.concurrent.Task;

/**
 *
 * @author george
 */
public class FetchProductsTask extends Task<List<Product>> {

    private final EntityService entityService;
    private int startPosition = 0;
    private int maxResults = 0;
    private List<Filter> filters;

    public FetchProductsTask(EntityService entityService) {
        this.entityService = entityService;
    }

    @Override
    protected List<Product> call() throws Exception {
        return entityService.getFilteredProducts(filters, startPosition, maxResults);
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

}
