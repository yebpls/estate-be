package com.fptu.estate.controller;

import com.fptu.estate.entities.CityEntity;
import com.fptu.estate.payload.response.BaseResponse;
import com.fptu.estate.services.imp.CityServiceImp;
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

  @GetMapping("")
  public ResponseEntity<?> findAllCity(){
    List<CityEntity> listCity = cityServiceImp.findAllCity();
    BaseResponse baseResponse = new BaseResponse();
    baseResponse.setData(listCity);
    return new ResponseEntity<>(baseResponse, HttpStatus.OK);
  }
}
