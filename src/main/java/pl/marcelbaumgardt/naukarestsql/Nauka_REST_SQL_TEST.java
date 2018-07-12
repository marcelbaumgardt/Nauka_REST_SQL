package pl.marcelbaumgardt.naukarestsql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Nauka_REST_SQL_TEST {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Nauka_REST_SQL_TEST.class, args);
        //context.close();

    }



}
