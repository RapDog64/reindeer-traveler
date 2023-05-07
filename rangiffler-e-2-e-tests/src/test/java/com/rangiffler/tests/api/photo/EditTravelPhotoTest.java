package com.rangiffler.tests.api.photo;

import com.rangiffler.api.validator.ResponsePhotoValidator;
import com.rangiffler.jupiter.annotation.GenerateUser;
import com.rangiffler.jupiter.annotation.ReceiverCountry;
import com.rangiffler.jupiter.annotation.Travels;
import com.rangiffler.jupiter.annotation.User;
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

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import static com.rangiffler.jupiter.extension.CreateUserExtension.Selector;
import static com.rangiffler.model.enums.Country.GERMANY;
import static com.rangiffler.model.enums.Country.RUSSIA;
import static com.rangiffler.tests.message.Message.DIFFERENT_IDS_PROVIDED;
import static com.rangiffler.tests.message.Message.PHOTO_NOT_FOUND;
import static com.rangiffler.utility.DataGenerator.generatePhoto;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("[API][rangiffler-photo]: Travel photo")
@DisplayName("[API][rangiffler-photo]: Edit Travel photo")
public class EditTravelPhotoTest extends BaseRestTest {

    @ReceiverCountry(country = GERMANY)
    private CountryJson germany;

    @Test
    @AllureId("500026")
    @DisplayName("API: Photo service should update the user's travel photo")
    @Tag("API")
    @Severity(BLOCKER)
    @GenerateUser(travels = @Travels(country = RUSSIA, count = 1))
    void shouldUpdateTravelPhoto(@User(selector = Selector.METHOD) UserJson user) throws IOException {
        final PhotoServiceJson currentPhoto = photoService.getPhotosForUser(user.getUsername()).get(0);
        PhotoJson newPhoto = generatePhoto(currentPhoto.getId(), germany, user.getUsername());

        PhotoServiceJson updatePhoto = photoService.editPhoto(currentPhoto.getId(), newPhoto).body();
        PhotoJson photoJson = PhotoServiceJson.fromPhotoServiceJson(Objects.requireNonNull(updatePhoto), germany);

        step("Verify the photo has been updated", () ->
                ResponsePhotoValidator.validatePhoto(newPhoto, photoJson));
    }

    @Test
    @AllureId("500027")
    @DisplayName("API: Photo service should return error message about discrepancy between ids")
    @Tag("API")
    @Severity(CRITICAL)
    @GenerateUser(travels = @Travels(country = RUSSIA, count = 1))
    void shouldReturnErrorMessage(@User(selector = Selector.METHOD) UserJson user) throws IOException {
        final PhotoServiceJson currentPhoto = photoService.getPhotosForUser(user.getUsername()).get(0);
        final PhotoJson newPhoto = generatePhoto(UUID.randomUUID(), germany, user.getUsername());
        final String expectedMessage = String.format(DIFFERENT_IDS_PROVIDED, currentPhoto.getId(), newPhoto.getId());

        String actualMessage = photoService.editPhoto(currentPhoto.getId(), newPhoto)
                .errorBody()
                .string()
                .substring(10, 134);

        step("Verify '" + expectedMessage + "' is present", () ->
                assertEquals(expectedMessage, actualMessage));
    }

    @Test
    @AllureId("500028")
    @DisplayName("API: Photo service should return error message 'photo is not found'")
    @Tag("API")
    @Severity(CRITICAL)
    @GenerateUser(travels = @Travels(country = RUSSIA, count = 1))
    void shouldReturnErrorPhotoNotFound(@User(selector = Selector.METHOD) UserJson user) throws IOException {
        final UUID id = UUID.randomUUID();
        final PhotoJson newPhoto = generatePhoto(id, germany, user.getUsername());

        final String expectedMessage = String.format(PHOTO_NOT_FOUND, id);

        final String actualMessage = photoService.editPhoto(id, newPhoto)
                .errorBody()
                .string()
                .substring(10, 74);

        step("Verify '" + expectedMessage + "' is present", () ->
                assertEquals(expectedMessage, actualMessage));
    }
}
