package com.fptu.estate.controller;

import com.fptu.estate.DTO.AccountDTO;
import com.fptu.estate.DTO.AccountRegisterRequest;
import com.fptu.estate.DTO.BuildingDTO;
import com.fptu.estate.services.imp.AccountServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@CrossOrigin
@RequestMapping("/api/account")
public class AccountController {

  @Autowired
  private AccountServiceImp accountServiceImp;

  @Operation(summary = "Create new Account")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "400", description = "Can't create account! Bad Request!"),
      @ApiResponse(responseCode = "201", description = "Account created successfully!"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody AccountRegisterRequest accountRegisterRequest){
    AccountDTO accountDTO = accountServiceImp.createAccount(accountRegisterRequest);
    if(accountDTO != null){
      return new ResponseEntity<>("Account created successfully!", HttpStatus.CREATED);
    }else{
      return new ResponseEntity<>("Create account fail!",HttpStatus.BAD_REQUEST);
    }
  }

  @Operation(summary = "Get account details by ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Load Account", content = @Content(schema = @Schema(implementation = AccountDTO.class))),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @GetMapping("/{id}")
  public ResponseEntity<?> getAccountById (@PathVariable("id") Integer id){
    try {
      AccountDTO accountDTO = accountServiceImp.findById(id);
      return ResponseEntity.ok(accountDTO);
    } catch (Exception e) {
      return new ResponseEntity<>("No account found!!!", HttpStatus.NOT_FOUND);
    }
  }
  @Operation(summary = "Update account details")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Load Account", content = @Content(schema = @Schema(implementation = AccountDTO.class))),
          @ApiResponse(responseCode = "404", description = "Not found"),
          @ApiResponse(responseCode = "400", description = "Bad request"),
          @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @PutMapping("/{id}")
  public ResponseEntity<?> updateAccountById(@PathVariable("id") Integer id) {
    try {
      AccountDTO account = accountServiceImp.findById(id);
      if(account != null) {
        accountServiceImp.UpdateAccount(account);
      } else {
        return new ResponseEntity<>("No account found!!!", HttpStatus.NOT_FOUND);
      }
      return ResponseEntity.ok("Update successfully");
    } catch (Exception e) {
      return new ResponseEntity<>("No account found!!!", HttpStatus.NOT_FOUND);
    }
  }

}
