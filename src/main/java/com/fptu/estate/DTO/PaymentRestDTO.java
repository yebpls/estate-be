package com.fptu.estate.DTO;

import java.io.Serializable;
import lombok.Data;

@Data
public class PaymentRestDTO implements Serializable {
  private String status;
  private String message;
  private String URL;
}
