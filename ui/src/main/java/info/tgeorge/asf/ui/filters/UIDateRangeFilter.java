/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui.filters;

import com.jfoenix.controls.JFXDatePicker;
import info.tgeorge.asf.common.filters.DateRangeFilter;
import info.tgeorge.asf.common.filters.Filter;
import info.tgeorge.asf.common.filters.FilterOperator;
import info.tgeorge.asf.common.filters.FilterType;
import info.tgeorge.asf.ui.Constants;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author george
 */
public class UIDateRangeFilter extends BaseUIFilter {

    private final JFXDatePicker uiControlDateFrom;
    private final JFXDatePicker uiControlDateTo;

    public UIDateRangeFilter(JFXDatePicker from, JFXDatePicker to) {
        this.uiControlDateFrom = from;
        this.uiControlDateTo = to;
        this.filter = new Filter(FilterType.DATE_RANGE, Constants.FILTER_EXPIRATION_DATE, FilterOperator.BETWEEN);

        bindFilterToUI();
    }

    private void bindFilterToUI() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateRangeFilter drf = new DateRangeFilter();

        uiControlDateFrom.valueProperty().addListener((ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) -> {
            drf.setFrom(uiControlDateFrom.getValue().format(formatter));
            setFilterValue(drf);
        });

        uiControlDateTo.valueProperty().addListener((ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) -> {
            drf.setTo(uiControlDateTo.getValue().format(formatter));
            setFilterValue(drf);
        });
    }

}
