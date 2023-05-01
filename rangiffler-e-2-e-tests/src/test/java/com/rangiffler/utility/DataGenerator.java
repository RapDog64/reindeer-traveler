package com.rangiffler.utility;

import com.github.javafaker.Faker;
import com.rangiffler.model.CountryJson;
import com.rangiffler.model.PhotoJson;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class DataGenerator {

    private static final Faker faker = new Faker();

    private static final ClassLoader classLoader = ClassLoader.getSystemClassLoader();

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


    public static String generateRandomSentence(int wordsCount) {
        return faker.lorem().sentence(wordsCount);
    }

    public static PhotoJson generatePhoto(CountryJson country, String username) {
        byte[] imageBytes = getImageBytes("src/test/resources/photos/berlin.jpeg");
        return PhotoJson.builder()
                .username(username)
                .description(generateRandomSentence(5))
                .countryJson(country)
                .photo(new String(imageBytes, StandardCharsets.UTF_8))
                .build();
    }

    @SneakyThrows
    private static byte[] getImageBytes(String path) {
        BufferedImage bImage = ImageIO.read(new File(path));
        var bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpeg", bos);
        return bos.toByteArray();
    }
}
