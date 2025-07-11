package tests;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import model.client.UpdateUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import util.BaseTest;

import java.util.stream.Stream;

public class UserUpdateTest extends BaseTest {

    @DisplayName("Успешное обновление пользователя")
    @Description("В тесте проверяется ответ системы на обновление пользователя с успешной авторизацией")
    @ParameterizedTest
    @MethodSource("paramsAuth")
    public void successUpdateUser(String email, String password, String name, int statusCode, boolean status) {
        response = userSteps().stepCreateUser(createUser);
        userSteps().stepLoginUser(loginUser);
        UpdateUser updateUser = new UpdateUser(email, password, name);
        Response actualResp = userSteps().stepUpdateUser(updateUser, response.path("accessToken"));
        Assertions.assertEquals(statusCode, actualResp.statusCode());
        Assertions.assertEquals(status, actualResp.path("success"));
    }

    @DisplayName("Обновление пользователя без авторизации")
    @Description("В тесте проверяется ответ системы на обновление пользователя без авторизации")
    @ParameterizedTest
    @MethodSource("paramsWithoutAuth")
    public void UpdateUserWithoutAuth(String email, String password, String name, int statusCode, boolean status) {
        response = userSteps().stepCreateUser(createUser);
        userSteps().stepLoginUser(loginUser);
        UpdateUser updateUser = new UpdateUser(email, password, name);
        Response actualResp = userSteps().stepUpdateUser(updateUser, "wrong_token");
        Assertions.assertEquals(statusCode, actualResp.statusCode());
        Assertions.assertEquals(status, actualResp.path("success"));
    }


    public static Stream<Arguments> paramsAuth() {
        String mail = "pochta-test" + (int) (Math.random() * 10000) + "@yandex.ru";
        return Stream.of(
                Arguments.of(mail, "111222333", "Byba", 200, true),
                //проверка попытки регистрации с существующей почтой
                Arguments.of(mail, "99999999", "Pepa", 403, false)
        );
    }

    public static Stream<Arguments> paramsWithoutAuth() {
        String mail = "pochta-test" + (int) (Math.random() * 10000) + "@yandex.ru";
        return Stream.of(
                Arguments.of(mail, "44444444", "Byba", 401, false),
                //проверка попытки регистрации с существующей почтой
                Arguments.of(mail, "99999999", "Pepa", 401, false)
        );
    }
}
