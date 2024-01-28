package com.fptu.estate.DTO;

import java.util.Date;
import lombok.Data;

@Data
public class BookingDistributionDTO {
  private Integer id;

  private Date createDate;

  private Date updateDate;

  private Date distributionDate;

  private Date expireDistributionDate;

  private Double bookingFee;

  private Integer bookingStatus;

  private Integer agencyId;

  private Integer apartmentId;
}
