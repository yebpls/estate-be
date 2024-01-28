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
@Entity(name = "building")
public class BuildingEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "building_name")
  private String buildingName;

  @Column(name = "address")
  private String address;

  @ManyToOne
  @JoinColumn(name = "city_id")
  private CityEntity city;

  @ManyToOne
  @JoinColumn(name = "project_id")
  private ProjectEntity project;

  @OneToMany(mappedBy = "building")
  private List<ApartmentEntity> apartments;
}
