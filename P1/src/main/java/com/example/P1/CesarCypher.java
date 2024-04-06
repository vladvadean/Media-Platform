package com.example.P1;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class CesarCypher {

    public static String GenerateCypher(String input, CypherDifficulty difficulty) {
        //only for lowercase
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String rez = "";
        String date = dtf.format(LocalDateTime.now());
        date = date.substring(0, 2);
        int index = 0;
        switch (difficulty) {
            case CypherDifficulty.SIMPLE -> index = 1;
            case CypherDifficulty.MEDIUM -> index = 4;
            case CypherDifficulty.COMPLEX -> index = Integer.parseInt(date);
        }
        for (int i = 0; i < input.length(); i++) {
            char letter = input.charAt(i);
            if (letter >= 'A' && letter <= 'Z') {
                letter = (char) Math.max((letter + index) % 91, 64 + index);
            } else {
                letter = (char) Math.max((letter + index) % 123, 96 + index);
            }
            rez = rez + letter;
        }
        return rez;
    }
}