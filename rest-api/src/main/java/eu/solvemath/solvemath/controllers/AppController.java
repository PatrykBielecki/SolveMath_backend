package eu.solvemath.solvemath.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping(method = RequestMethod.POST, value = "/login_to_home/{username}/{team_name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> loginToHome(@PathVariable String username, @PathVariable String team_name){
        String teamId, userName = username, userId;
        try{
            teamId = jdbcTemplate.queryForObject("select team_id from postgres.teams where team_name='"+team_name+"'", String.class);
        }
        catch (EmptyResultDataAccessException e){
            jdbcTemplate.queryForObject("select postgres.add_team('"+team_name+"')", String.class);
            teamId = jdbcTemplate.queryForObject("select team_id from postgres.teams where team_name='"+team_name+"'", String.class);
        }

        try{
            userId = jdbcTemplate.queryForObject("select user_id from postgres.users where user_name='"+userName+"'", String.class);
        }
        catch (EmptyResultDataAccessException e){
            String tmp = "select postgres.add_user('"+userName+"', '"+teamId+"', " + "'"+0+"')";
            jdbcTemplate.queryForObject("select postgres.add_user('"+userName+"', '"+teamId+"', " + "'"+0+"')", String.class);
            userId = jdbcTemplate.queryForObject("select user_id from postgres.users where user_name='"+userName+"'", String.class);
        }

        HashMap<String, String> map = new HashMap<>();
        map.put("username", userName);
        map.put("team_name", team_name);
        map.put("user_id", userId);
        map.put("team_id", teamId);
        return map;

    }

}
