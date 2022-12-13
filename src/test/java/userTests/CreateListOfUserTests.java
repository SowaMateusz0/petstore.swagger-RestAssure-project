package userTests;

import BaseSuite.BaseClass;
import generateData.UserPayloadGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pojo.UserRequest;
import pojo.UserResponse;
import rop.CreateListOfUsersROP;
import rop.DeleteUserROP;

import java.util.ArrayList;

public class CreateListOfUserTests extends BaseClass {

    private UserRequest firstUserRequest;
    private UserRequest secondUserRequest;

    @Test
    @Description("Validation of user list creation")
    @Severity(SeverityLevel.CRITICAL)
    public void givenUserListPayloadRequestWhenPostUserListThenUsersAreCreatedTest(){

        firstUserRequest= new UserPayloadGenerator().generateUser();
        secondUserRequest = new UserPayloadGenerator().generateUser();

        ArrayList<UserRequest> usersList = new ArrayList<>();
        usersList.add(firstUserRequest);
        usersList.add(secondUserRequest);

        UserResponse actualUserResponse = new CreateListOfUsersROP()
                .setUsersPayload(usersList)
                .sendRequestPayload()
                .assertRequestSuccess()
                .getResponsePayload();

        UserResponse expectedUserResponse = new UserResponse();
        expectedUserResponse.setCode(HttpStatus.SC_OK);
        expectedUserResponse.setType("unknown");
        expectedUserResponse.setMessage("ok");

        Assertions.assertThat(actualUserResponse).usingRecursiveComparison().isEqualTo(expectedUserResponse);
    }

    @AfterTest
    public void cleanUp(){

        UserResponse firstUserResponse = new DeleteUserROP()
                .setUsername(firstUserRequest.getUsername())
                .sendRequestPayload()
                .assertRequestSuccess()
                .getResponsePayload();

        UserResponse expectedFirstUserResponse = new UserResponse();
        expectedFirstUserResponse.setCode(HttpStatus.SC_OK);
        expectedFirstUserResponse.setType("unknown");
        expectedFirstUserResponse.setMessage(firstUserRequest.getUsername());

        UserResponse secondUserResponse = new DeleteUserROP()
                .setUsername(secondUserRequest.getUsername())
                .sendRequestPayload()
                .assertRequestSuccess()
                .getResponsePayload();

        UserResponse expectedSecondResponse = new UserResponse();
        expectedSecondResponse.setCode(HttpStatus.SC_OK);
        expectedSecondResponse.setType("unknown");
        expectedSecondResponse.setMessage(secondUserRequest.getUsername());

        Assertions.assertThat(firstUserResponse).usingRecursiveComparison().isEqualTo(expectedFirstUserResponse);
        Assertions.assertThat(secondUserResponse).usingRecursiveComparison().isEqualTo(expectedSecondResponse);

    }
}
