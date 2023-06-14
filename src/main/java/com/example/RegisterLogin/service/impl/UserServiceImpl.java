package com.example.RegisterLogin.service.impl;

import com.example.RegisterLogin.exceptions.IncorrectPasswordException;
import com.example.RegisterLogin.exceptions.UserNotFoundException;
import com.example.RegisterLogin.util.JwtTokenUtil;
import com.example.RegisterLogin.dto.request.LogInRequestDTO;
import com.example.RegisterLogin.dto.request.RegistrationRequestDTO;
import com.example.RegisterLogin.model.Role;
import com.example.RegisterLogin.model.User;
import com.example.RegisterLogin.repository.RoleDAO;
import com.example.RegisterLogin.repository.UserDAO;
import com.example.RegisterLogin.service.UserService;
import com.example.RegisterLogin.util.PasswordEncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public User registerUser(RegistrationRequestDTO registrationRequestDTO) {
        User user = new User();
        String encodedPassword = PasswordEncryptionUtil.encryptPassword(registrationRequestDTO.getPassword());
        long role_id = registrationRequestDTO.getRoleId();
        Optional<Role> optionalRole = Optional.ofNullable(roleDAO.findById(role_id));
        Role role = optionalRole.orElseThrow(() -> new IllegalArgumentException("Invalid Role Id"));
        LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp currentTimestamp = Timestamp.valueOf(currentDateTime);
        Timestamp time = new Timestamp(System.currentTimeMillis());

        //=========================================================
        // This is important
        // Find a way to get the GMT(Zone Specific) Timezone.
        //=========================================================

        user.setEmail(registrationRequestDTO.getEmail());
        user.setPassword(encodedPassword);
        user.setRole(role);
        user.setCreatedAt(currentTimestamp);
        user.setUpdatedAt(currentTimestamp);

        return userDAO.save(user);
    }

    public String logInUser(LogInRequestDTO logInRequestDTO) {
        String email = logInRequestDTO.getEmail();
        String password = logInRequestDTO.getPassword();

        User user = userDAO.findByEmail(email);
        if(user == null) {
            throw new UserNotFoundException("User does not exist");
        }

        if(PasswordEncryptionUtil.verifyPassword(password, user.getPassword())) {
            return jwtTokenUtil.generateToken(user.getEmail());
        } else {
            throw new IncorrectPasswordException("Incorrect Password");
        }
    }
}
