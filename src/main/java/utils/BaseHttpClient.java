package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

public class BaseHttpClient {

    private final static RequestSpecification baseRequestSpec = new RequestSpecBuilder()
            .addHeader("Content-Type", "application/json")
            .addFilter(new RequestLoggingFilter())
            .addFilter(new ResponseLoggingFilter())
            .addFilter(new ErrorLoggingFilter())
            .build();

    public static RequestSpecification getBaseRequestSpec() {
        return baseRequestSpec;
    }

    public static RequestSpecification getBaseRequestSpecAuth(String accessToken) {
        return new RequestSpecBuilder()
                .addRequestSpecification(baseRequestSpec)
                .addHeader("Authorization",accessToken)
                .build();
    }
}
