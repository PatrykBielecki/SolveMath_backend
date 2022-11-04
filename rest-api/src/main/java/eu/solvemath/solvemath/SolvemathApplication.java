package eu.solvemath.solvemath;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Solvemath REST API", version = "0.1", description = "Solvemath API explorer"))
public class SolvemathApplication {

    public static void main(String[] args) {
        SpringApplication.run(SolvemathApplication.class, args);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource ds){
        return new JdbcTemplate(ds);
    }

}
