package com.example.RegisterLogin.util;

import com.example.RegisterLogin.model.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RoleCheckUtil {

    @Autowired
    UserContext userContext;

    public void checkUser() {
        if(!Objects.equals(userContext.getRole().getRoleName(), "user")) {
            throw new RuntimeException("Only users can access this");
        }
    }

    public void checkManager() {
        if(!Objects.equals(userContext.getRole().getRoleName(), "manager")) {
            throw new RuntimeException("Only managers can access this");
        }
    }
}
