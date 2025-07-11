package tests;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.BaseTest;

import static org.hamcrest.Matchers.equalTo;

public class GetOrdersListTest extends BaseTest {

    @DisplayName("Получение списка заказов авторизованного пользователя")
    @Description("В тесте проверяется ответ системы на запрос списка заказов авторизованного пользователя")
    @Test
    public void getOrderListWithAuth() {
        response = userSteps().stepCreateUser(createUser);
        userSteps().stepLoginUser(loginUser);
        Response actualResp = orderSteps().stepGetOrdersList(response.path("accessToken"));
        actualResp.then().statusCode(200).assertThat().body("success", equalTo(true));
    }

    @DisplayName("Получение списка заказов не авторизованного пользователя")
    @Description("В тесте проверяется ответ системы на запрос списка заказов не авторизованного пользователя")
    @Test
    public void getOrderListWithoutAuth() {
        response = userSteps().stepCreateUser(createUser);
        userSteps().stepLoginUser(loginUser);
        Response actualResp = orderSteps().stepGetOrdersList("");
        actualResp.then().statusCode(401).assertThat().body("message", equalTo("You should be authorised"));
    }

}
