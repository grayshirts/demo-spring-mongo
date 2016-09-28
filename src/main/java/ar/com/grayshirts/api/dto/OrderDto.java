package ar.com.grayshirts.api.dto;

import ar.com.grayshirts.api.model.Product;
import org.mongodb.morphia.annotations.Reference;

import javax.validation.constraints.NotNull;

/**
 * Created by user on 28/09/16.
 */
public class OrderDto {

    @NotNull
    public String item;
    @NotNull
    public Double price;
    @NotNull
    public Integer quantity;
}
