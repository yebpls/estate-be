package com.fptu.estate.DTO;

import lombok.Data;

@Data
public class ApartmentRawFormDTO {
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
}
