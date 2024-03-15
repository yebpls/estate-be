package com.fptu.estate.controller;

import com.fptu.estate.DTO.AppointmentDTO;
import com.fptu.estate.DTO.ContractDTO;
import com.fptu.estate.DTO.ProjectDTO;
import com.fptu.estate.DTO.SubscriptionDTO;
import com.fptu.estate.services.imp.ContractServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/contract")
public class ContractController {
  @Autowired
  private ContractServiceImp contractServiceImp;

  @Operation(summary = "Get contract details by AppointmentID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Load contract", content = @Content(schema = @Schema(implementation = ContractDTO.class))),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @GetMapping("/by-appointment/{appointId}")
  public ResponseEntity<?> findByAppointmentId(@Parameter(description = "Subscription ID", example = "1") @PathVariable("appointId") Integer appointId){
    try {
      ContractDTO contractDTO = contractServiceImp.getByAppointmentId(appointId);
      return ResponseEntity.ok(contractDTO);
    } catch (Exception e) {
      return new ResponseEntity<>("No contract found!!!", HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Get contract details by AppartmentId")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Load contract", content = @Content(schema = @Schema(implementation = ContractDTO.class))),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @GetMapping("/by-apartment/{apartId}")
  public ResponseEntity<?> findByApartmentId(@Parameter(description = "Subscription ID", example = "1") @PathVariable("apartId") Integer apartId){
    try {
      ContractDTO contractDTO = contractServiceImp.getByApartmentId(apartId);
      return ResponseEntity.ok(contractDTO);
    } catch (Exception e) {
      return new ResponseEntity<>("No contract found!!!", HttpStatus.NOT_FOUND);
    }
  }



}
