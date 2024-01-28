package com.fptu.estate.DTO;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class ProjectDTO implements Serializable {
  private Integer id;

  private String name;

  private Date startDate;

  private Date endDate;

  private int status;

  private Integer investorId;

}
