package com.zuluco.oculuz.controllers;

import com.zuluco.oculuz.model.dtos.AuthenticationResponse;
import com.zuluco.oculuz.model.dtos.ModelMapper;
import com.zuluco.oculuz.model.dtos.UserRegistrationDTO;
import com.zuluco.oculuz.model.entities.User;
import com.zuluco.oculuz.model.services.JwtUtil;
import com.zuluco.oculuz.model.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Controller
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
        if (!newUser.getPassword().equals(newUser.getPasswordConfirm())){
            return new ResponseEntity<>("Passwords do not match", HttpStatus.BAD_REQUEST);
        }
        if (!userService.saveUser(ModelMapper.toUser(newUser))) {
            return new ResponseEntity<>("User with this name already exists", HttpStatus.CONFLICT);
        }

        // Load user details
        final UserDetails userDetails = userService.loadUserByUsername(newUser.getUsername());

        // Generate JWT
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        // Create HttpHeaders object and set Authorization header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwt);

        // Return JWT in Authorization header
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
