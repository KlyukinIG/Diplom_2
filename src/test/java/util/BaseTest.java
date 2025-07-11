package util;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.client.CreateUser;
import model.client.CreateUserResponse;
import model.client.LoginUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import step.OrderSteps;
import step.UserSteps;

public class BaseTest {

    protected CreateUser createUser;
    protected LoginUser loginUser;
    protected String accessToken;
    protected Response response;

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        createUser = new CreateUser("test-" + (int) (Math.random() * 10000) + "@yandex.ru", "123123123", "Semenich");
        loginUser = new LoginUser(createUser.getEmail(), createUser.getPassword());
    }

    @AfterEach
    public void tearDown() {
        try {
            accessToken = response.path("accessToken");
            if (accessToken != null) {
                userSteps().stepDeleteUser(accessToken);
            }
        } catch (RuntimeException e) {
            System.out.println("Удаление пользователя не прошло " + e.getMessage());
            e.getStackTrace();
        }
    }

    //init steps
    protected UserSteps userSteps() {
        return new UserSteps();
    }

    protected OrderSteps orderSteps() {
        return new OrderSteps();
    }

}
