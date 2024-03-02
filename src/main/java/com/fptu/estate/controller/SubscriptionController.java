package com.fptu.estate.controller;

import com.fptu.estate.services.imp.SubcriptionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/subscription")
public class SubscriptionController {
  @Autowired
  private SubcriptionServiceImp subcriptionServiceImp;
}
