/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui.view;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author george
 */
public class ProductTableModel extends RecursiveTreeObject<ProductTableModel> {

    private IntegerProperty id;
    private StringProperty name;
    private DoubleProperty price;
    private StringProperty color;
    private BooleanProperty inStoc;
    private StringProperty expirationDate;
    private IntegerProperty productTypeId;

    public IntegerProperty getId() {
        return id;
    }

    public void setId(IntegerProperty id) {
        this.id = id;
    }

    public StringProperty getName() {
        return name;
    }

    public void setName(StringProperty name) {
        this.name = name;
    }

    public DoubleProperty getPrice() {
        return price;
    }

    public void setPrice(DoubleProperty price) {
        this.price = price;
    }

    public StringProperty getColor() {
        return color;
    }

    public void setColor(StringProperty color) {
        this.color = color;
    }

    public BooleanProperty getInStoc() {
        return inStoc;
    }

    public void setInStoc(BooleanProperty inStoc) {
        this.inStoc = inStoc;
    }

    public StringProperty getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(StringProperty expirationDate) {
        this.expirationDate = expirationDate;
    }

    public IntegerProperty getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(IntegerProperty productTypeId) {
        this.productTypeId = productTypeId;
    }

}
