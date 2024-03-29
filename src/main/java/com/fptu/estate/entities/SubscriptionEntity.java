package com.fptu.estate.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import lombok.Data;

@Data
@Entity(name = "subscription")
public class SubscriptionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "subscribe_date")
  private Date subscribeDate;

  @Column(name = "update_date")
  private Date updateDate;

  @Column(name = "subscription_status")
  private Integer subscriptionStatus;

  @ManyToOne
  @JsonManagedReference
  @JoinColumn(name = "customer_id")
  private CustomerEntity customer;

  @ManyToOne
  @JsonManagedReference
  @JoinColumn(name = "appointment_id")
  private AppointmentEntity appointment;

  @ManyToOne
  @JsonManagedReference
  @JoinColumn(name = "apartment_id")
  private ApartmentEntity apartment;
}
