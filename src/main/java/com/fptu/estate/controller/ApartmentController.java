package com.fptu.estate.controller;

import com.fptu.estate.DTO.ApartmentDTO;
import com.fptu.estate.services.imp.ApartmentServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/apartment")
public class ApartmentController {
  @Autowired
  private ApartmentServiceImp apartmentServiceImp;

  @Operation(summary = "Get all apartments")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Load Apartments List", content = @Content(schema = @Schema(implementation = ApartmentDTO.class))),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @GetMapping("")
  public ResponseEntity<?> findAll(){
    List<ApartmentDTO> list = apartmentServiceImp.findAll();
    if (list.isEmpty()) {
      return new ResponseEntity<>("No apartment found!!!", HttpStatus.NOT_FOUND);
    } else {
      return ResponseEntity.ok(list);
    }
  }

  @Operation(summary = "Get apartment details by ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Load Apartment", content = @Content(schema = @Schema(implementation = ApartmentDTO.class))),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@Parameter(description = "Apartment ID", example = "1") @PathVariable("id") Integer id){
    try {
      ApartmentDTO apartment = apartmentServiceImp.findById(id);
      return ResponseEntity.ok(apartment);
    } catch (Exception e) {
      return new ResponseEntity<>("No apartment found!!!", HttpStatus.NOT_FOUND);
    }
  }
  @Operation(summary = "Create a new apartment")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Apartment created successfully", content = @Content(schema = @Schema(implementation = ApartmentDTO.class))),
          @ApiResponse(responseCode = "400", description = "Bad request"),
          @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<?> createApartment(@RequestBody ApartmentDTO newApartment) {
    try {
      ApartmentDTO createdApartment = apartmentServiceImp.createApartment(newApartment);
      return ResponseEntity.status(HttpStatus.CREATED).body(createdApartment);
    } catch (Exception e) {
      return new ResponseEntity<>("Failed to create apartment: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
