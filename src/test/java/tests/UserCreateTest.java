package tests;

import io.qameta.allure.Description;
import model.client.CreateUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.BaseTest;

public class UserCreateTest extends BaseTest {

    @DisplayName("Успешное создание пользователя")
    @Description("В тесте проверяется успешное создание нового пользователя")
    @Test
    public void successCreateUser() {
        response = userSteps().stepCreateUser(createUser);
    }

    @DisplayName("Создание дубликата пользователя")
    @Description("В тесте проверяется ответ системы на создание пользователя, который уже зарегистрирован")
    @Test
    public void сreateDoubleUser() {
        response = userSteps().stepCreateUser(createUser);
        userSteps().stepCreateUser(createUser);
    }

    @DisplayName("Создание пользователя без name")
    @Description("В тесте проверяется ответ системы на создание пользователя без обязательного параметра name")
    @Test
    public void сreateUserWithoutName() {
        CreateUser createUser1 = new CreateUser("testpochta111@mail.com", "123123");
        userSteps().stepCreateUser(createUser1);
    }
}
