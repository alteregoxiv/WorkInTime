package com.example.RegisterLogin.service.impl;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmail(email));
//        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
//
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
//    }
//
//    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
//        // Implement your logic to retrieve user authorities (roles)
//        // and return them as a collection of GrantedAuthority objects.
//        return Collections.emptyList();
//    }
}
