package userTests;

import BaseSuite.BaseClass;
import generateData.UserPayloadGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pojo.UserRequest;
import rop.CreateUserROP;
import rop.GetUserROP;

public class GetUserTests extends BaseClass {

    UserRequest userRequest;

    @BeforeTest
    public void beforeTest(){

        userRequest = new UserPayloadGenerator().generateUser();
        new CreateUserROP()
                .setUserPayload(userRequest)
                .sendRequestPayload()
                .assertRequestSuccess()
                .getResponsePayload();
    }

    @Test
    @Description("Validation of extracting an object ")
    @Severity(SeverityLevel.CRITICAL)
    public void givenUsernameWhenGetUserThenUserIsDisplayedTest(){

     UserRequest user = new GetUserROP()
             .setUsername(userRequest.getUsername())
             .sendRequestPayload()
             .assertRequestSuccess()
             .getResponsePayload();

        Assertions.assertThat(userRequest).usingRecursiveComparison().isEqualTo(user);

    }

}
