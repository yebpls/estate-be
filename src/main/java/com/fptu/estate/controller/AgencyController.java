package com.fptu.estate.controller;

import com.fptu.estate.DTO.AgencyDTO;
import com.fptu.estate.services.imp.AgencyServiceImp;
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
@RequestMapping("/api/agency")
public class AgencyController {
  @Autowired
  private AgencyServiceImp agencyServiceImp;

  @Operation(summary = "Get agency details by ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Load Agency", content = @Content(schema = @Schema(implementation = AgencyDTO.class))),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @GetMapping("/{id}")
  public ResponseEntity<?> getAgencyById (@PathVariable("id") Integer id){
    try {
      AgencyDTO agencyDTO = agencyServiceImp.findAgencyById(id);
      return ResponseEntity.ok(agencyDTO);
    } catch (Exception e) {
      return new ResponseEntity<>("No agency found!!!", HttpStatus.NOT_FOUND);
    }
  }
}
