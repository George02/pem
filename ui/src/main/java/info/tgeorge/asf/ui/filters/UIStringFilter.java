/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui.filters;

import info.tgeorge.asf.common.filters.FilterOperator;
import info.tgeorge.asf.common.filters.Filter;
import info.tgeorge.asf.common.filters.FilterType;
import com.jfoenix.controls.JFXTextField;
import info.tgeorge.asf.ui.Constants;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author george
 */
public class UIStringFilter extends BaseUIFilter {

    private final JFXTextField uiControl;

    public UIStringFilter(JFXTextField uiControl) {
        this.uiControl = uiControl;
        this.filter = new Filter(FilterType.STRING, Constants.NAME_FILTER, FilterOperator.LIKE);

        bindFilterToUI();
    }

    private void bindFilterToUI() {
        uiControl.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!"".equals(newValue)) {
                setFilterValue(newValue);
            } else {
                setFilterValue(null);
            }
        });
    }
}
