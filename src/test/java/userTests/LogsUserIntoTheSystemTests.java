package userTests;

import BaseSuite.BaseClass;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import pojo.UserResponse;
import rop.LogsUserIntoTheSystemROP;


public class LogsUserIntoTheSystemTests extends BaseClass {

    @Test
    @Description("Validate success of login")
    @Severity(SeverityLevel.CRITICAL)
    public void givenLoginAndPasswordWhenLoginThenUserIsLoggedIn() {

        UserResponse actualResponse = new LogsUserIntoTheSystemROP()
                .setUsername("login")
                .setPassword("password")
                .sendRequestPayload()
                .assertRequestSuccess()
                .getResponsePayload();

        UserResponse expectedResponse = new UserResponse();
        expectedResponse.setCode(HttpStatus.SC_OK);
        expectedResponse.setType("unknown");


        Assertions.assertThat(actualResponse).extracting("code","type").containsExactly(200,"unknown");
        Assertions.assertThat(actualResponse.getMessage()).matches("logged in user session:\\d{13}");

    }
}
