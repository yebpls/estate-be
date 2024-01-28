package com.fptu.estate.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
@Entity(name = "article")
public class ArticleEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "title")
  private String title;

  @Column(name = "content_body")
  private String contentBody;

  @Column(name = "create_date")
  private Date createDate;

  @Column(name = "update_date")
  private Date updateDate;

  @Column(name = "status")
  private Integer status;

  @ManyToOne
  @JsonManagedReference
  @JoinColumn(name = "city_id")
  private CityEntity city;

  @ManyToOne
  @JsonManagedReference
  @JoinColumn(name = "agency_id")
  private AgencyEntity agency;

}
