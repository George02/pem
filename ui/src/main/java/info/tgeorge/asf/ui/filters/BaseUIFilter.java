/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui.filters;

import info.tgeorge.asf.common.filters.Filter;
import info.tgeorge.asf.ui.Callback;

/**
 *
 * @author george
 */
public abstract class BaseUIFilter implements UIFilter {

    protected Filter filter;
    protected Callback<Object> filterValueChangedListener;

    public void onValueChanged(Callback<Object> filterValueChangedListener) {
        this.filterValueChangedListener = filterValueChangedListener;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    public void setFilterValue(Object value) {
        filter.setValue(value);
        filterValueChangedListener.call(value);
    }
}
