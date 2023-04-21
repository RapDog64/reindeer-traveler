package com.rangiffler.utility;

import com.github.javafaker.Faker;

public class DataGenerator {

    private static final Faker faker = new Faker();

    public static String generateRandomUsername() {
        return faker.name().username();
    }

    public static String generateRandomPassword() {
        return faker.bothify("????####");
    }

    public static String generateRandomFirstname() {
        return faker.name().firstName();
    }

    public static String generateRandomLastname() {
        return faker.name().lastName();
    }

    public static String generateNewCategory() {
        return faker.book().title();
    }

    public static String generateRandomSentence(int wordsCount) {
        return faker.lorem().sentence(wordsCount);
    }
}
