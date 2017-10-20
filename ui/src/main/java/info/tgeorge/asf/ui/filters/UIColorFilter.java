/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui.filters;

import com.jfoenix.controls.JFXColorPicker;
import info.tgeorge.asf.common.filters.Filter;
import info.tgeorge.asf.common.filters.FilterOperator;
import info.tgeorge.asf.common.filters.FilterType;
import info.tgeorge.asf.ui.ColorUtils;
import info.tgeorge.asf.ui.Constants;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;

/**
 *
 * @author george
 */
public class UIColorFilter extends BaseUIFilter {

    private final JFXColorPicker uiControl;

    public UIColorFilter(JFXColorPicker uiControl) {
        this.uiControl = uiControl;
        this.filter = new Filter(FilterType.STRING, Constants.FILTER_COLOR, FilterOperator.EQUAL_TO);

        bindFilterToUI();
    }

    private void bindFilterToUI() {
        uiControl.valueProperty().addListener((ObservableValue<? extends Color> observable, Color oldValue, Color newValue) -> {
            setFilterValue(ColorUtils.colorToHex(uiControl.getValue()));
        });
    }
}
