package ar.com.grayshirts.api.model;

import org.mongodb.morphia.annotations.Entity;
import java.util.List;

@Entity(value="orders_report", noClassnameStored=true)
public class OrdersReport {
    public Double price;
    public Integer quantity;
    public List<Product> products;
}
