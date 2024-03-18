package com.fptu.estate.DTO;

import java.util.Date;
import lombok.Data;

@Data
public class CustomerDetailDTO {
  private Integer id;

  private Integer accountId;

  private String password;

  private String email;

  private String avatarUrl;

  private String role;

  private Integer gender;

  private Double balance;

  private Date dob;

  private boolean status;

  private Integer cityId;

  private String name;

  private String phoneNumber;

}
