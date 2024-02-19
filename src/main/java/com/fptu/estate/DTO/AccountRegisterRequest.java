package com.fptu.estate.DTO;

import java.util.Date;
import lombok.Data;

@Data
public class AccountRegisterRequest {
  private String password;

  private String email;

  private Integer cityId;

  private String role;

  private Integer gender;

  private Date dob;

  private String name;

}
