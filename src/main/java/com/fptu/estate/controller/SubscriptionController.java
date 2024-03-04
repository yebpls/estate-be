package com.fptu.estate.controller;

import com.fptu.estate.DTO.ApartmentDTO;
import com.fptu.estate.DTO.SubscriptionDTO;
import com.fptu.estate.services.imp.SubcriptionServiceImp;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/subscription")
public class SubscriptionController {
  @Autowired
  private SubcriptionServiceImp subcriptionServiceImp;

  @Operation(summary = "Get all subscription")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Load Subscription List", content = @Content(schema = @Schema(implementation = SubscriptionDTO.class))),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @GetMapping("")
  public ResponseEntity<?> findAll(){
    List<SubscriptionDTO> list = subcriptionServiceImp.getAll();
    if (list.isEmpty()) {
      return new ResponseEntity<>("No subscriptions found!!!", HttpStatus.NOT_FOUND);
    } else {
      return ResponseEntity.ok(list);
    }
  }

  @Operation(summary = "Get subscription details by ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Load Subscription", content = @Content(schema = @Schema(implementation = SubscriptionDTO.class))),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @GetMapping("/{subId}")
  public ResponseEntity<?> findById(@Parameter(description = "Subscription ID", example = "1") @PathVariable("subId") Integer id){
    try {
      SubscriptionDTO subscriptionDTO = subcriptionServiceImp.findById(id);
      return ResponseEntity.ok(subscriptionDTO);
    } catch (Exception e) {
      return new ResponseEntity<>("No apartment found!!!", HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Get subscription details by ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Load Subscription", content = @Content(schema = @Schema(implementation = SubscriptionDTO.class))),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @GetMapping("/customer/{cusId}")
  public ResponseEntity<?> getAllByCustomerId(@Parameter(description = "Customer ID", example = "1") @PathVariable("cusId") Integer id){
    List<SubscriptionDTO> list = subcriptionServiceImp.findAllByCustomerId(id);
    if (list.isEmpty()) {
      return new ResponseEntity<>("No subscriptions found!!!", HttpStatus.NOT_FOUND);
    } else {
      return ResponseEntity.ok(list);
    }
  }

  @Operation(summary = "Create subscription")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Create Subscription!!!"),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @PostMapping("/create")
  public ResponseEntity<?> createSubscription(@RequestBody SubscriptionDTO subscriptionDTO){
    try {
      SubscriptionDTO subscriptionDTO1 = subcriptionServiceImp.createSubscription(subscriptionDTO);
      return ResponseEntity.ok(subscriptionDTO1);
    } catch (Exception e) {
      return new ResponseEntity<>("No apartment found!!!", HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Update status subscription by ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Update Status Subscription Successfully!!!"),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @PutMapping("/{subId}/{status}")
  public ResponseEntity<?> changeStatus(@Parameter(description = "Subscription ID", example = "1") @PathVariable("subId") Integer subId, @Parameter(description = "Status", example = "1") @PathVariable("status") Integer status){
    try {
      SubscriptionDTO subscriptionDTO = subcriptionServiceImp.changeStatus(subId, status);
      return ResponseEntity.ok(subscriptionDTO);
    } catch (Exception e) {
      return new ResponseEntity<>("Update status fail", HttpStatus.NOT_FOUND);
    }
  }

}
