package com.rangiffler.tests.api.grpc;

import com.rangiffler.jupiter.annotation.GenerateUser;
import com.rangiffler.jupiter.annotation.ReceiveCountry;
import com.rangiffler.jupiter.annotation.User;
import com.rangiffler.jupiter.extension.ReceiverCountryTestInstancePostProcessor;
import com.rangiffler.model.CountryJson;
import com.rangiffler.model.UserJson;
import com.rangiffler.tests.api.BaseGrpcTest;
import io.grpc.StatusRuntimeException;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.UUID;

import static com.rangiffler.jupiter.extension.BeforeSuiteExtension.ALL_COUNTRIES;
import static com.rangiffler.model.enums.Country.RUSSIA;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Epic("[API][rangiffler-geo]: Receive countries")
@DisplayName("[API][rangiffler-geo]: Get countries")
@ExtendWith({ReceiverCountryTestInstancePostProcessor.class})
public class GetCountryTest extends BaseGrpcTest {


    @ReceiveCountry(country = RUSSIA)
    private CountryJson russia;


    @Test
    @AllureId("500022")
    @DisplayName("API: Geo service should return all countries")
    @Tags({@Tag("API"), @Tag("gRPC")})
    @Severity(BLOCKER)
    @GenerateUser
    void receiveCountries(@User UserJson user) {
        final int expectedSize = 175;

        final List<CountryJson> countries = countryService.getAllCountries();

        step("Verify all the countries are present" + expectedSize, () ->
                Assertions.assertAll(
                        () -> assertEquals(expectedSize, countries.size()),
                        () -> assertEquals(countries.containsAll(ALL_COUNTRIES), ALL_COUNTRIES.containsAll(countries))
                ));
    }

    @Test
    @AllureId("500023")
    @DisplayName("API: Geo service should return one country")
    @Tags({@Tag("API"), @Tag("gRPC")})
    @Severity(BLOCKER)
    @GenerateUser
    void shouldReturnOneCountry(@User UserJson user) {
        final UUID countryId = russia.getId();

        final CountryJson receivedCountry = countryService.getCountryBy(countryId);

        step("Verify that the received country is " + russia.getName(), () ->
                assertAll(
                        () -> assertEquals(russia.getName(), receivedCountry.getName()),
                        () -> assertEquals(russia.getCode(), receivedCountry.getCode())
                ));
    }

    @Test
    @AllureId("500024")
    @DisplayName("API: Geo service should return the error message country is not found")
    @Tag("API")
    @Severity(BLOCKER)
    @GenerateUser
    void shouldReturnErrorMessage(@User UserJson user) {
        final UUID countryId = UUID.randomUUID();
        final String expectedMessage = String.format("NOT_FOUND: County with id: %s is not found", countryId);

        var message = step(String.format("Verify the '%s' message is present", expectedMessage),
                () -> assertThrows(StatusRuntimeException.class, () -> countryService.getCountryBy(countryId))
        );
        step("Verify the error message is present", ()-> {
            assertEquals(expectedMessage, message.getMessage());
        });
    }
}
