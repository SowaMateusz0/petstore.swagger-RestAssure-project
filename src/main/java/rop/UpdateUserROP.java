package rop;


import ConfigRequest.RequestConfiguration;
import org.apache.http.HttpStatus;
import pojo.UserRequest;
import pojo.UserResponse;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class UpdateUserROP extends BaseRequestObjectPattern<UpdateUserROP, UserResponse> {


    private String username;
    private UserRequest userRequest;

    @Override
    protected Type responsePayload() {
        return UserResponse.class;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }

    @Override
    public UpdateUserROP sendRequestPayload() {
        response = given()
                .spec(RequestConfiguration.getConfigRequest())
                .body(userRequest)
                .when().put("user/{username}",username);
        return this;
    }

    public UpdateUserROP setUsername(String username){
        this.username = username;
        return this;
    }

    public UpdateUserROP setUserPayload(UserRequest userRequest){
        this.userRequest = userRequest;
        return this;
    }
}
