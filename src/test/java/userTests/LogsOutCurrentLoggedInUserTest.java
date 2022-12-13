package userTests;

import BaseSuite.BaseClass;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pojo.UserResponse;
import rop.LogsOutCurrentLoggedInUserROP;
import rop.LogsUserIntoTheSystemROP;

public class LogsOutCurrentLoggedInUserTest extends BaseClass {


    @Test
    @Description("Validate success of logout")
    @Severity(SeverityLevel.CRITICAL)
    public void given_WhenLogoutThenUserIsLogout() {

        UserResponse actualResponse = new LogsOutCurrentLoggedInUserROP()
                .sendRequestPayload()
                .assertRequestSuccess()
                .getResponsePayload();

        UserResponse expectedResponse = new UserResponse();
        expectedResponse.setCode(HttpStatus.SC_OK);
        expectedResponse.setType("unknown");
        expectedResponse.setMessage("ok");


        Assertions.assertThat(actualResponse).usingRecursiveComparison().isEqualTo(expectedResponse);

    }
}
