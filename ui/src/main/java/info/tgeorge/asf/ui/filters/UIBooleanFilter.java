/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui.filters;

import com.jfoenix.controls.JFXCheckBox;
import info.tgeorge.asf.common.filters.Filter;
import info.tgeorge.asf.common.filters.FilterOperator;
import info.tgeorge.asf.common.filters.FilterType;
import info.tgeorge.asf.ui.Constants;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author george
 */
public class UIBooleanFilter extends BaseUIFilter {

    private final JFXCheckBox uiControl;

    public UIBooleanFilter(JFXCheckBox uiControl) {
        this.uiControl = uiControl;
        this.filter = new Filter(FilterType.BOOLEAN, Constants.FILTER_IN_STOC, FilterOperator.EQUAL_TO);

        bindFilterToUI();
    }

    private void bindFilterToUI() {
        uiControl.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            setFilterValue(uiControl.selectedProperty().getValue());
        });

        uiControl.indeterminateProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue == true) {
                setFilterValue(null);
            }
        });
    }

}
