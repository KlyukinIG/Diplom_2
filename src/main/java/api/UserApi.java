package api;

import io.restassured.response.Response;
import model.client.LogOutUser;
import model.client.LoginUser;
import model.client.UpdateUser;
import model.client.CreateUser;

import static io.restassured.RestAssured.given;
import static utils.BaseHttpClient.getBaseRequestSpec;
import static utils.BaseHttpClient.getBaseRequestSpecAuth;

public class UserApi {

    public Response createUser(CreateUser createUser) {
        return given()
                .spec(getBaseRequestSpec())
                .body(createUser)
                .when()
                .post("/api/auth/register");
    }

    public Response loginUser(LoginUser user) {
        return given()
                .spec(getBaseRequestSpec())
                .body(user)
                .when()
                .post("/api/auth/login");
    }

    public Response updateUser(UpdateUser user, String accessToken) {
        return given()
                .spec(getBaseRequestSpecAuth(accessToken))
                .body(user)
                .when()
                .patch("/api/auth/user");
    }

    public Response logOutUser(LogOutUser logOutUser) {
        return given()
                .spec(getBaseRequestSpec())
                .body(logOutUser)
                .when()
                .patch("/api/auth/logout");
    }

    public Response deleteUser(String token) {
        return given()
                .spec(getBaseRequestSpec())
                .header("Authorization", token)
                .delete("/api/auth/user");
    }


}
