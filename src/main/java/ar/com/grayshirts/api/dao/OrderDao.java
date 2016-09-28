package ar.com.grayshirts.api.dao;

import ar.com.grayshirts.api.model.Order;
import ar.com.grayshirts.api.model.OrdersReport;
import com.google.common.collect.ImmutableList;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Iterator;
import java.util.List;

@Repository
public class OrderDao extends BasicDAO<Order, ObjectId> {

    @Autowired
    protected OrderDao(Datastore ds) {
        super(ds);
    }

    public List<OrdersReport> ordersReport() {
        Iterator<OrdersReport> report = getDatastore().createAggregation(getEntityClass())
                .lookup("products", "item", "_id", "products")
                .out(OrdersReport.class);
        return ImmutableList.copyOf(report);
    }
}
