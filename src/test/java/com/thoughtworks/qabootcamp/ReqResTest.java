package com.thoughtworks.qabootcamp;

import com.thoughtworks.qabootcamp.pojo.UserPojo;
import com.thoughtworks.qabootcamp.utils.Routes;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ReqResTest extends BaseTest {
    Response response;
    @Test(dataProvider = "createUserData")
    public void test1_CreateUser(String name, String job){
        UserPojo user = new UserPojo(name,job);
        response = request.body(user)
                .when().post(Routes.CREATE_USER)
                .then().extract().response();
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_CREATED);
        Assert.assertEquals(response.jsonPath().get("name"),name);
        Assert.assertEquals(response.jsonPath().get("job"),job);
        testContext.setProperty(response.jsonPath().get("name")+"_"+response.jsonPath().get("job"),response.jsonPath().get("id"));
    }

    @Test(dataProvider = "updateUserData")
    public void test2_UpdateUser(String name, String job){
        UserPojo user = new UserPojo(name, job);
        response  = request.body(user)
                .when().put(String.format(Routes.UPDATE_USER,testContext.getProperty(response.jsonPath().get("name")+"_"+response.jsonPath().get("job"))))
                .then().extract().response();
        Assert.assertEquals(response.statusCode(),HttpStatus.SC_OK);
        Assert.assertEquals(response.jsonPath().get("name"),name);
    }

    @Test
    public void test3_DeleteUser(){
        response = request
                .when().delete(String.format(Routes.DELETE_USER,response.jsonPath().get("name")+"_"+response.jsonPath().get("job")))
                .then().extract().response();
        Assert.assertEquals(response.statusCode(),HttpStatus.SC_NO_CONTENT);
    }

    @DataProvider
    public Object[][] createUserData(){
        return new Object[][]{
                {
                    "James","Manager"
                },
                {
                  "Thomas", "Lead"
                }
        };
    }

    @DataProvider
    public Object[][] updateUserData(){
        return new Object[][]{
                {"James", "Senior Manager"},
                {"Thomas Hudson", "Lead"}
        };
    }
}
