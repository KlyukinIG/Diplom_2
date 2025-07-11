package tests;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import model.client.CreateUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.BaseTest;

import static org.hamcrest.Matchers.equalTo;

public class UserCreateTest extends BaseTest {

    @DisplayName("Успешное создание пользователя")
    @Description("В тесте проверяется успешное создание нового пользователя")
    @Test
    public void successCreateUser() {
        response = userSteps().stepCreateUser(createUser);
        response.then().statusCode(200).assertThat()
                .body("user.email", equalTo(createUser.getEmail()));

    }

    @DisplayName("Создание дубликата пользователя")
    @Description("В тесте проверяется ответ системы на создание пользователя, который уже зарегистрирован")
    @Test
    public void сreateDoubleUser() {
        response = userSteps().stepCreateUser(createUser);
        Response actualResp = userSteps().stepCreateUser(createUser);
        actualResp.then().statusCode(403).assertThat()
                .body("message", equalTo("User already exists"));
    }

    @DisplayName("Создание пользователя без name")
    @Description("В тесте проверяется ответ системы на создание пользователя без обязательного параметра name")
    @Test
    public void сreateUserWithoutName() {
        CreateUser createUser1 = new CreateUser("testpochta111@mail.com", "123123");
        response = userSteps().stepCreateUser(createUser1);
        response.then().statusCode(403).assertThat()
                .body("message", equalTo("Email, password and name are required fields"));
    }
}
