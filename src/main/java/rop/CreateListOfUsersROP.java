package rop;

import ConfigRequest.RequestConfiguration;
import org.apache.http.HttpStatus;
import pojo.UserRequest;
import pojo.UserResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class CreateListOfUsersROP extends BaseRequestObjectPattern<CreateListOfUsersROP, UserResponse> {

    private ArrayList<UserRequest> users;

    @Override
    protected Type responsePayload() {
        return UserResponse.class;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }

    @Override
    public CreateListOfUsersROP sendRequestPayload() {

        response = given()
                    .spec(RequestConfiguration.getConfigRequest())
                    .body(users)
                .when()
                    .post("user/createWithList");
        return this;
    }

    public CreateListOfUsersROP setUsersPayload(ArrayList<UserRequest> users){
        this.users = users;
        return this;
    }
}
