package com.fptu.estate.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
@Entity(name = "appointment")
public class AppointmentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "meeting_date")
  private Date meetingDate;

  @Column(name = "update_date")
  private Date updateDate;

  @Column(name = "appointment_status")
  private Integer appointmentStatus;

  @ManyToOne
  @JsonManagedReference
  @JoinColumn(name = "distribution_id")
  private BookingDistributionEntity distribution;

  @OneToMany(mappedBy = "appointment")
  @JsonBackReference
  private List<SubscriptionEntity> subscriptions;

  @OneToMany(mappedBy = "appointment")
  @JsonBackReference
  private List<ContractImageEntity> contractImages;

  @OneToMany(mappedBy = "appointment")
  @JsonBackReference
  private List<ContractEntity> contracts;

}
