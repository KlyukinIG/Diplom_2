package tests;

import io.qameta.allure.Description;
import model.client.CreateUserResponse;
import model.order.CreateOrder;
import model.order.IngredientListResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.BaseTest;

import java.util.List;
import java.util.UUID;

public class CreateOrderTest extends BaseTest {

    @DisplayName("Создание заказа от авторизованного пользователя")
    @Description("В тесте проверяется ответ системы на создание заказа от авторизованного пользователя c ингредиентами")
    @Test
    public void createOrderWithIngredients() {
        response = userSteps().stepCreateUser(createUser);
        userSteps().stepLoginUser(loginUser);
        IngredientListResponse ingredients = orderSteps().stepGetListIngredient();
        List<String> ingredientsList = List.of(ingredients.getData().get(0).get_id(), ingredients.getData().get(1).get_id());
        CreateOrder order = new CreateOrder(ingredientsList);
        orderSteps().stepCreateOrder(order, response.getAccessToken());
    }

    @DisplayName("Создание заказа не авторизованного пользователя c ингредиентами")
    @Description("В тесте проверяется ответ системы на создание заказа от не авторизованного пользователя c ингредиентами")
    @Test
    public void createOrderWithoutAth() {
        response = userSteps().stepCreateUser(createUser);
        IngredientListResponse ingredients = orderSteps().stepGetListIngredient();
        List<String> ingredientsList = List.of(ingredients.getData().get(0).get_id(), ingredients.getData().get(1).get_id());
        CreateOrder order = new CreateOrder(ingredientsList);
        orderSteps().stepCreateOrder(order, "wrong_token");
    }

    @DisplayName("Создание заказа авторизованного пользователя без ингредиентов")
    @Description("В тесте проверяется ответ системы на создание заказа от авторизованного пользователя без ингредиентов")
    @Test
    public void createOrderWithoutIngredients() {
        response = userSteps().stepCreateUser(createUser);
        userSteps().stepLoginUser(loginUser);
        CreateOrder order = new CreateOrder();
        orderSteps().stepCreateOrder(order, response.getAccessToken());
    }

    @DisplayName("Создание заказа авторизованного пользователя с неверным хешом ингредиента")
    @Description("В тесте проверяется ответ системы на создание заказа от авторизованного пользователя с неверным хеш ")
    @Test
    public void createOrderWithWrongHashIngredients() {
        response = userSteps().stepCreateUser(createUser);
        String invalidIngredientId = UUID.randomUUID().toString().replace("-", "").substring(0, 24);
        CreateOrder order = new CreateOrder(List.of(invalidIngredientId));
        orderSteps().stepCreateOrder(order, response.getAccessToken());
    }

}
