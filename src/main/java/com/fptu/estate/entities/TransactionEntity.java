package com.fptu.estate.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import lombok.Data;

@Data
@Entity(name = "transaction")
public class TransactionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "transaction_date")
  private Date transactionDate;

  @Column(name = "status")
  private Integer status;

  @Column(name = "amount")
  private Double amount;

  @ManyToOne
  @JsonManagedReference
  @JoinColumn(name = "account_id")
  private AccountEntity account;

}
