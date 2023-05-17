package com.rangiffler.tests.api.photo;

import com.rangiffler.api.validator.ResponsePhotoValidator;
import com.rangiffler.jupiter.annotation.GenerateUser;
import com.rangiffler.jupiter.annotation.ReceiverCountry;
import com.rangiffler.jupiter.annotation.User;
import com.rangiffler.jupiter.extension.ReceiverCountryTestInstancePostProcessor;
import com.rangiffler.model.CountryJson;
import com.rangiffler.model.PhotoJson;
import com.rangiffler.model.PhotoServiceJson;
import com.rangiffler.model.UserJson;
import com.rangiffler.tests.api.BaseRestTest;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static com.rangiffler.jupiter.extension.CreateUserExtension.Selector;
import static com.rangiffler.model.enums.Country.KAZAKHSTAN;
import static com.rangiffler.utility.DataGenerator.generatePhoto;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("[API][rangiffler-photo]: Travel photo")
@DisplayName("[API][rangiffler-photo]: Add Travel photo")
@ExtendWith({ReceiverCountryTestInstancePostProcessor.class})
public class AddTravelPhotoTest extends BaseRestTest {

    @ReceiverCountry(country = KAZAKHSTAN)
    private CountryJson kazakhstan;

    @Test
    @AllureId("500025")
    @DisplayName("API: Photo Service should add photo for a user")
    @Tag("API")
    @Severity(BLOCKER)
    @GenerateUser
    void shouldReturnErrorMessage(@User(selector = Selector.METHOD) UserJson user) throws Exception {
        final String username = user.getUsername();
        final PhotoJson photo = generatePhoto(kazakhstan, username);

        photoService.addPhoto(photo);

        final List<PhotoServiceJson> usersPhotos = photoService.getPhotosForUser(username);
        final PhotoJson uploadedPhoto = PhotoServiceJson.fromPhotoServiceJson(usersPhotos.get(0), kazakhstan);

        step("Validated created photo", () -> {
            assertEquals(1, usersPhotos.size());
            ResponsePhotoValidator.validatePhoto(photo, uploadedPhoto);
        });
    }
}
