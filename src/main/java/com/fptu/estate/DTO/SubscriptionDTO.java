package com.fptu.estate.DTO;

import java.util.Date;
import lombok.Data;

@Data
public class SubscriptionDTO {
  private Integer id;

  private Date subscribeDate;

  private Date updateDate;

  private Integer subscriptionStatus;

  private Integer customerId;

  private Integer appointmentId;

  private Integer apartmentId;
}
