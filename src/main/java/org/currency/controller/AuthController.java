package org.currency.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.currency.data.dto.SignUpRequest;
import org.currency.data.dto.SignUpResponse;
import org.currency.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<SignUpResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
    var user = userService.signUp(signUpRequest);
    var response = new SignUpResponse().setEmail(user.getEmail()).setId(user.getId());
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(response);
  }

}
