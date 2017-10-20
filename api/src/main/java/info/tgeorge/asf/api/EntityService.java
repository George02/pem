/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.api;

import info.tgeorge.asf.common.filters.Filter;
import info.tgeorge.asf.common.models.Product;
import info.tgeorge.asf.common.models.ProductType;
import java.util.List;

/**
 *
 * @author george
 */
public interface EntityService {

    List<ProductType> getProductTypes();

    List<Product> getFilteredProducts(List<Filter> filters, int startPosition, int maxResults);

    List<Product> getProducts(int startPosition, int maxResults);

    public int getProductsCount(List<Filter> filters);

}
