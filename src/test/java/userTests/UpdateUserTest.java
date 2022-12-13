package userTests;

import BaseSuite.BaseClass;
import generateData.UserPayloadGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pojo.UserRequest;
import pojo.UserResponse;
import rop.CreateUserROP;
import rop.DeleteUserROP;
import rop.UpdateUserROP;

public class UpdateUserTest extends BaseClass {

    UserRequest userBeforeUpdate;
    UserResponse userResponseBeforeUpdate;
    UserRequest userAfterUpdate;

    @BeforeTest
    public void beforeTest(){

        userBeforeUpdate = new UserPayloadGenerator().generateUser();
        userResponseBeforeUpdate = new CreateUserROP()
                .setUserPayload(userBeforeUpdate)
                .sendRequestPayload()
                .assertRequestSuccess()
                .getResponsePayload();
    }

    @Test
    @Description("Validation of user update")
    @Severity(SeverityLevel.CRITICAL)
    public void givenUserPayloadRequestWhenUpdateUserThenUserIsUpdatedTest(){

        userAfterUpdate = new UserPayloadGenerator().generateUser();

        UserResponse userResponseAfterUpdate = new UpdateUserROP()
                .setUserPayload(userAfterUpdate)
                .setUsername(userBeforeUpdate.getUsername())
                .sendRequestPayload()
                .assertRequestSuccess()
                .getResponsePayload();

        UserResponse expectedUserResponse = new UserResponse();
        expectedUserResponse.setCode(HttpStatus.SC_OK);
        expectedUserResponse.setType("unknown");
        expectedUserResponse.setMessage(Integer.toString(userAfterUpdate.getId()));

        System.out.println(userResponseAfterUpdate.getCode());
        System.out.println(userResponseAfterUpdate.getMessage());
        System.out.println(userResponseAfterUpdate.getType());

        Assertions.assertThat(userResponseAfterUpdate)
                .usingRecursiveComparison().isEqualTo(expectedUserResponse);
    }

    @AfterTest
    public void cleanUp(){

        UserResponse userResponse = new DeleteUserROP()
                .setUsername(userAfterUpdate.getUsername())
                .sendRequestPayload()
                .assertRequestSuccess()
                .getResponsePayload();

        UserResponse expectedResponse = new UserResponse();
        expectedResponse.setCode(HttpStatus.SC_OK);
        expectedResponse.setType("unknown");
        expectedResponse.setMessage(userAfterUpdate.getUsername());

        Assertions.assertThat(userResponse).usingRecursiveComparison().isEqualTo(expectedResponse);
    }
}
