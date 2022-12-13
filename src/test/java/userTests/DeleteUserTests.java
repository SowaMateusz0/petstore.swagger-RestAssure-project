package userTests;

import BaseSuite.BaseClass;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import rop.DeleteUserROP;

public class DeleteUserTests extends BaseClass {

    private String noExistingUsername;

    @BeforeMethod
    public void beforeTest(){
        noExistingUsername = new Faker().name().username();
        new DeleteUserROP().setUsername(noExistingUsername).sendRequestPayload();
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Validation of removing no-existing user")
    @Test
    public void givenNoExistingUserWhenDeletingUserThenUserNotFoundTest() {
        new DeleteUserROP()
                .setUsername(noExistingUsername)
                .sendRequestPayload()
                .assertStatusCode(HttpStatus.SC_NOT_FOUND);
    }

}
