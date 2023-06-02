package com.example.chessclient;

public class Column {
    public static String getLetter(int number) {
        if (number < 1 || number > 8) {
            throw new IllegalArgumentException("Number must be between 1 and 26");
        }

        char letter = (char) (number + 96); // 'a' starts at ASCII code 97, so we add 96 to the number
        return String.valueOf(letter);
    }
}
