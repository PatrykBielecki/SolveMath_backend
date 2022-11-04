package eu.solvemath.solvemath.controllers;

import eu.solvemath.solvemath.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/")
public class UserController {

    private final UserService service;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserController(UserService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findALl(){
        return jdbcTemplate.queryForObject("select postgres.select_all_users()", String.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findByName(@PathVariable String name){
        return jdbcTemplate.queryForObject("select postgres.select_users_by_name('"+name+"')", String.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findByName(@PathVariable Integer id){
        return jdbcTemplate.queryForObject("select postgres.select_users_by_id('"+id+"')", String.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/surname/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findBySurname(@PathVariable String name) {
        return jdbcTemplate.queryForObject("select postgres.select_users_by_surname('" + name + "')", String.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search_name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String searchByName(@PathVariable String name) {
        return jdbcTemplate.queryForObject("select postgres.search_users_by_name('"+name+"')", String.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search_fullname/{fullName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String searchByFullName(@PathVariable String fullName) {
        return jdbcTemplate.queryForObject("select postgres.search_users_by_fullname('"+fullName+"')", String.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search_surname/{surname}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String searchBySurname(@PathVariable String surname) {
        return jdbcTemplate.queryForObject("select postgres.search_users_by_surname('"+surname+"')", String.class);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add/{name}/{surname}/{extraInfo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String add(@PathVariable String name, @PathVariable String surname, @PathVariable String extraInfo){
        return jdbcTemplate.queryForObject("select postgres.add_user('"+name+"', '"+surname+"', " +
                "'"+extraInfo+"')", String.class);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update/{id}/{name}/{surname}/{extraInfo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateUser(@PathVariable Integer id, @PathVariable String name, @PathVariable String surname,
                             @PathVariable String extraInfo) {
        return jdbcTemplate.queryForObject("select postgres.update_user("+id+", '"+name+"', '"+surname+"', " +
                "'"+extraInfo+"')", String.class);
    }
}
