package com.fptu.estate.controller;

import com.fptu.estate.DTO.CustomerDTO;
import com.fptu.estate.services.imp.CustomerServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/customer")
public class CustomerController {
  private Logger log = LoggerFactory.getLogger(CustomerController.class);


  @Autowired
  private CustomerServiceImp customerServiceImp;

  @Operation(summary = "Get customer details by Account ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Load Customer", content = @Content(schema = @Schema(implementation = CustomerDTO.class))),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @GetMapping("/{id}")
  public ResponseEntity<?> getCustomerById (@PathVariable("id") Integer id){
    log.info("Attempting to find customer with account ID: {}", id);

    try {

      CustomerDTO customerDTO = customerServiceImp.findCustomerIdByAccountId(id);
      return ResponseEntity.ok(customerDTO);
    } catch (Exception e) {
      log.error("Error finding customer with account ID: {}, Error: {}", id, e.getMessage());

      return new ResponseEntity<>("No customer found!!!", HttpStatus.NOT_FOUND);
    }
  }
}
