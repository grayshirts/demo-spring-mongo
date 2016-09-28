package ar.com.grayshirts.api.dao;

import ar.com.grayshirts.api.model.Product;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao extends BasicDAO<Product, ObjectId> {

    @Autowired
    protected ProductDao(Datastore ds) {
        super(ds);
    }

    public Product getBySku(String sku) {
        return createQuery().field("sku").equal(sku).get();
    }
}
