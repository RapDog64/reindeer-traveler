package com.rangiffler.jupiter.extension;


import com.rangiffler.api.service.grpc.GeoGrpcClient;
import com.rangiffler.jupiter.annotation.ReceiveCountry;
import com.rangiffler.model.CountryJson;
import com.rangiffler.model.enums.Country;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class ReceiverCountryTestInstancePostProcessor implements TestInstancePostProcessor {

    private final GeoGrpcClient countryService = new GeoGrpcClient();

    @Override
    public void postProcessTestInstance(Object testInstance, final ExtensionContext context) throws Exception {
        final List<Field> fields = Arrays.stream(testInstance.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(ReceiveCountry.class))
                .filter(f -> f.getType() == CountryJson.class)
                .peek(f -> f.setAccessible(true)).toList();

        for (Field field : fields) {
            final Country country = field.getDeclaredAnnotation(ReceiveCountry.class).country();
            final CountryJson foundCountry = countryService.getAllCountries()
                    .stream()
                    .filter(c -> c.getCode().equals(country.code))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Country is not found"));

            field.set(testInstance, foundCountry);
        }
    }
}
