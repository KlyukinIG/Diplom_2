package model.client;

public class LoginUserResponse {

    private String success;
    private String accessToken;
    private String refreshToken;
    private LoginUser user;

    public String getSuccess() {
        return success;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public LoginUser getUser() {
        return user;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public void setUser(LoginUser user) {
        this.user = user;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
