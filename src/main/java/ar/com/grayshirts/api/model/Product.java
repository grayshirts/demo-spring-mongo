package ar.com.grayshirts.api.model;

import ar.com.grayshirts.api.type.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;

@Entity(value="products", noClassnameStored=true)
public class Product extends BaseModel {

    @NotNull
    public String sku;
    @NotNull
    public String description;
    @Property("instock") @JsonProperty("instock")
    public Integer stock;
}
