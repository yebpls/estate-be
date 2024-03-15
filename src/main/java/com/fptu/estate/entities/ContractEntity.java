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
@Entity(name = "contract")
public class ContractEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "amount")
  private Double amount;

  @Column(name = "customer_name")
  private String customerName;

  @Column(name = "agency_name")
  private String agencyName;

  @Column(name = "sign_date")
  private Date signDate;

  @Column(name = "address")
  private String address;

  @Column(name = "city")
  private String city;

  @ManyToOne
  @JoinColumn(name = "appointment_id")
  @JsonManagedReference
  private AppointmentEntity appointment;
}
