package com.fptu.estate.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/test")
public class TestController {

  @Operation(summary = "Test Server")
  @GetMapping("")
  public ResponseEntity<?> Test() {
    TimeZone timeZone = TimeZone.getTimeZone("GMT+7");
    TimeZone.setDefault(timeZone);
    LocalDateTime localDateTime = LocalDateTime.now();

    return new ResponseEntity<>(localDateTime, HttpStatus.OK);}
}
