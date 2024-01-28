package com.fptu.estate.entities;

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
@Entity(name = "bookingdistribution")
public class BookingDistributionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "create_date")
  private Date createDate;

  @Column(name = "update_date")
  private Date updateDate;

  @Column(name = "distribution_date")
  private Date distributionDate;

  @Column(name = "expire_distribution_date")
  private Date expireDistributionDate;

  @Column(name = "booking_fee")
  private Double bookingFee;

  @Column(name = "booking_status")
  private Integer bookingStatus;

  @OneToMany(mappedBy = "distribution")
  private List<AppointmentEntity> appointments;

  @ManyToOne
  @JoinColumn(name = "agency_id")
  private AgencyEntity agency;

  @ManyToOne
  @JoinColumn(name = "apartment_id")
  private ApartmentEntity apartment;
}
