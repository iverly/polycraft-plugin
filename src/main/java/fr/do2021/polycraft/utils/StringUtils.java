package fr.do2021.polycraft.utils;

import java.util.Random;
import java.util.regex.Pattern;

public class StringUtils {

    public static boolean isUniversityEmail(String email) {
        String regexPattern = "^[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+@(etu\\.){0,1}umontpellier\\.fr$";
        return StringUtils.patternMatches(email, regexPattern);
    }

    public static String generateRandom(int length) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private static boolean patternMatches(String input, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(input)
                .matches();
    }

}
