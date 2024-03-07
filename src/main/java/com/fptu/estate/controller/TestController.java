package com.fptu.estate.controller;

import com.fptu.estate.services.imp.ApartmentImageServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/test")
public class TestController {

  private ApartmentImageServiceImp apartmentImageService;

  @Operation(summary = "Test Server")
  @GetMapping("")
  public ResponseEntity<?> Test() {
    TimeZone timeZone = TimeZone.getTimeZone("GMT+7");
    TimeZone.setDefault(timeZone);
    LocalDateTime localDateTime = LocalDateTime.now();

    return new ResponseEntity<>(localDateTime, HttpStatus.OK);}
  @PostMapping("/upload")
  public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
    try {
      apartmentImageService.uploadImage(file);
      return ResponseEntity.status(HttpStatus.CREATED).body("Image uploaded successfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image: " + e.getMessage());
    }
  }
}
