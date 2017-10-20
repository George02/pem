/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui.filters;

import info.tgeorge.asf.common.filters.Filter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author george
 */
public class UIFiltersState {

    private final Map<String, BaseUIFilter> uiFilters;

    public UIFiltersState() {
        this.uiFilters = new HashMap<>();
    }

    public void registerFilters(List<BaseUIFilter> uiFilters) {
        uiFilters.forEach(uiFilter -> this.uiFilters.put(uiFilter.getFilter().getKey(), uiFilter));
    }

    public void updateFilter(BaseUIFilter uiFilter) {
        if (!uiFilters.containsKey(uiFilter.getFilter().getKey())) {
            throw new RuntimeException("Filter not registred exception.");
        }

        uiFilters.put(uiFilter.getFilter().getKey(), uiFilter);
    }

    public List<Filter> getFilters() {
        List<Filter> filters = new ArrayList<>();

        uiFilters.values().forEach((uiFilter) -> {
            filters.add(uiFilter.getFilter());
        });

        return filters;
    }
}
