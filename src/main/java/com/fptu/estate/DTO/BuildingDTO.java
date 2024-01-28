package com.fptu.estate.DTO;

import java.io.Serializable;
import lombok.Data;

@Data
public class BuildingDTO implements Serializable {
  private Integer id;

  private String buildingName;

  private String address;

  private Integer cityId;

  private Integer projectId;
}
