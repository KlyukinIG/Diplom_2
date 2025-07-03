package tests;

import io.qameta.allure.Description;
import model.client.*;
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
    @MethodSource("params")
    public void successUpdateUser(String email, String password, String name) {
        response = userSteps().stepCreateUser(createUser);
        userSteps().stepLoginUser(loginUser);
        UpdateUser updateUser = new UpdateUser(email, password, name);
        userSteps().stepUpdateUser(updateUser,response.getAccessToken());
    }

    @DisplayName("Обновление пользователя без авторизации")
    @Description("В тесте проверяется ответ системы на обновление пользователя с неуспешной авторизацией")
    @ParameterizedTest
    @MethodSource("params")
    public void UpdateUserWithoutAuth(String email, String password, String name) {
        response = userSteps().stepCreateUser(createUser);
        userSteps().stepLoginUser(loginUser);
        UpdateUser updateUser = new UpdateUser(email, password, name);
        userSteps().stepUpdateUser(updateUser,"wrong_token");
    }


    public static Stream<Arguments> params() {
        CreateUser user = new CreateUser("pochtaruuuu.@mail.com", "233322111");
        return Stream.of(
                Arguments.of(user.getEmail(), "44444444", "Byba"),
                //проверка попытки регистрации с существующей почтой
                Arguments.of(user.getEmail() + "@yandex.ru", "99999999", "Pepa"),
                Arguments.of("mail" + (int)(Math.random() * 10000) + "@yandex.ru", "11111111", "Pepe")
        );
    }
}
