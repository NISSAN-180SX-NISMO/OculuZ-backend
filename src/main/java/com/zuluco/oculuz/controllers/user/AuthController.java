package com.zuluco.oculuz.controllers.user;

import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zuluco.oculuz.models.entities.RoleType;
import com.zuluco.oculuz.models.entities.RefreshToken;
import com.zuluco.oculuz.models.entities.Role;
import com.zuluco.oculuz.models.entities.User;
import com.zuluco.oculuz.payload.request.LoginRequest;
import com.zuluco.oculuz.payload.request.SignupRequest;
import com.zuluco.oculuz.payload.request.TokenRefreshRequest;
import com.zuluco.oculuz.payload.response.JwtResponse;
import com.zuluco.oculuz.payload.response.MessageResponse;
import com.zuluco.oculuz.payload.response.TokenRefreshResponse;
import com.zuluco.oculuz.repository.RoleRepository;
import com.zuluco.oculuz.repository.UserRepository;
import com.zuluco.oculuz.security.jwt.JwtUtils;
import com.zuluco.oculuz.security.services.RefreshTokenService;
import com.zuluco.oculuz.security.services.UserDetailsImpl;
import com.zuluco.oculuz.security.services.UserDetailsServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
  private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  RefreshTokenService refreshTokenService;

  @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

  private void test() {
    // Получаем объект Authentication из контекста безопасности
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

// Получаем principal из объекта Authentication
    Object principal = authentication.getPrincipal();

    if (principal instanceof UserDetails) {
      // Если principal является экземпляром UserDetails, мы можем безопасно привести его к UserDetails
      UserDetails userDetails = (UserDetails) principal;

      // Теперь мы можем получить имя пользователя
      String username = userDetails.getUsername();

      // Выводим имя пользователя на экран
      System.out.println("Current user: " + username);
    } else {
      // Если principal не является экземпляром UserDetails, выводим его на экран как есть
      System.out.println("Current user: " + principal.toString());
    }
  }

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    logger.info("Authentication request received for user: {}", loginRequest.getUsername());

    Authentication authentication;
    try {
      authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
      logger.info("Authentication successful for user: {}", loginRequest.getUsername());
    } catch (BadCredentialsException e) {
      logger.warn("Authentication failed for user: {}", loginRequest.getUsername());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Error: Incorrect username or password!"));
    } catch (Exception e) {
      logger.error("Authentication failed for user: {}", loginRequest.getUsername(), e);
      throw e;
    }

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    System.out.println(
            userDetails.getUsername() + " "
            + userDetails.getPassword() + " "
            + userDetails.getAuthorities() + " "
            + userDetails.getId() + " "
            + userDetails.getEmail() + " "
            + userDetails.getAuthorities() + " "
            + userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList()));

    String jwt = jwtUtils.generateJwtToken(userDetails);
    logger.info("JWT token generated for user: {}", userDetails.getUsername());

    List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
            .collect(Collectors.toList());

    refreshTokenService.deleteByUserId(userDetails.getId());

    RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
    logger.info("Refresh token created for user: {}", userDetails.getUsername());

    logger.info("Sending response for authentication request of user: {}", userDetails.getUsername());



    test();

    return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(),
            userDetails.getUsername(), userDetails.getEmail(), roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    logger.info("Registration request received for user: {}", signUpRequest.getUsername());

    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      logger.warn("Username is already taken: {}", signUpRequest.getUsername());
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      logger.warn("Email is already in use: {}", signUpRequest.getEmail());
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));
    user.setRegistDate(new java.sql.Date(new Date().getTime()));

    List<String> strRoles = signUpRequest.getRole();
    List<Role> roles = new ArrayList<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
      logger.info("User with role " + userRole.toString() + " registered successfully: {}", signUpRequest.getUsername());
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);
            logger.info("User with role ADMIN registered successfully: {}", signUpRequest.getUsername());
            break;
          case "author":
            Role modRole = roleRepository.findByName(RoleType.ROLE_AUTHOR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(modRole);
            logger.info("User with role AUTHOR registered successfully: {}", signUpRequest.getUsername());
            break;
          default:
            Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
            logger.info("User with role USER registered successfully: {}", signUpRequest.getUsername());
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);
    logger.info("User registered successfully: {}", signUpRequest.getUsername());

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  @PostMapping("/refresh")
  public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
    logger.info("Refresh token request received");

    String requestRefreshToken = request.getRefreshToken();

    return refreshTokenService.findByToken(requestRefreshToken)
            .map(refreshToken -> {
              logger.info("Refresh token found and is valid");
              refreshTokenService.verifyExpiration(refreshToken);
              return refreshToken;
            })
            .map(RefreshToken::getUser)
            .map(user -> {
              String token = jwtUtils.generateTokenFromUsername(user.getUsername());
              logger.info("New JWT token generated for user: {}", user.getUsername());

              // Delete old refresh token
              refreshTokenService.deleteByToken(requestRefreshToken);
              logger.info("Old refresh token deleted for user: {}", user.getUsername());

              // Create new refresh token
              RefreshToken newRefreshToken = refreshTokenService.createRefreshToken(user.getId());
              logger.info("New refresh token created for user: {}", user.getUsername());


              return ResponseEntity.ok(new TokenRefreshResponse(token, newRefreshToken.getToken()));
            })
            .orElseGet(() -> {
              logger.error("Refresh token is not in database or has expired. User has deleted the token. Please make a new signin request");
              //SecurityContextHolder.clearContext(); // очищаем контекст безопасности
              return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            });
  }

  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

    test();

    UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Long userId = userDetails.getId();
    logger.info("Logout request received for user: {}", userDetails.getUsername());

    refreshTokenService.deleteByUserId(userId);
    logger.info("Refresh token deleted for user: {}", userDetails.getUsername());

    logger.info("Logout successful for user: {}", userDetails.getUsername());
    return ResponseEntity.ok(new MessageResponse("Log out successful!"));
  }

}
