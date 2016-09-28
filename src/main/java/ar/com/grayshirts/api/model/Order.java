package ar.com.grayshirts.api.model;

import ar.com.grayshirts.api.type.BaseModel;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;
import javax.validation.constraints.NotNull;

@Entity(value="orders", noClassnameStored=true)
public class Order extends BaseModel {

    @NotNull @Reference(idOnly = true)
    Product item;
    @NotNull
    Double price;
    @NotNull
    Integer quantity;

    public Product getItem() {
        return item;
    }
    public void setItem(Product item) {
        this.item = item;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
