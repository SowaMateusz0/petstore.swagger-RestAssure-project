package userTests;

import BaseSuite.BaseClass;
import generateData.UserPayloadGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pojo.UserRequest;
import pojo.UserResponse;
import rop.CreateUserROP;
import rop.DeleteUserROP;

public class CreateUserTests extends BaseClass {


    private UserRequest userRequest;

    @Test
    @Description("Validation of user creation")
    @Severity(SeverityLevel.CRITICAL)
    public void givenUserPayloadRequestWhenPostUserThenUserIsCreatedTest(){

        userRequest = new UserPayloadGenerator().generateUser();

        UserResponse actualUserResponse = new CreateUserROP()
                .setUserPayload(userRequest)
                .sendRequestPayload()
                .assertRequestSuccess()
                .getResponsePayload();

        UserResponse expectedUserResponse = new UserResponse();
        expectedUserResponse.setCode(HttpStatus.SC_OK);
        expectedUserResponse.setType("unknown");
        expectedUserResponse.setMessage(Integer.toString(userRequest.getId()));

        Assertions.assertThat(actualUserResponse).usingRecursiveComparison().isEqualTo(expectedUserResponse);
    }

    @AfterTest
    public void cleanUp(){

        UserResponse userResponse = new DeleteUserROP()
                .setUsername(userRequest.getUsername())
                .sendRequestPayload()
                .assertRequestSuccess()
                .getResponsePayload();

        UserResponse expectedResponse = new UserResponse();
        expectedResponse.setCode(HttpStatus.SC_OK);
        expectedResponse.setType("unknown");
        expectedResponse.setMessage(userRequest.getUsername());

    Assertions.assertThat(userResponse).usingRecursiveComparison().isEqualTo(expectedResponse);
    }

}
