package com.digi.helper;

import com.digi.enums.Role;
import com.digi.dto.UserDTO;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;


public class UserHelper {


    private static final String URL = "http://localhost:9090/user";

    public static Response createUser(UserDTO userDTO){
        Response post = RestAssured
                .given()
                .header("Content-type","application/json")
                .and()
                .body(userDTO)
                .when()
                .post(URL)
                .then()
                .extract().response();

        return post;
    }

    public static Response deleteUser(Integer userId,String accessToken){
        Response delete = RestAssured
                .given()
                .header("Authorization","Basic "+accessToken)
                .and()
                .param("id",userId)
                .when()
                .delete(URL)
                .then()
                .extract().response();

        return delete;
    }

    public static Response getAllUser(Map<String,String> params){
        Response get = RestAssured
                .given()
                .header("Content-type","application/json")
                .and()
                .params(params)
                .when()
                .get(URL+"/get-all")
                .then()
                .and()
                .extract().response();

        return get;
    }

    public static Response getByEmail(String username,String accessToken){
        Response get = RestAssured
                .given()
                .header("Content-type","application/json")
                .header("Authorization","Basic "+accessToken)
                .and()
                .param("email",username)
                .when()
                .get(URL)
                .then()
                .and()
                .extract().response();

        return get;
    }

    public static UserDTO testUser(){
        return UserDTO.builder()
                .name("Ara")
                .surname("Aramyan")
                .year(1989)
                .email("ara@test.com")
                .password("123456Ara")
                .role(Role.ADMIN)
                .build();
    }
}
