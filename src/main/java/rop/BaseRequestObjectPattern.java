package rop;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

import java.lang.reflect.Type;

public abstract class BaseRequestObjectPattern<E,M>{

    protected Response response;
    protected abstract Type responsePayload();
    protected abstract int getSuccessStatusCode();
    public abstract E sendRequestPayload();

    public M getResponsePayload(){
        return response.as(responsePayload());
    }

    public E assertStatusCode(int statusCode){
        Assertions.assertThat(response.getStatusCode()).isEqualTo(statusCode);
        return (E) this;
    }

    public E assertRequestSuccess(){
        return assertStatusCode(getSuccessStatusCode());
    }


}
