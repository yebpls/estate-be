package com.fptu.estate.DTO;

import java.io.Serializable;
import lombok.Data;

@Data
public class ApartmentDTO implements Serializable {
  private Integer id;

  private String apartmentNumber;

  private Integer livingRoom;

  private Integer bedRoom;

  private Integer bathRoom;

  private Integer kitchen;

  private Double price;

  private Integer buildingId;

  private Integer status;

  private Double area;

  private String mainImage;

  private String projectName;

  private String cityName;
}
