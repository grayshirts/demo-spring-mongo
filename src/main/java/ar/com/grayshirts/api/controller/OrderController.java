package ar.com.grayshirts.api.controller;

import ar.com.grayshirts.api.dao.OrderDao;
import ar.com.grayshirts.api.dao.ProductDao;
import ar.com.grayshirts.api.dto.OrderDto;
import ar.com.grayshirts.api.model.Order;
import ar.com.grayshirts.api.model.OrdersReport;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping
public class OrderController {

    private Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @RequestMapping
    public List<Order> list() {
        return orderDao.find().asList();
    }

    @RequestMapping(path = "/report")
    public List<OrdersReport> report() {
        return orderDao.ordersReport();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@RequestBody @Validated OrderDto order) {
        Order o = new Order();
        o.setItem(productDao.getBySku(order.item));
        o.setPrice(order.price);
        o.setQuantity(order.quantity);
        orderDao.save(o);
        log.info("New order with id {}", o.getIdAsStr());
        return o;
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<Order> get(@PathVariable String id) {
        try {
            return new ResponseEntity<Order>(orderDao.get(new ObjectId(id)), HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        orderDao.deleteById(new ObjectId(id));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
    public Order edit(@PathVariable String id, @RequestBody Map<String, String> map) {
        Order order = orderDao.get(new ObjectId(id));
        if (map.containsKey("item")) order.setItem(productDao.getBySku(map.get("item")));
        if (map.containsKey("price")) order.setPrice(new Double(map.get("price")));
        if (map.containsKey("quantity")) order.setQuantity(new Integer(map.get("quantity")));
        orderDao.save(order);
        return order;
    }
}
