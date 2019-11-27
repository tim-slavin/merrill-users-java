package com.merrill.users.managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        CompletableFuture<User[]> unregisteredUsersFuture = usersService.getUnregistered();
        CompletableFuture<Membership[]> membershipsFuture = membershipsService.get();

        CompletableFuture.allOf(registeredUsersFuture, unregisteredUsersFuture, membershipsFuture).join();

        List<User> registeredUsers = Arrays.asList(registeredUsersFuture.get());
        List<User> unregisteredUsers = Arrays.asList(unregisteredUsersFuture.get());
        List<Membership> memberships = Arrays.asList(membershipsFuture.get());

        HashMap<String, Set<String>> userProjects = getProjects(memberships);

        List<User> users = new ArrayList<>();
        users.addAll(registeredUsers);
        users.addAll(unregisteredUsers);
        
        return attachProjects(users, userProjects);
    }
    
    private HashMap<String, Set<String>> getProjects(List<Membership> memberships) {
    	HashMap<String, Set<String>> projects = new HashMap<String, Set<String>>();
    	
    	for (Membership membership: memberships) {
    		String projectId = membership.getProjectId();
    		String userId = membership.getUserId();
    		
    		Set<String> userProjectIds = projects.get(userId);
    		if (userProjectIds == null) {
    			Set<String> projectIds = new HashSet<String>();
    			projectIds.add(projectId);
    			
    			projects.put(userId, projectIds);
    		} else {
    			userProjectIds.add(projectId);
    		}
    	}
    	
    	return projects;
    }
    
    private List<User> attachProjects(List<User> users, HashMap<String, Set<String>> projects) {
    	List<User> usersWithProjects = new ArrayList<User>();
    	
    	for (User originalUser: users) {
    		User user = originalUser.copy();
    		
    		Set<String> userProjectIds = projects.getOrDefault(user.getId(), new HashSet<String>());
    		user.setProjectIds(userProjectIds.toArray(new String[0]));
    		
    		
			usersWithProjects.add(user);
    	}
    	
    	return usersWithProjects;
    }
}
