package com.zuluco.oculuz.controllers.user;

import com.zuluco.oculuz.model.dtos.ModelMapper;
import com.zuluco.oculuz.model.dtos.user.UserAccountMainPageDTO;
import com.zuluco.oculuz.model.entities.User;
import com.zuluco.oculuz.model.services.JwtUtil;
import com.zuluco.oculuz.model.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserAccountController {

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<UserAccountMainPageDTO> getUserInfo(@PathVariable Long id) {
        User user = userService.findUserById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        UserAccountMainPageDTO userAccountMainPageDTO = ModelMapper.toUserAccountMainPageDTO(user);
        return ResponseEntity.ok(userAccountMainPageDTO);
    }
}