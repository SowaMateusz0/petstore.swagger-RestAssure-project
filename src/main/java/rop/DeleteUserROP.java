package rop;

import ConfigRequest.RequestConfiguration;
import org.apache.http.HttpStatus;
import pojo.UserResponse;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class DeleteUserROP extends BaseRequestObjectPattern<DeleteUserROP, UserResponse> {

    private String username;

    @Override
    protected Type responsePayload() {
        return UserResponse.class;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }

    @Override
    public DeleteUserROP sendRequestPayload() {
        response = given().spec(RequestConfiguration.getConfigRequest())
                .when().delete("user/{username}", username);
        return this;
    }

    public DeleteUserROP setUsername(String username) {
        this.username = username;
        return this;
    }
}
