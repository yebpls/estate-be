package com.fptu.estate.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
@Entity(name = "city")
public class CityEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "city_name")
  private String cityName;

  @OneToMany(mappedBy = "city")
  @JsonBackReference
  private List<AccountEntity> accounts;

  @OneToMany(mappedBy = "city")
  @JsonBackReference
  private List<BuildingEntity> buildings;

  @OneToMany(mappedBy = "city")
  @JsonBackReference
  private List<ArticleEntity> articles;
}
