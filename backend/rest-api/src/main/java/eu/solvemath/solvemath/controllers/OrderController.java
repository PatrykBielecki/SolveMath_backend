package eu.solvemath.solvemath.controllers;

import eu.solvemath.solvemath.domain.Order;
import eu.solvemath.solvemath.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/orders/")
public class OrderController {

    private final OrderService service;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findAll(){
        return jdbcTemplate.queryForObject("select postgres.select_all_orders()", String.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all_statuses", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findALlStatuses(){
        return jdbcTemplate.queryForObject("select postgres.select_all_statuses()", String.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findOrderById(@PathVariable Integer id){
        return jdbcTemplate.queryForObject("select postgres.select_order_by_id("+id+")", String.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/product_id/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findOrderByProductId(@PathVariable Integer productId){
        return jdbcTemplate.queryForObject("select postgres.select_order_by_product_id("+productId+")", String.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user_id/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findOrderByUserId(@PathVariable Integer userId){
        return jdbcTemplate.queryForObject("select postgres.select_order_by_user_id("+userId+")", String.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/status/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findOrderByProductId(@PathVariable String status){
        return jdbcTemplate.queryForObject("select postgres.select_order_by_status('"+status+"')", String.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/created_between/{dateFrom}/{dateTo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getOrdersByCreatedDate(@PathVariable String dateFrom, @PathVariable String dateTo){
        return jdbcTemplate.queryForObject("select postgres.select_orders_by_created_date('"+dateFrom+"', '"+dateTo+" ')", String.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/to_return_between/{dateFrom}/{dateTo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getOrdersByReturnDate(@PathVariable String dateFrom, @PathVariable String dateTo){
        return jdbcTemplate.queryForObject("select postgres.select_orders_by_return_date('"+dateFrom+"', '"+dateTo+" ')", String.class);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add/{userId}/{productId}/{quantity}/{extraInfo}/{status}/{dateToReturn}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addOrder(@PathVariable Integer userId, @PathVariable Integer productId, @PathVariable Integer quantity,
                           @PathVariable String extraInfo, @PathVariable String status, @PathVariable String dateToReturn){
        return jdbcTemplate.queryForObject("select postgres.add_order('"+userId+"', '"+productId+"', '"+quantity+"'," +
                " '"+extraInfo+"', '"+status+"', '"+dateToReturn+"')", String.class);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update/{id}/{userId}/{productId}/{quantity}/{createdDate}/{extraInfo}/{status}/{dateToReturn}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@PathVariable Integer id, @PathVariable Integer userId, @PathVariable Integer productId,
                         @PathVariable Integer quantity, @PathVariable String extraInfo, @PathVariable String status,
                         @PathVariable String dateToReturn, @PathVariable String createdDate){
        return jdbcTemplate.queryForObject("select postgres.update_order("+id+", '"+userId+"', '"+productId+"', '"+quantity+"'," +
                "'"+createdDate+"', '"+extraInfo+"', '"+status+"', '"+dateToReturn+"')", String.class);
    }
}
