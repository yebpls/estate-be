package com.fptu.estate.DTO;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class AccountDTO implements Serializable {
  private Integer id;

  private String password;

  private String email;

  private String avatarUrl;

  private String role;

  private Integer gender;

  private Date createDate;

  private Date updateDate;

  private Double balance;

  private boolean status;

  private Integer cityId;
}
