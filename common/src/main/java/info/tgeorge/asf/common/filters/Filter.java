/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.common.filters;

/**
 *
 * @author george
 */
public class Filter {

    private FilterType type;
    private String key;
    private Object value;
    private FilterOperator operator;

    public Filter(FilterType type, String key) {
        this.type = type;
        this.key = key;
    }
    
    public Filter(FilterType type, String key, FilterOperator operator) {
        this.type = type;
        this.key = key;
        this.operator = operator;
    }

    public Filter(FilterType type, String key, Object value, FilterOperator operator) {
        this.type = type;
        this.key = key;
        this.value = value;
        this.operator = operator;
    }

    public FilterType getType() {
        return type;
    }

    public void setType(FilterType type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public FilterOperator getOperator() {
        return operator;
    }

    public void setOperator(FilterOperator operator) {
        this.operator = operator;
    }

}
