package com.digi.tests;

import com.digi.dto.UserDTO;
import com.digi.helper.AuthorizationHelper;
import com.digi.model.UserEntity;
import com.digi.helper.UserHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.Base64;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTests {

    private ObjectMapper objectMapper;

    private String accessTokenAllPermission;

    private UserEntity userEntity;

    @BeforeAll
    @Test
    void beforeAll() throws IOException {
        objectMapper = new ObjectMapper();
        UserDTO client = AuthorizationHelper.createClient();
        Response response = UserHelper.createUser(client);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED.value());

        userEntity = objectMapper.readValue(response.asByteArray(), UserEntity.class);
        accessTokenAllPermission = Base64.getUrlEncoder()
                .encodeToString((client.getEmail() + ":" + client.getPassword()).getBytes());

    }

    @AfterAll
    void afterAll() {
        Response response = UserHelper.deleteUser(userEntity.getId(), accessTokenAllPermission);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT.value());
    }


    @Test
    void createUser() throws IOException {
        UserDTO userDTO = UserHelper.testUser();
        Response response = UserHelper.createUser(userDTO);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED.value());


        UserEntity userEntity = objectMapper.readValue(response.asByteArray(), UserEntity.class);

        Assertions.assertEquals(userEntity.getName(), userDTO.getName());
        Assertions.assertEquals(userEntity.getEmail(), userDTO.getEmail());
        Assertions.assertEquals(userEntity.getYear(), userDTO.getYear());
        Assertions.assertEquals(userEntity.getEmail(), userDTO.getEmail());

        response = UserHelper.createUser(userDTO);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CONFLICT.value());

        response = UserHelper.getByEmail(userDTO.getEmail(), accessTokenAllPermission);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK.value());

        response = UserHelper.deleteUser(userEntity.getId(), accessTokenAllPermission);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT.value());

    }

    @Test
    void getUserByUserName() {
        Response response = UserHelper.getByEmail("oneone@mail.ru",accessTokenAllPermission);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND.value());


        //Response response = UserHelper.getByEmail(userEntity.getEmail(), accessTokenAllPermission);
    }
}
