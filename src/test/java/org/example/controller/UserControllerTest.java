package org.example.controller;

import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.util.List;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.example.dto.request.UserRequestDto;
import org.example.model.User;
import org.example.service.UserService;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {
    private static final int OK = HttpStatus.OK.value();
    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService userService;

    @BeforeEach
    void beforeEach() {
        RestAssuredMockMvc.mockMvc(mvc);
    }

    @Test
    void create_is_ok() {
        User user = new User(1L, "task1@gmail.com",
                "Igor", "Shevchenko",
                LocalDate.of(1994, 3, 21),
                "Shevchenka 1", "+380684572457");
        Mockito.when(userService.create(any(User.class))).thenReturn(user);
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(toDto(user))
                .when()
                .post("/users")
                .then()
                .statusCode(OK)
                .body("id", Matchers.equalTo(1))
                .body("email", Matchers.equalTo("task1@gmail.com"))
                .body("firstName", Matchers.equalTo("Igor"))
                .body("lastName", Matchers.equalTo("Shevchenko"))
                .body("birthDate", Matchers.equalTo("1994-03-21"))
                .body("address", Matchers.equalTo("Shevchenka 1"))
                .body("phone", Matchers.equalTo("+380684572457"));
    }

    @Test
    void update_is_ok() {
        User user = new User(1L, "task1@gmail.com",
                "Igor", "Shevchenko",
                LocalDate.of(1994, 3, 21),
                "Shevchenka 1", "+380684572457");
        User updateUser = new User(1L, "newuser@gmail.com",
                "Oleksii", "Zinkevich",
                LocalDate.of(2000, 1, 1),
                "location 2", "+380984172568");
        Mockito.when(userService.getById(user.getId())).thenReturn(user);
        Mockito.when(userService.update(any(User.class))).thenReturn(updateUser);
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(toDto(updateUser))
                .when()
                .put("/users/" + user.getId())
                .then()
                .statusCode(OK)
                .body("id", Matchers.equalTo(1))
                .body("email", Matchers.equalTo("newuser@gmail.com"))
                .body("firstName", Matchers.equalTo("Oleksii"))
                .body("lastName", Matchers.equalTo("Zinkevich"))
                .body("birthDate", Matchers.equalTo("2000-01-01"))
                .body("address", Matchers.equalTo("location 2"))
                .body("phone", Matchers.equalTo("+380984172568"));
    }

    @Test
    void patch_is_ok() {
        User user = new User(1L, "task1@gmail.com",
                "Igor", "Shevchenko",
                LocalDate.of(1994, 3, 21),
                "Shevchenka 1", "+380684572457");
        User updateUser = new User(1L, "newuser@gmail.com",
                "Oleksii", "Zinkevich",
                LocalDate.of(2000, 1, 1),
                "location 2", "+380984172568");
        Mockito.when(userService.getById(user.getId())).thenReturn(user);
        Mockito.when(userService.update(any(User.class))).thenReturn(updateUser);
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(toDto(updateUser))
                .when()
                .patch("/users/" + user.getId())
                .then()
                .statusCode(OK)
                .body("id", Matchers.equalTo(1))
                .body("email", Matchers.equalTo("newuser@gmail.com"))
                .body("birthDate", Matchers.equalTo("2000-01-01"));
    }

    @Test
    void delete_is_ok() {
        User user = new User(1L, "task1@gmail.com",
                "Igor", "Shevchenko",
                LocalDate.of(1994, 3, 21),
                "Shevchenka 1", "+380684572457");
        Mockito.when(userService.getById(user.getId())).thenReturn(user);
        RestAssuredMockMvc.when()
                .delete("/users/" + user.getId())
                .then()
                .statusCode(OK);
    }

    @Test
    void findUsersBetweenBirthDate_is_ok() {
        LocalDate before = LocalDate.of(1994, 1, 1);
        LocalDate after = LocalDate.of(2000, 1, 1);
        List<User> users = List.of(new User(1L, "newuser@gmail.com",
                "Igor", "Shevchenko",
                LocalDate.of(1994, 3, 21),
                "Shevchenka 1", "+380684572457"));
        Mockito.when(userService.getByBirthDate(before, after)).thenReturn(users);
        RestAssuredMockMvc.given()
                .queryParam("before", "01.01.1994")
                .queryParam("after", "01.01.2000")
                .when()
                .get("/users/by-birthday")
                .then()
                .statusCode(OK)
                .body("size()", Matchers.equalTo(1))
                .body("[0].id", Matchers.equalTo(1))
                .body("[0].email", Matchers.equalTo("newuser@gmail.com"))
                .body("[0].firstName", Matchers.equalTo("Igor"))
                .body("[0].lastName", Matchers.equalTo("Shevchenko"))
                .body("[0].birthDate", Matchers.equalTo("1994-03-21"))
                .body("[0].address", Matchers.equalTo("Shevchenka 1"))
                .body("[0].phone", Matchers.equalTo("+380684572457"));
    }

    @Test
    void getAll_is_ok() {
        List<User> users = List.of(new User(1L, "newuser@gmail.com",
                "Igor", "Shevchenko",
                LocalDate.of(1994, 3, 21),
                "Shevchenka 1", "+380684572457"), new User(2L, "newuser2@gmail.com",
                "Petro", "Bamper",
                LocalDate.of(2002, 1, 13),
                "Location 2", "+380111111111"));
        Mockito.when(userService.getAll()).thenReturn(users);
        RestAssuredMockMvc.given()
                .when()
                .get("/users")
                .then()
                .statusCode(OK)
                .body("size()", Matchers.equalTo(2))
                .body("[0].id", Matchers.equalTo(1))
                .body("[0].email", Matchers.equalTo("newuser@gmail.com"))
                .body("[0].firstName", Matchers.equalTo("Igor"))
                .body("[0].lastName", Matchers.equalTo("Shevchenko"))
                .body("[0].birthDate", Matchers.equalTo("1994-03-21"))
                .body("[0].address", Matchers.equalTo("Shevchenka 1"))
                .body("[0].phone", Matchers.equalTo("+380684572457"))
                .body("[1].id", Matchers.equalTo(2))
                .body("[1].email", Matchers.equalTo("newuser2@gmail.com"))
                .body("[1].firstName", Matchers.equalTo("Petro"))
                .body("[1].lastName", Matchers.equalTo("Bamper"))
                .body("[1].birthDate", Matchers.equalTo("2002-01-13"))
                .body("[1].address", Matchers.equalTo("Location 2"))
                .body("[1].phone", Matchers.equalTo("+380111111111"));
    }

    private UserRequestDto toDto(User user) {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setEmail(user.getEmail());
        userRequestDto.setFirstName(user.getFirstName());
        userRequestDto.setLastName(user.getLastName());
        userRequestDto.setBirthDate(user.getBrithDate());
        userRequestDto.setAddress(user.getAddress());
        userRequestDto.setPhoneNumber(user.getPhoneNumber());
        return userRequestDto;
    }
}