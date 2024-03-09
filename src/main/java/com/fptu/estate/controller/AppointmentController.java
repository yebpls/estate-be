package com.fptu.estate.controller;

import com.fptu.estate.DTO.ApartmentDTO;
import com.fptu.estate.DTO.AppointmentDTO;
import com.fptu.estate.services.imp.AppointmentServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/appointment")
public class AppointmentController {

  @Autowired
  private AppointmentServiceImp appointmentServiceImp;

  @Operation(summary = "Get all appointment")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Load Appoinment List", content = @Content(schema = @Schema(implementation = AppointmentDTO.class))),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @GetMapping("")
  public ResponseEntity<?> findAll() {
    List<AppointmentDTO> list = appointmentServiceImp.getAll();
    if (list.isEmpty()) {
      return new ResponseEntity<>("No apartment found!!!", HttpStatus.NOT_FOUND);
    } else {
      return ResponseEntity.ok(list);
    }
  }

  @Operation(summary = "Get appointment detail by distribution id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Load Appoinment detail", content = @Content(schema = @Schema(implementation = AppointmentDTO.class))),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @GetMapping("/booking-distribution/{distributionId}")
  public ResponseEntity<?> findAllAppointmentByBookingDistributionId(
      @Parameter(description = "BookingDistribution ID", example = "1") @PathVariable("distributionId") Integer id) {
    try {
      AppointmentDTO appointmentDTO = appointmentServiceImp.getAppointmentByBookingDistributionId(
          id);
      return ResponseEntity.ok(appointmentDTO);
    } catch (Exception e) {
      return new ResponseEntity<>("No apointment found!!!", HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Update status appointment")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Update status appointment Successfully!!!"),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @PutMapping("/status/{appointmentId}")
  public ResponseEntity<?> updateStatus(@Parameter(description = "Appointment ID", example = "1") @PathVariable("appointmentId") Integer id){
    try {
        AppointmentDTO appointmentDTO = appointmentServiceImp.changeStatus(id);
        return ResponseEntity.ok(appointmentDTO);
    } catch (Exception e) {
      return new ResponseEntity<>("Change Status fail", HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Get appointment detail by apartmentId")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Load Appoinment detail", content = @Content(schema = @Schema(implementation = AppointmentDTO.class))),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @GetMapping("/apartment/{apartmentId}")
  public ResponseEntity<?> findAllAppointmentByApartmentId(
      @Parameter(description = "Apartment ID", example = "1") @PathVariable("apartmentId") Integer id) {
    try {
      AppointmentDTO appointmentDTO = appointmentServiceImp.findByApartmentId(
          id);
      return ResponseEntity.ok(appointmentDTO);
    } catch (Exception e) {
      return new ResponseEntity<>("No apointment found!!!", HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Update meeting date appointment")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Update meeting date of appointment Successfully!!!"),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @PutMapping("/meeting-date/{appointmentId}")
  public ResponseEntity<?> changeMeetingDate(@Parameter(description = "Appointment ID", example = "1") @PathVariable("appointmentId") Integer id, @RequestBody
      Date meetingDate){
    try {
      AppointmentDTO appointmentDTO = appointmentServiceImp.setMeetingDate(id, meetingDate);
      return ResponseEntity.ok(appointmentDTO);
    } catch (Exception e) {
      return new ResponseEntity<>("Change meetingDate fail", HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Sold appointment")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Update isSold appointment Successfully!!!"),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @PutMapping("/is-sold")
  public ResponseEntity<?> isSold( @RequestParam("appointId") Integer appointId,
      @RequestParam("subId") Integer subId ){
    try {
      AppointmentDTO appointmentDTO = appointmentServiceImp.isSold(appointId, subId);
      return ResponseEntity.ok(appointmentDTO);
    } catch (Exception e) {
      return new ResponseEntity<>("Sold fail", HttpStatus.NOT_FOUND);
    }
  }
}
