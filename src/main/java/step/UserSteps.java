package step;

import api.UserApi;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.client.*;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

public class UserSteps {

    private UserApi userApi = new UserApi();

    @Step("Создание пользователя")
    public CreateUserResponse stepCreateUser(CreateUser createUser) {
        Response response = userApi.createUser(createUser);
        int statusCode = response.statusCode();
        if (statusCode == 200) {
            return response.then().statusCode(200).assertThat()
                    .body("user.email", equalTo(createUser.getEmail())).extract().as(CreateUserResponse.class);
        } else if (statusCode == 403) {
            response.then().statusCode(403).assertThat()
                    .body("message", anyOf(equalTo("User already exists"), equalTo("Email, password and name are required fields")));
            return null;
        } else {
            throw new RuntimeException("Код ответа неописан в документации....");
        }
    }

    @Step("Авторизация пользователя")
    public void stepLoginUser(LoginUser user) {
        Response response = userApi.loginUser(user);
        int statusCode = response.statusCode();
        if (statusCode == 200) {
            response.then().statusCode(200).assertThat().body("user.email", equalTo(user.getEmail())).extract().as(LoginUserResponse.class);
        } else if (statusCode == 401) {
            response.then().statusCode(401).assertThat().body("message", equalTo("email or password are incorrect"));
        } else {
            throw new RuntimeException("Код ответа неописан в документации....");
        }
    }

    @Step("Обновление параметров пользователя")
    public void stepUpdateUser(UpdateUser user, String accessToken) {
        Response response = userApi.updateUser(user, accessToken);
        int statusCode = response.statusCode();
        if (statusCode == 200) {
            response.then().statusCode(200).assertThat().body("user.email", equalTo(user.getEmail()));
        } else if (statusCode == 401) {
            response.then().statusCode(401).assertThat().body("message", equalTo("You should be authorised"));
        } else if (statusCode == 403) {
            response.then().statusCode(403).assertThat().body("message", equalTo("User with such email already exists"));
        } else {
            throw new RuntimeException("Код ответа неописан в документации....");
        }
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
