package tests;

import io.qameta.allure.Description;
import model.client.CreateUserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.BaseTest;

public class GetOrdersListTest extends BaseTest {

    @DisplayName("Получение списка заказов авторизованного пользователя")
    @Description("В тесте проверяется ответ системы на запрос списка заказов авторизованного пользователя")
    @Test
    public void getOrderListWithAuth() {
        CreateUserResponse response = userSteps().stepCreateUser(createUser);
        userSteps().stepLoginUser(loginUser);
        orderSteps().stepGetOrdersList(response.getAccessToken());
    }

    @DisplayName("Получение списка заказов не авторизованного пользователя")
    @Description("В тесте проверяется ответ системы на запрос списка заказов не авторизованного пользователя")
    @Test
    public void getOrderListWithoutAuth() {
        CreateUserResponse response = userSteps().stepCreateUser(createUser);
        userSteps().stepLoginUser(loginUser);
        orderSteps().stepGetOrdersList("");
    }

}
