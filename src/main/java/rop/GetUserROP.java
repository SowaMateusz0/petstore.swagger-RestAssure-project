package rop;

import ConfigRequest.RequestConfiguration;
import org.apache.http.HttpStatus;
import pojo.UserRequest;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class GetUserROP extends BaseRequestObjectPattern<GetUserROP, UserRequest> {

    private String username;

    @Override
    protected Type responsePayload() {
        return UserRequest.class;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }

    @Override
    public GetUserROP sendRequestPayload() {
        response = given()
                    .spec(RequestConfiguration.getConfigRequest())
                    .pathParam("username",username)
                .when()
                    .get("user/{username}");

        return this;
    }

    public GetUserROP setUsername(String username){
        this.username = username;
        return this;
    }
}
