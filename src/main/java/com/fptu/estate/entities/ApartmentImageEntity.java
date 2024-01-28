package com.fptu.estate.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "apartmentimage")
public class ApartmentImageEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "image_url")
  private String imageUrl;

  @ManyToOne
  @JsonManagedReference
  @JoinColumn(name = "apartment_id")
  private ApartmentEntity apartment;
}
