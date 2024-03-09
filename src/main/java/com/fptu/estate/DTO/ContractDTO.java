package com.fptu.estate.DTO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fptu.estate.entities.AppointmentEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import lombok.Data;

@Data
public class ContractDTO {
  private Integer id;

  private Double amount;

  private String customerName;

  private String agencyName;

  private Date signDate;

  private String address;

  private Integer appointmentId;

  private String city;
}
