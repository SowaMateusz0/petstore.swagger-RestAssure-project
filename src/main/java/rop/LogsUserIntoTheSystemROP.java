package rop;

import org.apache.http.HttpStatus;
import pojo.UserResponse;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class LogsUserIntoTheSystemROP extends BaseRequestObjectPattern<LogsUserIntoTheSystemROP, UserResponse>{

    private String username;
    private String password;

    @Override
    protected Type responsePayload() {
        return UserResponse.class;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }

    @Override
    public LogsUserIntoTheSystemROP sendRequestPayload() {

        response = given()
                    .queryParam("username",username)
                    .queryParam("password ",password)
                .when()
                    .get("user/login");
        return this;
    }

    public LogsUserIntoTheSystemROP setUsername(String username) {
        this.username = username;
        return this;
    }

    public LogsUserIntoTheSystemROP setPassword(String password) {
        this.password = password;
        return this;
    }
}
