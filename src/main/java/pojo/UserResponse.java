package pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private Integer code;
    private String type;
    private String message;
}
