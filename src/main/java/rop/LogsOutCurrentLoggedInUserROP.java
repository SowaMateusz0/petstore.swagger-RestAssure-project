package rop;

import org.apache.http.HttpStatus;
import pojo.UserResponse;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class LogsOutCurrentLoggedInUserROP extends BaseRequestObjectPattern<LogsOutCurrentLoggedInUserROP, UserResponse>{

    @Override
    protected Type responsePayload() {
        return UserResponse.class;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }

    @Override
    public LogsOutCurrentLoggedInUserROP sendRequestPayload() {
        response = given().when().get("user/logout");
        return this;
    }
}
