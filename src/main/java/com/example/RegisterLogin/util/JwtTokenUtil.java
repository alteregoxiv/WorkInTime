package com.example.RegisterLogin.util;

import com.example.RegisterLogin.model.Role;
import com.example.RegisterLogin.model.User;
import com.example.RegisterLogin.model.UserContext;
import com.example.RegisterLogin.repository.RoleDAO;
import com.example.RegisterLogin.repository.UserDAO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    UserContext userContext;

    public String generateToken(String email) {
        log.info("request to generate JWT {}", email);
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration );

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUserFromJwtToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean isJwtValid(String jwtToken) {
        try {
            Jws<Claims> jwsClaims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(jwtToken);

            Claims claims = jwsClaims.getBody();
            String email = claims.getSubject();

            // Check the token expiration
            Date expirationDate = claims.getExpiration();
            if (expirationDate.before(new Date())) {
                return false; // Token has expired
            }

            // Check the validity of the email (add your custom validation logic)
            return isValidEmail(email);
        } catch (Exception e) {
            return false; // Token is invalid
        }
    }

    private boolean isValidEmail(String email) {
        try {
            User user = userDAO.findByEmail(email);
            Role role = user.getRole();
            userContext.setUser(user);
            userContext.setRole(role);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
