package com.fptu.estate.controller;

import com.fptu.estate.services.imp.ApartmentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/apartment")
public class ApartmentController {
  @Autowired
  private ApartmentServiceImp apartmentServiceImp;

  @GetMapping("")
  public ResponseEntity<?> findAll(){
    return new ResponseEntity<>(apartmentServiceImp.findAll(), HttpStatus.OK);
  }

}
