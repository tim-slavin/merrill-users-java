package com.merrill.users.managers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.merrill.users.model.Membership;
import com.merrill.users.model.User;
import com.merrill.users.services.MembershipsService;
import com.merrill.users.services.UsersService;

@Component
public class UsersManager {
    @Autowired
    private UsersService usersService;

    @Autowired
    private MembershipsService membershipsService;


    public List<User> get() throws InterruptedException, ExecutionException {
        CompletableFuture<User[]> registeredUsersFuture = usersService.getRegistered();
        //CompletableFuture<User[]> unRegisteredFuture = usersService.getUnRegistered();
        CompletableFuture<Membership[]> membershipsFuture = membershipsService.get();

        //CompletableFuture.allOf(registeredFuture, unRegisteredFuture, membershipsFuture).join();
        CompletableFuture.allOf(registeredUsersFuture, membershipsFuture).join();

        User[] registeredUsers = registeredUsersFuture.get();
        //UnRegisteredUser[] unRegisteredUsers = unRegisteredFuture.get();
       //ProjectMembership[] memberships = membershipsFuture.get();

        // HashMap<String, Set<String>> userMemberships = getUserProjects(memberships);

        //List<User> users = new ArrayList<>();
        //users.addAll(registeredUsers)
        //users.addAll(getUsers(registeredUsers, userMemberships));
        //users.addAll(getUsers(unRegisteredUsers, userMemberships));

        return Arrays.asList(registeredUsers);
    }
}
