package com.merrill.users.managers;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.merrill.users.model.Membership;
import com.merrill.users.model.RegisteredUser;
import com.merrill.users.model.RegisteredUserImpl;
import com.merrill.users.model.UnregisteredUser;
import com.merrill.users.model.UnregisteredUserImpl;
import com.merrill.users.model.User;
import com.merrill.users.services.MembershipsService;
import com.merrill.users.services.UsersService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersManagerTests {
    private RegisteredUser registeredUser1;
    private RegisteredUser registeredUser2;
    private UnregisteredUser unregisteredUser1;
    private UnregisteredUser unregisteredUser2;

    @MockBean
    private UsersService usersService;

    @MockBean
    private MembershipsService membershipsService;

    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);

        Membership projectMembership1 = new Membership();
        projectMembership1.setProjectId("1");
        projectMembership1.setUserId("1");

        Membership projectMembership2 = new Membership();
        // Ensure we handle duplicates
        projectMembership2.setProjectId("1");
        projectMembership2.setUserId("1");

        Membership projectMembership3 = new Membership();
        projectMembership3.setProjectId("22");
        projectMembership3.setUserId("3");

        Membership projectMembership4 = new Membership();
        projectMembership4.setProjectId("23");
        projectMembership4.setUserId("1");

        Membership projectMembership5 = new Membership();
        // Ensure we handle duplicates
        projectMembership5.setProjectId("22");
        projectMembership5.setUserId("3");

        Membership projectMembership6 = new Membership();
        projectMembership6.setProjectId("26");
        projectMembership6.setUserId("3");

        unregisteredUser1 = new UnregisteredUserImpl();
        unregisteredUser1.setRegistrationId("1");
        unregisteredUser1.setRegistrationIdGeneratedTime("1");
        unregisteredUser1.setEmailAddress("test1@test.com");
        unregisteredUser1.setId("1");
        unregisteredUser1.setLanguageCode("en");

        unregisteredUser2 = new UnregisteredUserImpl();
        unregisteredUser2.setRegistrationId("2");
        unregisteredUser2.setRegistrationIdGeneratedTime("2");
        unregisteredUser2.setEmailAddress("test2@test.com");
        unregisteredUser2.setId("2");
        unregisteredUser2.setLanguageCode("en");

        registeredUser1 = new RegisteredUserImpl();
        registeredUser1.setCity("city1");
        registeredUser1.setCompany("company1");
        registeredUser1.setCountry("Country1");
        registeredUser1.setDisclaimerAccepted(true);
        registeredUser1.setFirstName("First Name 1");
        registeredUser1.setLastName("Last Name 1");
        registeredUser1.setOrganizationType("Type 1");
        registeredUser1.setPhone("612-123-3214");
        registeredUser1.setState("MN");
        registeredUser1.setZipCode("55402");
        registeredUser1.setId("3");
        registeredUser1.setEmailAddress("Test3@test.com");
        registeredUser1.setLanguageCode("en");

        registeredUser2 = new RegisteredUserImpl();
        registeredUser2.setCity("city2");
        registeredUser2.setCompany("company2");
        registeredUser2.setCountry("Country2");
        registeredUser2.setDisclaimerAccepted(false);
        registeredUser2.setFirstName("First Name 2");
        registeredUser2.setLastName("Last Name 2");
        registeredUser2.setOrganizationType("Type 2");
        registeredUser2.setPhone("612-123-3214");
        registeredUser2.setState("MN");
        registeredUser2.setZipCode("55402");
        registeredUser2.setId("4");
        registeredUser2.setEmailAddress("Test4@test.com");
        registeredUser2.setLanguageCode("en");


        User[] unRegisteredUsers = {unregisteredUser1, unregisteredUser2};
        User[] registeredUsers = {registeredUser1, registeredUser2};
        Membership[] projectMemberships = {
                projectMembership1,
                projectMembership2,
                projectMembership3,
                projectMembership4,
                projectMembership5,
                projectMembership6
        };

        Mockito.when(membershipsService.get())
                .thenReturn(CompletableFuture.supplyAsync(() -> projectMemberships));
        Mockito.when(usersService.getUnregistered())
                .thenReturn(CompletableFuture.supplyAsync(() -> unRegisteredUsers));
        Mockito.when(usersService.getRegistered())
                .thenReturn(CompletableFuture.supplyAsync(() -> registeredUsers));
    }

    @InjectMocks
    private UsersManager usersManager;

    @Test
    public void getUsersWithProjectIds() throws ExecutionException, InterruptedException {
        List<User> users = usersManager.get();
        RegisteredUser user1 = (RegisteredUser) users.get(0);
        RegisteredUser user2 = (RegisteredUser) users.get(1);
        UnregisteredUser user3 =(UnregisteredUser) users.get(2);
        UnregisteredUser user4 = (UnregisteredUser) users.get(3);

        assertThat(user1.getProjectIds(), is(new String[]{"22", "26"}));
        assertThat(user2.getProjectIds(), is(new String[]{}));
        assertThat(user3.getProjectIds(), is(new String[]{"1", "23"}));
        assertThat(user4.getProjectIds(), is(new String[]{}));

        assertRegisteredUser(user1, registeredUser1);
        assertRegisteredUser(user2, registeredUser2);
        assertUnregisteredUser(user3, unregisteredUser1);
        assertUnregisteredUser(user4, unregisteredUser2);
    }

    private void assertRegisteredUser(RegisteredUser user, RegisteredUser registeredUser) {
        assertThat(user.getCity(), is(registeredUser.getCity()));
        assertThat(user.getCompany(), is(registeredUser.getCompany()));
        assertThat(user.getCountry(), is(registeredUser.getCountry()));
        assertThat(user.isDisclaimerAccepted(), is(registeredUser.isDisclaimerAccepted()));
        assertThat(user.getEmailAddress(), is(registeredUser.getEmailAddress()));
        assertThat(user.getFirstName(), is(registeredUser.getFirstName()));
        assertThat(user.getId(), is(registeredUser.getId()));
        assertThat(user.getLanguageCode(), is(registeredUser.getLanguageCode()));
        assertThat(user.getLastName(), is(registeredUser.getLastName()));
        assertThat(user.getOrganizationType(), is(registeredUser.getOrganizationType()));
        assertThat(user.getPhone(), is(registeredUser.getPhone()));
        assertThat(user.getState(), is(registeredUser.getState()));
        assertThat(user.getZipCode(), is(registeredUser.getZipCode()));
    }

    private void assertUnregisteredUser(UnregisteredUser user, UnregisteredUser unRegisteredUser) {
        assertThat(user.getId(), is(unRegisteredUser.getId()));
        assertThat(user.getLanguageCode(), is(unRegisteredUser.getLanguageCode()));
        assertThat(user.getRegistrationId(), is(unRegisteredUser.getRegistrationId()));
        assertThat(user.getRegistrationIdGeneratedTime(), is(unRegisteredUser.getRegistrationIdGeneratedTime()));
        assertThat(user.getEmailAddress(), is(unRegisteredUser.getEmailAddress()));
    }
}

