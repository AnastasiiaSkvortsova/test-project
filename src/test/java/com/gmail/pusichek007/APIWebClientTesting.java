package com.gmail.pusichek007;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class APIWebClientTesting {

    ToDoItemModel toDoItemModel;
    ToDoItemModel result;
    ToDoItemModel newItem;

    public static final String toDoItemName = "washYourDish";
    public static final String toDoNewItemName = "cookYourLunch";
    public static final boolean toDoItemStatus = false;
    public static final boolean toDoNewItemStatus = true;

    public static final String APIRequest = "http://awesometodoapp.azurewebsites.net/api/toDo/";

    @Test (priority = 1)
    public void pingTest_GetAllToDoItems_Success(){
        given().when().get(APIRequest).then().statusCode(200);
    }

    @Test (priority = 2)
    public void getInvalidToDoItem_GetError(){
        given().when().get(APIRequest+555).then().statusCode(404);
    }

    //@Test (priority = 3)
    //put item without required parameter name
    //public void postNewToDoItemWithoutName_GetError(){
       // toDoItemModel = new ToDoItemModel("",);
       // given().contentType(ContentType.JSON)
               // .body(toDoItemModel)
                //.when()
                //.post(APIRequest)
                //.then()
                //.statusCode(400);
    //}

    @Test (priority = 4)
    //post new ToDoItem
    public void postNextToDoItem_Success(){
        toDoItemModel = new ToDoItemModel(toDoItemName, toDoItemStatus);
        result = given().contentType(ContentType.JSON)
                .body(toDoItemModel)
                .when()
                .post(APIRequest)
                .as(ToDoItemModel.class);
        Assert.assertEquals(result.name, toDoItemName);
        Assert.assertTrue(result.isComplete == toDoItemStatus);
    }

    @Test (priority = 5)
    //get created ToDOItem with posted Id
    public void getExistingToDoItem_Success(){

        try {
                given()
                .when()
                .get(APIRequest + result.id)
                .then()
                .statusCode(200);
            Assert.assertEquals(result.name, toDoItemName);
            Assert.assertEquals(result.isComplete, toDoItemStatus);
        }
        catch (NullPointerException e){
            System.out.println("ALERT!!! The element id is null - 5 test");
        }
    }

    @Test (priority = 6)
    //put same item with different name
    public void putNewNamedToDoItem_Success(){

        try {
            toDoItemModel = new ToDoItemModel(result.id, toDoNewItemName, toDoNewItemStatus);

            given().contentType(ContentType.JSON)
                    .body(toDoItemModel)
                    .when()
                    .put(APIRequest + result.id)
                    .then().statusCode(204);
        }
        catch (NullPointerException e){
            System.out.println("ALERT!!! The element id is null - 6 test");
        }
    }

    @Test (priority = 7)
    //get updated item
    public void getUpdatedToDoItem_Success(){

        try {
            result = given()
                    .when()
                    .get(APIRequest + result.id)
                    .as(ToDoItemModel.class);
            Assert.assertEquals(result.name, toDoNewItemName);
            Assert.assertEquals(result.isComplete, toDoNewItemStatus);
        }
        catch (NullPointerException e){
            System.out.println("ALERT!!! The element id is null - 7 test");
        }
    }

    @Test (priority = 8)
    //delete item
    public void deleteToDoItem_Success(){

        try {
            given().when().delete(APIRequest + result.id).then().assertThat().statusCode(204);
        }
        catch (NullPointerException e){
            System.out.println("ALERT!!! The element id is null - 8 test");
        }
    }

    @Test (priority = 9)
    //try to get deleted item
    public void getDeletedToDoItem_GetError(){

        try {
            given().when().get(APIRequest + result.id).then().statusCode(404);
        }
        catch (NullPointerException e){
            System.out.println("ALERT!!! The element id is null - 9 test");
        }
    }

}
