package com.example.P1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * main class that contains the main method and
 * runs the whole project
 */
@SpringBootApplication
public class P1Application {

    public static void main(String[] args) {
        // String cypher = CesarCypher.GenerateCypher("ANA",CypherDifficulty.SIMPLE);
        // System.out.println(cypher);
        SpringApplication.run(P1Application.class, args);
    }

}
