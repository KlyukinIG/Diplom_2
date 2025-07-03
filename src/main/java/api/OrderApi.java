package api;

import io.restassured.response.Response;
import model.order.CreateOrder;

import static io.restassured.RestAssured.given;
import static utils.BaseHttpClient.getBaseRequestSpec;
import static utils.BaseHttpClient.getBaseRequestSpecAuth;

public class OrderApi {

    public Response createOrder(CreateOrder order,String accessToken) {
        return given()
                .spec(getBaseRequestSpecAuth(accessToken))
                .body(order)
                .when()
                .post("/api/orders");
    }

    public Response getUserOrder(String accessToken) {
        return given().spec(getBaseRequestSpecAuth(accessToken)).get("/api/orders");
    }

    public Response getIngredientList() {
        return given().spec(getBaseRequestSpec()).get("/api/ingredients");
    }
}
