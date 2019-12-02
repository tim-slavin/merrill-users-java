package com.merrill.users.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.merrill.users.managers.UsersManager;
import com.merrill.users.model.User;

@RestController
public class UsersController {
    // private Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UsersManager usersManager;

//    @ApiOperation(value = "Get all registered and un-registered users with their project assignments")
    @GetMapping(value = "/users", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping("/users")
    public List<User> get() {
        try {
            return usersManager.get();
        } catch(Exception ex) { // Ensure no stacktrace leaks make it out
        	System.out.println(ex);
           throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
