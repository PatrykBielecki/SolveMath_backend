package eu.solvemath.solvemath.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import eu.solvemath.solvemath.mapping.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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





    @RequestMapping(method = RequestMethod.GET, value = "/generate_equation", produces = MediaType.APPLICATION_JSON_VALUE)
    public Question generateEquation() {

        int result = 0;
        Random rand = new Random();
        Question question = new Question();

        while (question.term_3_arr.size() <= 4) {
            int num1 = rand.nextInt(1000) + 200;
            int num2 = rand.nextInt(1000) + 200;
            int kind = rand.nextInt(4);
            String eqChar = "";


            switch (kind) {
                case 0: {
                    eqChar = "+";
                    result = num1 + num2;
                    break;
                }
                case 1: {
                    eqChar = "-";
                    while (num2 > num1) {
                        num2 = rand.nextInt(1000) + 200;
                    }
                    result = num1 - num2;
                    break;
                }
                case 2: {
                    eqChar = "/";
                    num1 = rand.nextInt(71) + 30;
                    num2 = rand.nextInt(19) + 2;

                    BigInteger big1 = BigInteger.valueOf(num1);
                    while (big1.isProbablePrime(1)) // sprawdza czy liczba pierwsza sie wylosowala i losuje inna bo bez sensu wtedy dzielimy ja przez 1 lub ja sama to  za latwe
                    {
                        num1 = rand.nextInt(51) + 50;
                        big1 = BigInteger.valueOf(num1);
                    }

                    result = num1 / num2;

                    while (num1 % num2 != 0) {
                        num2 = rand.nextInt(9) + 2;
                        result = num1 / num2;

                    }

                    break;
                }
                case 3: {
                    eqChar = "*";
                    num1 = rand.nextInt(9) + 2;
                    num2 = rand.nextInt(50) + 50;
                    result = num1 * num2;
                    break;
                }
                default: {
                    System.out.println("Kind equation error!");
                    break;
                }
            }

            question.term_1_arr.add(num1);
            question.term_2_arr.add(num2);
            question.operator_arr.add(eqChar);
            question.term_3_arr.add(result);
            question.ans_1_arr.add(result);
            question.ans_2_arr.add(result + 4);
            question.ans_3_arr.add(result - 2);
            question.ans_4_arr.add(result - 6);

        }

        return question;


    }

}
