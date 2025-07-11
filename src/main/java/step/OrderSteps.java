package step;

import api.OrderApi;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.order.CreateOrder;
import model.order.IngredientListResponse;

import static org.hamcrest.Matchers.equalTo;

public class OrderSteps {

    private OrderApi orderApi = new OrderApi();

    @Step("Создание заказа")
    public Response stepCreateOrder(CreateOrder order, String accessToken) {
        Response response = orderApi.createOrder(order, accessToken);
        return response;
    }

    @Step("Получить список ингредиентов")
    public IngredientListResponse stepGetListIngredient() {
        return orderApi.getIngredientList().then().statusCode(200).assertThat().body("success", equalTo(true)).extract().as(IngredientListResponse.class);
    }

    @Step("Получить список заказов пользователя")
    public Response stepGetOrdersList(String accessToken) {
        Response response = orderApi.getUserOrder(accessToken);
        return response;
    }
}
