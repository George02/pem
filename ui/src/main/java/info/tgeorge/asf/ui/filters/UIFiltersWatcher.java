/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui.filters;

import info.tgeorge.asf.ui.Callback;
import java.util.List;

/**
 *
 * @author george
 */
public class UIFiltersWatcher {

    private Callback<BaseUIFilter> filterValueChangedListener;
    private boolean watch = true;

    public UIFiltersWatcher() {
    }

    public void watchAll(List<BaseUIFilter> uiFilters) {
        uiFilters.forEach((uiFilter) -> {
            uiFilter.onValueChanged((value) -> {
                if (watch) {
                    filterValueChangedListener.call(uiFilter);
                }
            });
        });
    }

    public void on() {
        watch = true;
    }

    public void off() {
        watch = false;
    }

    public void setOnFilterValueChangeListener(Callback<BaseUIFilter> filterValueChangedListener) {
        this.filterValueChangedListener = filterValueChangedListener;
    }

}
