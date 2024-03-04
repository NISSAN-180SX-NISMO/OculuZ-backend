package com.zuluco.oculuz.controllers.auth;

import com.zuluco.oculuz.model.dtos.AuthenticationResponse;
import com.zuluco.oculuz.model.dtos.ModelMapper;
import com.zuluco.oculuz.model.dtos.UserRegistrationDTO;
import com.zuluco.oculuz.model.entities.Role;
import com.zuluco.oculuz.model.entities.User;
import com.zuluco.oculuz.model.services.JwtUtil;
import com.zuluco.oculuz.model.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.sql.Date;
import java.util.Collections;
import java.util.Objects;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public ResponseEntity<?> addUser(@RequestBody @Valid UserRegistrationDTO newUser, BindingResult bindingResult) {
        System.out.println(newUser); // todo: remove

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Validation error", HttpStatus.BAD_REQUEST);
        }
        if (!newUser.getPassword().equals(newUser.getPasswordConfirm())) {
            return new ResponseEntity<>("Passwords do not match", HttpStatus.BAD_REQUEST);
        }
        try {
            User user = userService.loadUserByUsername(newUser.getUsername());
            if (Objects.equals(user.getUsername(), newUser.getUsername())) {
                return new ResponseEntity<>("User with this name already exists", HttpStatus.CONFLICT);
            }
        } catch (UsernameNotFoundException e) {
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        }


        // Create a new User with default values
        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        user.setAccessPermission("user");
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setBanned(false);
        user.setRegistDate(new Date(System.currentTimeMillis()));
        user.setAvatarUrl("https://storage.yandexcloud.net/oculuz-media-storage/avatar/defaultUserAvatar.png");

        // Save the new User
        userService.saveUser(user);

        // Load user details
        final User userDetails = userService.loadUserByUsername(newUser.getUsername());

        // Generate JWT
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        // Create HttpHeaders object and set Authorization header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwt);

        // Return JWT in Authorization header
        return new ResponseEntity<>(headers, HttpStatus.OK);


    }
}
