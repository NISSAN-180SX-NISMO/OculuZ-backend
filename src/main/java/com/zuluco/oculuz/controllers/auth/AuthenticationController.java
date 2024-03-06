package com.zuluco.oculuz.controllers.auth;

import com.zuluco.oculuz.model.dtos.AuthenticationRequest;
import com.zuluco.oculuz.model.dtos.AuthenticationResponse;
import com.zuluco.oculuz.model.dtos.user.ErrorResponse;
import com.zuluco.oculuz.model.entities.User;
import com.zuluco.oculuz.model.services.JwtUtil;
import com.zuluco.oculuz.model.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        System.out.println("im in createAuthenticationToken");
        System.out.println(authenticationRequest);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
            System.out.println("nice user!");
        }
        catch (BadCredentialsException e) {
            System.out.println("Incorrect username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Incorrect username or password"));
        }

        final User user = userService.loadUserByUsername(authenticationRequest.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("User not found"));
        }

        final String jwt = jwtTokenUtil.generateToken(user);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwt);
        return new ResponseEntity<>(new AuthenticationResponse(jwt), headers, HttpStatus.OK);
    }
}