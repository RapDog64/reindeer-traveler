package com.rangiffler.utility;

import com.github.javafaker.Faker;
import com.rangiffler.model.CountryJson;
import com.rangiffler.model.PhotoJson;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Base64;

public class DataGenerator {

    private static final Faker faker = new Faker();
    private static final String IMAGE_BASE64_PREFIX = "data:image/jpeg;base64,";

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
        return PhotoJson.builder()
                .username(username)
                .description(generateRandomSentence(5))
                .countryJson(country)
                .photo(IMAGE_BASE64_PREFIX + getImageBytes("src/test/resources/photos/berlin.jpeg"))
                .build();
    }

    @SneakyThrows
    private static String getImageBytes(String path) {
        BufferedImage bImage = ImageIO.read(new File(path));
        var bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpeg", bos);
        return Base64.getEncoder().encodeToString(bos.toByteArray());
    }
}
