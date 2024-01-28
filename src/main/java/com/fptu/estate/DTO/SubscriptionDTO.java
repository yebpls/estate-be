package com.fptu.estate.DTO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fptu.estate.entities.ApartmentEntity;
import com.fptu.estate.entities.AppointmentEntity;
import com.fptu.estate.entities.CustomerEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
