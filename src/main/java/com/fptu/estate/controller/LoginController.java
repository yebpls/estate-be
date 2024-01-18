package com.fptu.estate.controller;

import com.fptu.estate.DTO.LoginRequest;
import com.fptu.estate.entities.UserEntity;
import com.fptu.estate.payload.response.BaseResponse;
import com.fptu.estate.security.jwt.JwtService;
import com.fptu.estate.services.imp.LoginServiceImp;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private LoginServiceImp loginServiceImp;

  @Autowired
  private JwtService jwtService;

  private Logger logger = LoggerFactory.getLogger(LoginController.class);


  private Gson gson = new Gson();

  @PostMapping("")
  public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
    String email = loginRequest.getEmail();
    String password = loginRequest.getPassword();
    logger.info("Username: " + email);
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email,
        password);
//    System.out.println(token);

      Authentication authentication = authenticationManager.authenticate(token);
      logger.info("authentication: " + authentication);
      String json = gson.toJson(authentication.getAuthorities());
      UserEntity user = loginServiceImp.checkLogin(email, password);
      String jwtToken = jwtService.generateToken(user.getRole(), user);
      BaseResponse baseResponse = new BaseResponse();
      baseResponse.setData(jwtToken);
      return new ResponseEntity<>(baseResponse, HttpStatus.OK);

  }

  @GetMapping("")
  public ResponseEntity<?> login(){

    return new ResponseEntity<>("ok", HttpStatus.OK);

  }
}
