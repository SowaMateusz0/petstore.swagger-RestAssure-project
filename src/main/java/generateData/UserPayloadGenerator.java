package generateData;

import com.github.javafaker.Faker;
import pojo.UserRequest;

public class UserPayloadGenerator {

    public Faker faker() {
        return Faker.instance();
    }

    public UserRequest generateUser(){

        UserRequest user = new UserRequest();
        user.setId(faker().number().numberBetween(1,10000));
        user.setUsername(faker().name().username());
        user.setFirstName(faker().name().firstName());
        user.setLastName(faker().name().lastName());
        user.setEmail(faker().internet().emailAddress());
        user.setPassword("SecretPassword123");
        user.setPhone(faker().phoneNumber().phoneNumber());
        user.setUserStatus(faker().number().numberBetween(0,1));
        return user;
    }

}
