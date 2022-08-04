package com.thoughtworks.qabootcamp;

import com.thoughtworks.qabootcamp.context.TestContext;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    RequestSpecification request;
    TestContext testContext;

    @BeforeTest
    public void setUp(){
        RestAssured.baseURI = "https://reqres.in/";
        request = RestAssured.given().header("Content-Type","application/json");
        testContext = TestContext.getInstance();
    }
}
