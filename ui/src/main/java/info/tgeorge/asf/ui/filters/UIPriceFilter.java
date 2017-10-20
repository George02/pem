/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui.filters;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import info.tgeorge.asf.common.filters.Filter;
import info.tgeorge.asf.common.filters.FilterOperator;
import info.tgeorge.asf.common.filters.FilterType;
import info.tgeorge.asf.ui.Constants;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;

/**
 *
 * @author george
 */
public class UIPriceFilter extends BaseUIFilter {

    private final JFXComboBox cbPriceOperator;
    private final JFXTextField textField;

    public UIPriceFilter(JFXComboBox<Label> cbPriceOperator, JFXTextField textField) {
        this.cbPriceOperator = cbPriceOperator;
        this.textField = textField;
        this.filter = new Filter(FilterType.DOUBLE, Constants.PRICE_FILTER);

        Label lbl = cbPriceOperator.valueProperty().get();
        String priceOperatorName = lbl.getText();
        setFilterOperatorType(priceOperatorName);

        bindFilterToUI();
    }

    private void bindFilterToUI() {
        cbPriceOperator.valueProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            Label lbl = (Label) newValue;
            String priceOperatorName = lbl.getText();
            setFilterOperatorType(priceOperatorName);

            if (!"".equals(textField.getText())) {
                setFilterValue(Double.valueOf(textField.getText()));
            } else {
                setFilterValue(null);
            }
        });

        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*") || !newValue.contains(".")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }

            if (!"".equals(textField.getText())) {
                setFilterValue(Double.valueOf(textField.getText()));
            } else {
                setFilterValue(null);
            }
        });
    }

    private void setFilterOperatorType(String priceOperatorName) {
        switch (priceOperatorName) {
            case Constants.PRICE_LOWER_THAN:
                this.filter.setOperator(FilterOperator.LOWER_THAN);
                break;
            case Constants.PRICE_GREATER_THAN:
                this.filter.setOperator(FilterOperator.GREATER_THAN);
                break;
            case Constants.PRICE_EQUAL_TO:
                this.filter.setOperator(FilterOperator.EQUAL_TO);
                break;
            default:
                break;
        }
    }

}
