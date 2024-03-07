package com.fptu.estate.controller;

import com.fptu.estate.DTO.ApartmentDTO;
import com.fptu.estate.DTO.ArticleDTO;
import com.fptu.estate.DTO.BookingDistributionDTO;
import com.fptu.estate.DTO.BuildingDTO;
import com.fptu.estate.DTO.CityDTO;
import com.fptu.estate.services.imp.BookingDistributionServiceImp;
import com.fptu.estate.services.imp.CityServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
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
@RequestMapping("/api/booking-distribution")
public class BookingDistributionController {
  @Autowired
  private BookingDistributionServiceImp bookingDistributionServiceImp;


  @Operation(summary = "Get All Booking Distribution by AgencyId")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Load BookingDistribution List", content = @Content(schema = @Schema(implementation = BookingDistributionDTO.class))),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "403", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @GetMapping("/agency/{agency-id}")
  public ResponseEntity<?> findAll(@PathVariable("agency-id") Integer id){
    try {
    List<BookingDistributionDTO> list = bookingDistributionServiceImp.getAllByAgencyId(id);
      return ResponseEntity.ok(list);
    } catch (Exception e) {
      return new ResponseEntity<>("No booking found!!!", HttpStatus.NOT_FOUND);
    }
  }
  @Operation(summary = "Create booking distribution")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Create Booking Distribution Successfully!!!", content = @Content(schema = @Schema(implementation = BookingDistributionDTO.class))),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @PostMapping("/create")
  public ResponseEntity<?> createBuilding (@RequestBody BookingDistributionDTO bookingDistributionDTO){
    try {
      bookingDistributionServiceImp.createBookingDistribution(bookingDistributionDTO);
      return ResponseEntity.ok("Create successfully!!!");
    } catch (Exception e) {
      throw new RuntimeException("Create error!!!" + e.getMessage());
    }
  }

  @Operation(summary = "Get All Booking Distribution by status")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Load BookingDistribution List", content = @Content(schema = @Schema(implementation = BookingDistributionDTO.class))),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "403", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @GetMapping("/status/{status}")
  public ResponseEntity<?> findAllByStatus(@PathVariable("status") Integer status){
    try {
      List<BookingDistributionDTO> list = bookingDistributionServiceImp.getAllByStatus(status);
      return ResponseEntity.ok(list);
    } catch (Exception e) {
      return new ResponseEntity<>("No booking found!!!", HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Cancel booking distribution")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Cancel Booking Distribution Successfully!!!", content = @Content(schema = @Schema(implementation = BookingDistributionDTO.class))),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @PostMapping("/cancel/{bookingId}")
  public ResponseEntity<?> cancelBooking (@PathVariable("bookingId") Integer bookingId){
    try {
      boolean ok = bookingDistributionServiceImp.cancelBookingDistribution(bookingId);
      if(ok){
        return ResponseEntity.ok("Delete successfully!!!");
      } else {
        return ResponseEntity.badRequest().body("Delete fail!!!");
      }
    } catch (Exception e) {
      throw new RuntimeException("Create error!!!" + e.getMessage());
    }
  }
}
