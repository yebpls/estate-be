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
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "account")
public class AccountEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "password")
  private String password;

  @Column(name = "email")
  private String email;

  @Column(name = "avatar_url")
  private String avatarUrl;

  @Column(name = "role")
  private String role;

  @Column(name = "gender")
  private Integer gender;

  @Column(name = "dob")
  private Date dob;

  @Column(name = "create_date")
  private Date createDate;

  @Column(name = "update_date")
  private Date updateDate;

  @Column(name = "balance")
  private Double balance;

  @Column(name = "status")
  private Integer status;


  @ManyToOne
  @JsonManagedReference
  @JoinColumn(name = "city_id")
  private CityEntity city;

  @OneToMany(mappedBy = "account")
  @JsonBackReference
  private List<AgencyEntity> agencies;

  @OneToMany(mappedBy = "account")
  @JsonBackReference
  private List<InvestorEntity> investors;

  @OneToMany(mappedBy = "account")
  @JsonBackReference
  private List<CustomerEntity> customers;

  private String name;


  public AccountEntity(Integer id, String password, String email, String avatarUrl, String role,
      Integer gender, Date dob, Date createDate, Date updateDate, Double balance, Integer status,
      CityEntity city, String name) {
    this.id = id;
    this.password = password;
    this.email = email;
    this.avatarUrl = avatarUrl;
    this.role = role;
    this.gender = gender;
    this.dob = dob;
    this.createDate = createDate;
    this.updateDate = updateDate;
    this.balance = balance;
    this.status = status;
    this.city = city;
    this.name = name;
  }



}
