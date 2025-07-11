package tests;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import model.client.LoginUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.BaseTest;

import static org.hamcrest.Matchers.equalTo;

public class UserLoginTest extends BaseTest {

    @DisplayName("Успешная авторизация пользователя")
    @Description("В тесте проверяется ответ системы на авторизацию зарегистрированного пользователя")
    @Test
    public void successLoginUser() {
        response = userSteps().stepCreateUser(createUser);
        Response actualResp = userSteps().stepLoginUser(loginUser);
        actualResp.then().statusCode(200).assertThat().body("user.email", equalTo(createUser.getEmail()));
    }

    @DisplayName("Авторизация пользователя с неверным логином и паролем")
    @Description("В тесте проверяется ответ системы на авторизацию с неверным логином и паролем")
    @Test
    public void LoginWithWrongLoginAndPassword() {
        response = userSteps().stepCreateUser(createUser);
        Response actualResp = userSteps().stepLoginUser(new LoginUser("wrong_login", "wrong_password"));
        actualResp.then().statusCode(401).assertThat().body("message", equalTo("email or password are incorrect"));
    }
}
