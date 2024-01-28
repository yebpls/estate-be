package com.fptu.estate.DTO;

import com.fptu.estate.entities.BookingDistributionEntity;
import com.fptu.estate.entities.SubscriptionEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Date;
import java.util.List;

public class AppointmentDTO {
  private Integer id;

  private Date meetingDate;

  private Date updateDate;

  private Integer appointmentStatus;

  private Integer distributionId;

}
