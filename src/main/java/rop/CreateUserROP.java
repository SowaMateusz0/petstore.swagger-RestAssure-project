package rop;

import ConfigRequest.RequestConfiguration;
import org.apache.http.HttpStatus;
import pojo.UserRequest;
import pojo.UserResponse;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class CreateUserROP extends BaseRequestObjectPattern<CreateUserROP, UserResponse> {

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
    public CreateUserROP sendRequestPayload() {

        response = given()
                    .spec(RequestConfiguration.getConfigRequest())
                    .body(userRequest)
                .when()
                    .post("user");
        return this;
    }

    public CreateUserROP setUserPayload(UserRequest userRequest){
        this.userRequest = userRequest;
        return this;
    }
}
