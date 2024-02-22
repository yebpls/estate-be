package com.fptu.estate.controller;

import com.fptu.estate.DTO.InvestorDTO;
import com.fptu.estate.services.imp.InvestorServiceImp;
import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/investor")
public class InvestorController {
  @Autowired
  private InvestorServiceImp investorServiceImp;

  @Operation(summary = "Get investor details by Account ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Load Investor", content = @Content(schema = @Schema(implementation = InvestorDTO.class))),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @GetMapping("/{id}")
  public ResponseEntity<?> getInvestorById (@PathVariable("id") Integer id){
    try {

      InvestorDTO investorDTO = investorServiceImp.findInvestorById(id);
      return ResponseEntity.ok(investorDTO);
    } catch (Exception e) {
      return new ResponseEntity<>("No investor found!!!", HttpStatus.NOT_FOUND);
    }
  }
}
