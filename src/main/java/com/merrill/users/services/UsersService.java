package com.merrill.users.services;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.merrill.users.model.RegisteredUserImpl;
import com.merrill.users.model.UnregisteredUserImpl;
import com.merrill.users.model.User;

@Service
public class UsersService {

    @Autowired
    private RestTemplate restTemplate;

    @Async
    public CompletableFuture<User[]> getRegistered() {
        User[] users = restTemplate.getForObject( "/registeredusers", RegisteredUserImpl[].class);
        return CompletableFuture.completedFuture(users);
    }

    @Async
    public CompletableFuture<User[]> getUnRegistered() {
        User[] users = restTemplate.getForObject( "/unregisteredusers", UnregisteredUserImpl[].class);
        return CompletableFuture.completedFuture(users);
    }
}