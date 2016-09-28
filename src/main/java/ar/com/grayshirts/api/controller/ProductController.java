package ar.com.grayshirts.api.controller;

import ar.com.grayshirts.api.dao.ProductDao;
import ar.com.grayshirts.api.model.Product;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @RequestMapping
    public List<Product> list() {
        return productDao.find().asList();
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<Product> get(@PathVariable String id) {
        try {
            return new ResponseEntity<Product>(productDao.get(new ObjectId(id)), HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody @Validated Product product) {
        if(productDao.count("sku", product.sku)>0) throw new RuntimeException("Duplicated sku code");
        Product p = new Product();
        p.sku = product.sku;
        p.description = product.description;
        p.stock = product.stock;
        productDao.save(p);
        return p;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        productDao.deleteById(new ObjectId(id));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
    public Product edit(@PathVariable String id, @RequestBody Map<String, String> map) {
        Product product = productDao.get(new ObjectId(id));
        if (map.containsKey("sku")) product.sku = map.get("sku");
        if (map.containsKey("description")) product.description = map.get("description");
        if (map.containsKey("instock")) product.stock = new Integer(map.get("instock"));
        productDao.save(product);
        return product;
    }
}
