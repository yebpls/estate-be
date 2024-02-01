package com.fptu.estate.controller;

import com.fptu.estate.DTO.AccountDTO;
import com.fptu.estate.DTO.AccountRegisterRequest;
import com.fptu.estate.DTO.LoginRequest;
import com.fptu.estate.entities.AccountEntity;
import com.fptu.estate.payload.response.BaseResponse;
import com.fptu.estate.security.jwt.JwtService;
import com.fptu.estate.services.AccountService;
import com.fptu.estate.services.imp.LoginServiceImp;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

//  @Autowired
//  private AccountService accountService;

  private Logger logger = LoggerFactory.getLogger(LoginController.class);


  private Gson gson = new Gson();

  @Operation(summary = "Just test authentication and CORS")
  @GetMapping("")
  public ResponseEntity<?> login(){

    return new ResponseEntity<>("ok", HttpStatus.OK);

  }

  @Operation(summary = "Login")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "400", description = "Can't found account! Bad Request!"),
      @ApiResponse(responseCode = "200", description = "Login Successfully!"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @PostMapping("")
  public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
    String email = loginRequest.getEmail();
    String password = loginRequest.getPassword();
    logger.info("email: " + email);
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email,
        password);
//    System.out.println(token);

      Authentication authentication = authenticationManager.authenticate(token);
      logger.info("authentication: " + authentication);
      String json = gson.toJson(authentication.getAuthorities());
      AccountEntity account = loginServiceImp.checkLogin(email, password);
      String jwtToken = jwtService.generateToken(account.getRole(), account);
      BaseResponse baseResponse = new BaseResponse();
      baseResponse.setData(jwtToken);
      return new ResponseEntity<>(baseResponse, HttpStatus.OK);

  }

//  @Operation(summary = "Create new Account")
//  @ApiResponses(value = {
//      @ApiResponse(responseCode = "400", description = "Can't create account! Bad Request!"),
//      @ApiResponse(responseCode = "201", description = "Account created successfully!"),
//      @ApiResponse(responseCode = "500", description = "Internal error")
//  })
//  @PostMapping("/register")
//  public ResponseEntity<?> register(@RequestBody AccountRegisterRequest accountRegisterRequest){
//    AccountDTO accountDTO = accountService.createAccount(accountRegisterRequest);
//    if(accountDTO != null){
//      return new ResponseEntity<>("Account created successfully!", HttpStatus.CREATED);
//    }else{
//      return new ResponseEntity<>("Create account fail!",HttpStatus.BAD_REQUEST);
//    }
//  }

}
