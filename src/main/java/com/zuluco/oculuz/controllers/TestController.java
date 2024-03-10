package com.zuluco.oculuz.controllers;

import com.zuluco.oculuz.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/test")
	public ResponseEntity<?> myEndpoint(@RequestHeader(value = "Authorization", required = false) String authHeader, @RequestBody String requestBody) {
		System.out.println(requestBody);
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			String jwt = authHeader.substring(7);
			if (jwtUtils.validateJwtToken(jwt)) {
				// Если токен валидный, возвращаем один набор данных
				return ResponseEntity.ok("Data for authenticated user");
			} else {
				// Если токен невалидный, возвращаем 403
				return ResponseEntity.status(403).build();
			}
		}
		// Если токен отсутствует или невалидный, возвращаем другой набор данных
		return ResponseEntity.ok("Data for unauthenticated user");
	}
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
