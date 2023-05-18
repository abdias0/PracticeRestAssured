package org.example;
import java.util.Random;

import java.util.Random;

public class EmailGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String[] EMAIL_DOMAINS = {
            "gmail.com",
            "yahoo.com",
            "hotmail.com",
            "outlook.com",
            "example.com"
    };

    public static String generateRandomEmail() {
        StringBuilder emailBuilder = new StringBuilder();

        Random random = new Random();

        // Generate a random email length between 8 and 15 characters
        int emailLength = random.nextInt(8) + 8;

        // Generate a random email name
        for (int i = 0; i < emailLength; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            emailBuilder.append(randomChar);
        }

        // Add the domain to the email
        String emailDomain = getRandomDomain();
        emailBuilder.append("@").append(emailDomain);

        return emailBuilder.toString();
    }

    private static String getRandomDomain() {
        Random random = new Random();
        int randomIndex = random.nextInt(EMAIL_DOMAINS.length);

        return EMAIL_DOMAINS[randomIndex];
    }
}