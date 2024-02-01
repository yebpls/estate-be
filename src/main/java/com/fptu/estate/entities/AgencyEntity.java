package com.fptu.estate.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "agency")
public class AgencyEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "account_id")
  private AccountEntity account;

  @OneToMany(mappedBy = "agency")
  private List<ArticleEntity> articles;

  @OneToMany(mappedBy = "agency")
  private List<BookingDistributionEntity> bookingDistributions;

  public AgencyEntity(Integer id, AccountEntity account) {
    this.id = id;
    this.account = account;
  }

}
