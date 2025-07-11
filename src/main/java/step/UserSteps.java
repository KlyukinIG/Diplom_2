package step;

import api.UserApi;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.client.CreateUser;
import model.client.LogOutUser;
import model.client.LoginUser;
import model.client.UpdateUser;

import static org.hamcrest.Matchers.equalTo;

public class UserSteps {

    private UserApi userApi = new UserApi();

    @Step("Создание пользователя")
    public Response stepCreateUser(CreateUser createUser) {
        Response response = userApi.createUser(createUser);
        return response;
    }

    @Step("Авторизация пользователя")
    public Response stepLoginUser(LoginUser user) {
        Response response = userApi.loginUser(user);
        return response;
    }

    @Step("Обновление параметров пользователя")
    public Response stepUpdateUser(UpdateUser user, String accessToken) {
        Response response = userApi.updateUser(user, accessToken);
        return response;
    }

    @Step("Выход из системы пользователя")
    public void stepUserLogOut(LogOutUser logOutUser) {
        userApi.logOutUser(logOutUser).then().statusCode(200).assertThat().body("message", equalTo("Successful logout"));
    }

    @Step("Удаление пользователя")
    public void stepDeleteUser(String accessToken) {
        userApi.deleteUser(accessToken).then().statusCode(202).assertThat().body("message", equalTo("User successfully removed"));
    }
}
