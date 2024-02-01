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
@Entity(name = "investor")
public class InvestorEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "account_id")
  private AccountEntity account;

  @OneToMany(mappedBy = "investor")
  private List<ProjectEntity> projects;

  public InvestorEntity(Integer id, AccountEntity account) {
    this.id = id;
    this.account = account;
  }

}
