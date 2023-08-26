package com.rangiffler.jupiter.extension;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.fasterxml.jackson.databind.JsonNode;
import com.rangiffler.api.handler.CookieHandler;
import com.rangiffler.api.handler.SessionStorageHandler;
import com.rangiffler.api.service.AuthenticationClient;
import com.rangiffler.config.AppConfig;
import com.rangiffler.jupiter.annotation.ApiLogin;
import com.rangiffler.jupiter.annotation.GenerateUser;
import com.rangiffler.model.UserJson;
import io.qameta.allure.AllureId;
import io.qameta.allure.Step;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import java.util.Objects;

import static com.rangiffler.jupiter.extension.CreateUserExtension.API_LOGIN_USERS_NAMESPACE;

public class ApiAuthExtension implements BeforeEachCallback {

    private final AuthenticationClient authClient = new AuthenticationClient();

    @Step("Login to rangiffler using api")
    @Override
    public void beforeEach(final ExtensionContext context) throws Exception {
        ApiLogin apiLoginAnnotation = context.getRequiredTestMethod().getAnnotation(ApiLogin.class);
        GenerateUser generateUserAnnotation = apiLoginAnnotation.user();
        if ((!generateUserAnnotation.handleAnnotation() && "".equals(apiLoginAnnotation.username()) && "".equals(apiLoginAnnotation.password()))) {
            throw new IllegalArgumentException("You have to provide in @ApiLogin annotation user by username/password or @GenerateUser");
        }
        String testId = getTestId(context);

        UserJson userToLogin;
        if (generateUserAnnotation.handleAnnotation()) {
            userToLogin = context.getStore(API_LOGIN_USERS_NAMESPACE).get(testId, UserJson.class);
        } else {
            userToLogin = new UserJson();
            userToLogin.setUserName(apiLoginAnnotation.username());
            userToLogin.setPassword(apiLoginAnnotation.password());
        }
        apiLogin(userToLogin.getUsername(), userToLogin.getPassword());
        Selenide.open(AppConfig.config.frontUrl());
        com.codeborne.selenide.SessionStorage sessionStorage = Selenide.sessionStorage();
        sessionStorage.setItem("codeChallenge", SessionStorageHandler.getInstance().getCodeChallenge());
        sessionStorage.setItem("id_token", SessionStorageHandler.getInstance().getToken());
        sessionStorage.setItem("codeVerifier", SessionStorageHandler.getInstance().getCodeVerifier());

        WebDriverRunner.getWebDriver().manage()
                .addCookie(new Cookie("JSESSIONID", CookieHandler.getInstance().getCookieValueByPart("JSESSIONID")));
    }

    private void apiLogin(String username, String password) throws Exception {
        authClient.authorize();
        authClient.login(username, password);
        JsonNode token = authClient.getToken();
        SessionStorageHandler.getInstance().addToken(token.get("id_token").asText());
    }

    private String getTestId(final ExtensionContext context) {
        return Objects.requireNonNull(
                context.getRequiredTestMethod().getAnnotation(AllureId.class)
        ).value();
    }
}

