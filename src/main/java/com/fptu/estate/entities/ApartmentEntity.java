package com.fptu.estate.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Data;

@Data
@Entity(name = "apartment")
public class ApartmentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "apartment_number")
  private String apartmentNumber;

  @Column(name = "livingroom")
  private Integer livingRoom;

  @Column(name = "bedroom")
  private Integer bedRoom;

  @Column(name = "bathroom")
  private Integer bathRoom;

  @Column(name = "kitchen")
  private Integer kitchen;

  @Column(name = "price")
  private Double price;

  @Column(name = "status")
  private Integer status;

  @Column(name = "area")
  private Double area;

  @ManyToOne
  @JoinColumn(name = "building_id")
  private BuildingEntity building;

  @OneToMany(mappedBy = "apartment")
  private List<BookingDistributionEntity> bookingDistributions;

  @OneToMany(mappedBy = "apartment")
  private List<SubscriptionEntity> subscriptions;

}
