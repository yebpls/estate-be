package com.fptu.estate.controller;

import com.fptu.estate.DTO.ApartmentDTO;
import com.fptu.estate.entities.CityEntity;
import com.fptu.estate.payload.response.BaseResponse;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/city")
public class CityController {

  @Autowired
  private CityServiceImp cityServiceImp;

  @Operation(summary = "Get All City")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Load City List", content = @Content(schema = @Schema(implementation = ApartmentDTO.class))),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "403", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @GetMapping("")
  public ResponseEntity<?> findAllCity(){
    List<CityEntity> listCity = cityServiceImp.findAllCity();
    BaseResponse baseResponse = new BaseResponse();
    baseResponse.setData(listCity);
    return new ResponseEntity<>(baseResponse, HttpStatus.OK);
  }
}
