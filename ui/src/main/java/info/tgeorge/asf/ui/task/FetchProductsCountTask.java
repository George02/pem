/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui.task;

import info.tgeorge.asf.api.EntityService;
import info.tgeorge.asf.common.filters.Filter;
import java.util.List;
import javafx.concurrent.Task;

/**
 *
 * @author george
 */
public class FetchProductsCountTask extends Task<Integer> {

    private final EntityService entityService;
    private List<Filter> filters;

    public FetchProductsCountTask(EntityService entityService) {
        this.entityService = entityService;
    }

    @Override
    protected Integer call() throws Exception {
        return entityService.getProductsCount(filters);
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

}
