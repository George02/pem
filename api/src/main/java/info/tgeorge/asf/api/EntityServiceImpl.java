/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.api;

import info.tgeorge.asf.api.entity.ProductEntity;
import info.tgeorge.asf.api.entity.ProductTypeEntity;
import info.tgeorge.asf.common.filters.Filter;
import info.tgeorge.asf.common.models.Product;
import info.tgeorge.asf.common.models.ProductType;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author george
 */
public class EntityServiceImpl implements EntityService {

    SessionFactory sessionFactory = null;

    public EntityServiceImpl() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        sessionFactory = configuration
                .addAnnotatedClass(ProductEntity.class)
                .addAnnotatedClass(ProductTypeEntity.class)
                .buildSessionFactory(serviceRegistry);
    }

    @Override
    public List<ProductType> getProductTypes() {
        List<ProductType> productTypes = new ArrayList<>();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(ProductTypeEntity.class);

        List<ProductTypeEntity> results = criteria.list();

        results.stream().map((result) -> {
            ProductType productType = new ProductType();
            productType.setDescription(result.getDescription());
            productType.setName(result.getName());
            productType.setProductTypeId(result.getProductTypeId());
            return productType;
        }).forEachOrdered(productType -> productTypes.add(productType));

        transaction.commit();
        session.close();

        return productTypes;
    }

    @Override
    public List<Product> getFilteredProducts(List<Filter> filters, int startPosition, int maxResults) {
        List<Product> products = new ArrayList<>();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(ProductEntity.class);

        filters.forEach((filter) -> {
            if (filter.getValue() != null) {
                try {
                    criteria.add(FilterUtil.getCriterion(filter));
                } catch (ParseException ex) {
                    Logger.getLogger(EntityServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        criteria.setFirstResult(startPosition);
        criteria.setMaxResults(maxResults);

        List<ProductEntity> results = criteria.list();
        results.stream().map((result) -> {
            Product product = new Product();
            product.setName(result.getName());
            product.setColor(result.getColor());
            product.setExpirationDate(result.getExpirationDate());
            product.setInStoc(result.isInStoc());
            product.setPrice(result.getPrice());
            product.setProductId(result.getProductId());
            product.setProductTypeId(result.getProductTypeId());
            return product;
        }).forEachOrdered(product -> products.add(product));

        transaction.commit();
        session.close();

        return products;
    }

    @Override
    public List<Product> getProducts(int startPosition, int maxResults) {
        List<Product> products = new ArrayList<>();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(ProductEntity.class);

        criteria.setFirstResult(startPosition);
        criteria.setMaxResults(maxResults);

        List<ProductEntity> results = criteria.list();
        results.stream().map((result) -> {
            Product product = new Product();
            product.setName(result.getName());
            product.setColor(result.getColor());
            product.setExpirationDate(result.getExpirationDate());
            product.setInStoc(result.isInStoc());
            product.setPrice(result.getPrice());
            product.setProductId(result.getProductId());
            product.setProductTypeId(result.getProductTypeId());
            return product;
        }).forEachOrdered(product -> products.add(product));

        transaction.commit();
        session.close();

        return products;
    }

    @Override
    public int getProductsCount(List<Filter> filters) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(ProductEntity.class);
        criteria.setProjection(Projections.projectionList()
                .add(Projections.rowCount()));

        filters.forEach((filter) -> {
            if (filter.getValue() != null) {
                try {
                    criteria.add(FilterUtil.getCriterion(filter));
                } catch (ParseException ex) {
                    Logger.getLogger(EntityServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        Object count = criteria.uniqueResult();

        transaction.commit();
        session.close();

        return Long.valueOf(String.valueOf(count)).intValue();
    }

}
