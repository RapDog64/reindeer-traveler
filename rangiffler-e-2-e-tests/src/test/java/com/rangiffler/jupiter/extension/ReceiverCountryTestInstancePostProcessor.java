package com.rangiffler.jupiter.extension;

import com.rangiffler.api.service.CountryClient;
import com.rangiffler.jupiter.annotation.ReceiverCountry;
import com.rangiffler.model.CountryJson;
import com.rangiffler.model.enums.Country;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class ReceiverCountryTestInstancePostProcessor implements TestInstancePostProcessor {

    private final CountryClient countryService = new CountryClient();

    @Override
    public void postProcessTestInstance(Object testInstance, final ExtensionContext context) throws Exception {
        final List<Field> fields = Arrays.stream(testInstance.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(ReceiverCountry.class))
                .filter(f -> f.getType() == CountryJson.class)
                .peek(f -> f.setAccessible(true))
                .toList();


        for (Field field : fields) {
            final Country country = field.getDeclaredAnnotation(ReceiverCountry.class).country();
            final CountryJson foundCountry = countryService.getAllCountries().stream()
                    .filter(c -> c.getCode().equals(country.code))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Country is not found"));

            field.set(testInstance, foundCountry);
        }
    }
}
