package tests;

import io.qameta.allure.Description;
import model.client.LoginUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.BaseTest;

public class UserLoginTest extends BaseTest {

    @DisplayName("Успешная авторизация пользователя")
    @Description("В тесте проверяется ответ системы на авторизацию зарегистрированного пользователя")
    @Test
    public void successLoginUser() {
        response = userSteps().stepCreateUser(createUser);
        userSteps().stepLoginUser(loginUser);
    }

    @DisplayName("Авторизация пользователя с неверным логином и паролем")
    @Description("В тесте проверяется ответ системы на авторизацию с неверным логином и паролем")
    @Test
    public void LoginWithWrongLoginAndPassword() {
        response = userSteps().stepCreateUser(createUser);
        userSteps().stepLoginUser(new LoginUser("wrong_login", "wrong_password"));
    }
}
