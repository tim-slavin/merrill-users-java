package com.merrill.users.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.merrill.users.managers.UsersManager;
import com.merrill.users.model.RegisteredUser;
import com.merrill.users.model.RegisteredUserImpl;
import com.merrill.users.model.UnregisteredUser;
import com.merrill.users.model.UnregisteredUserImpl;
import com.merrill.users.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTests {
    private List<User> users;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersManager usersManager;

    @Before
    public void setupMock() throws ExecutionException, InterruptedException {
        MockitoAnnotations.initMocks(this);

        UnregisteredUser unregisteredUser = new UnregisteredUserImpl();
        unregisteredUser.setRegistrationId("1");
        unregisteredUser.setRegistrationIdGeneratedTime("1");
        unregisteredUser.setEmailAddress("test1@test.com");
        unregisteredUser.setId("1");
        unregisteredUser.setLanguageCode("en");

        RegisteredUser registeredUser = new RegisteredUserImpl();
        registeredUser.setCity("city1");
        registeredUser.setCompany("company1");
        registeredUser.setCountry("Country1");
        registeredUser.setDisclaimerAccepted(true);
        registeredUser.setFirstName("First Name 1");
        registeredUser.setLastName("Last Name 1");
        registeredUser.setOrganizationType("Type 1");
        registeredUser.setPhone("612-123-3214");
        registeredUser.setState("MN");
        registeredUser.setZipCode("55402");
        registeredUser.setId("3");
        registeredUser.setEmailAddress("Test3@test.com");
        registeredUser.setLanguageCode("en");
        registeredUser.setProjectIds(new String[]{"1", "2"});


        users = new ArrayList<>();

        users.add(unregisteredUser);
        users.add(registeredUser);
    }

    @InjectMocks
    private UsersController usersController;

    @Test
    public void getUsersShouldReturnAllTheUsers() throws Exception {
        Mockito.when(usersManager.get())
                .thenReturn(users);

        this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().json("[{'id':'1','emailAddress':'test1@test.com','languageCode':'en','projectIds':['1','2'],'registrationId':'1','registrationIdGeneratedTime':'1'},{'id':'3','emailAddress':'Test3@test.com','languageCode':'en','projectIds':[],'firstName':'First Name 1','lastName':'Last Name 1','company':'company1','organizationType':'Type 1','city':'city1','state':'MN','zipCode':'55402','country':'Country1','phone':'612-123-3214','disclaimerAccepted':true}]"));
    }

    @Test
    public void handleErrorWhenGettingUsers() throws Exception {
        Mockito.when(usersManager.get())
                .thenThrow(new InterruptedException());

        this.mockMvc.perform(get("/users"))
                .andExpect(status().isInternalServerError());
    }

}
