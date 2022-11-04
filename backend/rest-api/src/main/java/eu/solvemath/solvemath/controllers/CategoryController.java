package eu.solvemath.solvemath.controllers;

import eu.solvemath.solvemath.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/categories/")
public class CategoryController {

    private final CategoryService service;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findAll(){
        return jdbcTemplate.queryForObject("select postgres.select_all_categories()", String.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findById(@PathVariable Integer id){
        return jdbcTemplate.queryForObject("select postgres.select_categories_by_id("+id+")", String.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findByName(@PathVariable String name){
        return jdbcTemplate.queryForObject("select postgres.search_categories_by_name('"+name+"')", String.class);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addCategory(@PathVariable String name){
        return jdbcTemplate.queryForObject("select postgres.add_category('"+name+"')", String.class);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update/{id}/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateCategory(@PathVariable Integer id ,@PathVariable String name){
        return jdbcTemplate.queryForObject("select postgres.update_category("+id+", '"+name+"')", String.class);
    }
}
