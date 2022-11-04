package eu.solvemath.solvemath.controllers;

import eu.solvemath.solvemath.domain.Product;
import eu.solvemath.solvemath.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


@RestController
@RequestMapping("/api/products/")
public class ProductController {

    private final ProductService service;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findAll(){
        return jdbcTemplate.queryForObject("select postgres.select_all_products()", String.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findById(@PathVariable Integer id){
        return jdbcTemplate.queryForObject("select postgres.select_products_by_id("+id+")", String.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findByName(@PathVariable String name) {
        return jdbcTemplate.queryForObject("select postgres.select_products_by_name('"+name+"')", String.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/category/{categoryName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findByCategoryName(@PathVariable String categoryName) {
        return jdbcTemplate.queryForObject("select postgres.select_products_by_category('"+categoryName+"')", String.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search_name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String searchByName(@PathVariable String name) {
        return jdbcTemplate.queryForObject("select postgres.search_products_by_name('"+name+"')", String.class);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add/{name}/{categoryId}/{quantity}/{maxQuantity}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addProduct(@PathVariable String name, @PathVariable Integer categoryId, @PathVariable Integer quantity,
                             @PathVariable Integer maxQuantity) {
        return jdbcTemplate.queryForObject("select postgres.add_product('"+name+"', '"+categoryId+"', '"+quantity+"'," +
                "'"+maxQuantity+"')", String.class);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update/{id}/{name}/{categoryId}/{quantity}/{maxQuantity}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@PathVariable Integer id, @PathVariable String name, @PathVariable Integer categoryId, @PathVariable Integer quantity,
                             @PathVariable Integer maxQuantity) {
        return jdbcTemplate.queryForObject("select postgres.update_product("+id+", '"+name+"', '"+categoryId+"', '"+quantity+"'," +
                "'"+maxQuantity+"')", String.class);
    }
}
