package step;

import api.OrderApi;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.order.CreateOrder;
import model.order.IngredientListResponse;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

public class OrderSteps {

    private OrderApi orderApi = new OrderApi();

    @Step("Создание заказа")
    public void stepCreateOrder(CreateOrder order, String accessToken) {
        Response response = orderApi.createOrder(order, accessToken);
        int statusCode = response.statusCode();
        if (statusCode == 200) {
            response.then().statusCode(200).assertThat().body("success", equalTo(true));
        } else if (statusCode == 500) {
            response.then().statusCode(500);
        } else if (statusCode == 400) {
            response.then().statusCode(400).assertThat().body("message", anyOf(equalTo("Ingredient ids must be provided"), equalTo("One or more ids provided are incorrect")));
        } else if (statusCode == 401) {
            response.then().statusCode(400).assertThat().body("message", equalTo("You should be authorised"));
        } else {
            throw new RuntimeException("Код ответа неописан в документации....");
        }
    }

    @Step("Получить список ингредиентов")
    public IngredientListResponse stepGetListIngredient() {
        return orderApi.getIngredientList().then().statusCode(200).assertThat().body("success", equalTo(true)).extract().as(IngredientListResponse.class);
    }

    @Step("Получить список заказов пользователя")
    public void stepGetOrdersList(String accessToken) {
        Response response = orderApi.getUserOrder(accessToken);
        int statusCode = response.statusCode();
        if(statusCode == 200) {
            response.then().statusCode(200).assertThat().body("success", equalTo(true));
        } else if (statusCode == 401) {
            response.then().statusCode(401).assertThat().body("message", equalTo("You should be authorised"));
        }
    }
}
