package eu.solvemath.solvemath.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class AppController {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @RequestMapping(method = RequestMethod.GET, value = "/all_users", produces = MediaType.APPLICATION_JSON_VALUE)
    public String selectAllUsers(){
        return jdbcTemplate.queryForObject("select postgres.select_all_users()", String.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all_teams", produces = MediaType.APPLICATION_JSON_VALUE)
    public String selectAllTeams(){
        return jdbcTemplate.queryForObject("select postgres.select_all_teams()", String.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all_challenges", produces = MediaType.APPLICATION_JSON_VALUE)
    public String selectAllChallenges(){
        return jdbcTemplate.queryForObject("select postgres.select_all_challenges()", String.class);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add_user/{name}/{team_id}/{score}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addUser(@PathVariable String name, @PathVariable Integer team_id, @PathVariable Integer score){
        return jdbcTemplate.queryForObject("select postgres.add_user('"+name+"', '"+team_id+"', " +
                "'"+score+"')", String.class);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add_team/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addTeam(@PathVariable String name){
        return jdbcTemplate.queryForObject("select postgres.add_team('"+name+"')", String.class);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add_challenge/{reciever}/{sender}/{score}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addChallenge(@PathVariable Integer reciever, @PathVariable Integer sender, @PathVariable Integer score){
        return jdbcTemplate.queryForObject("select postgres.add_challenge('"+reciever+"', '"+sender+"', '"+score+"')", String.class);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update_score/{user_id}/{score}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateScore(@PathVariable Integer user_id, @PathVariable Integer score) {
        return jdbcTemplate.queryForObject("select postgres.update_score("+user_id+", '"+score+"')", String.class);
    }
}
