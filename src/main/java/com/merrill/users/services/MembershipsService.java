package com.merrill.users.services;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.merrill.users.model.Membership;

@Service
public class MembershipsService {
	
    @Autowired
    private RestTemplate restTemplate;

    @Async
    public CompletableFuture<Membership[]> get() {
        Membership[] memberships = restTemplate.getForObject( "/projectmemberships", Membership[].class);
        return CompletableFuture.completedFuture(memberships);
    }  
}
