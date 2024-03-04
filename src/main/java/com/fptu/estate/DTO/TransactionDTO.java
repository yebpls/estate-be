package com.fptu.estate.DTO;

import java.util.Date;
import lombok.Data;

@Data
public class TransactionDTO {
  private Integer id;
  private Integer accountId;
  private Date transactionDate;
  private Integer status;
  private Double amount;
}
