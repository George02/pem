/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.api;

import info.tgeorge.asf.common.filters.DateRangeFilter;
import info.tgeorge.asf.common.filters.FilterOperator;
import info.tgeorge.asf.common.filters.FilterType;
import info.tgeorge.asf.common.filters.Filter;
import info.tgeorge.asf.common.models.Product;
import info.tgeorge.asf.common.models.ProductType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;

/**
 *
 * @author george
 */
public class Test {

    @org.junit.Test
    public void getProductTypes() {
        EntityServiceFactory esf = new EntityServiceFactory();
        EntityService es = esf.createEntityService();
        List<ProductType> productTypes = es.getProductTypes();
        productTypes.forEach((productType) -> {
            System.out.println(productType);
        });
        Assert.assertTrue(productTypes.size() == 24);
    }

    @org.junit.Test
    public void getProducts() {
        EntityServiceFactory esf = new EntityServiceFactory();
        EntityService es = esf.createEntityService();
        List<Product> products = es.getProducts(1, 10);
        Assert.assertTrue(!products.isEmpty());
        Assert.assertTrue(products.size() == 10);
    }

    @org.junit.Test
    public void testStringFilter() {
        List<Filter> filters = new ArrayList<>();

        filters.add(new Filter(FilterType.STRING, "name", "a%", FilterOperator.LIKE));

        EntityServiceFactory esf = new EntityServiceFactory();
        EntityService es = esf.createEntityService();
        List<Product> products = es.getFilteredProducts(filters, 0, 10);

        products.forEach(product -> Assert.assertTrue("Product starts with a", product.getName().startsWith("a")));
    }

    @org.junit.Test
    public void testBooleanFilter() {
        List<Filter> filters = new ArrayList<>();

        filters.add(new Filter(FilterType.BOOLEAN, "stoc", true, FilterOperator.EQUAL_TO));

        EntityServiceFactory esf = new EntityServiceFactory();
        EntityService es = esf.createEntityService();
        List<Product> products = es.getFilteredProducts(filters, 0, 10);

        products.forEach(product -> Assert.assertTrue("Product is in stoc", product.isInStoc()));
    }
    
    @org.junit.Test
    public void testDateRangeFilter() {
        List<Filter> filters = new ArrayList<>();

        filters.add(new Filter(
                FilterType.DATE_RANGE,
                "expirationDate",
                new DateRangeFilter("22-10-2017", "24-10-2017"),
                FilterOperator.BETWEEN
        ));

        EntityServiceFactory esf = new EntityServiceFactory();
        EntityService es = esf.createEntityService();
        List<Product> products = es.getFilteredProducts(filters, 0, 10);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        products.forEach((product) -> {
            System.out.println(formatter.format(product.getExpirationDate()));
        });

//        products.forEach(product -> {
//            try {
//                Assert.assertTrue("Product expiration_date is after 24-10-2017",
//                        product.getExpirationDate().after(formatter.parse("24-10-2017")));
//                Assert.assertTrue("Product expiration_date is before 22-10-2017",
//                        product.getExpirationDate().before(formatter.parse("24-10-2017")));
//            } catch (ParseException ex) {
//                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        );
    }

}
