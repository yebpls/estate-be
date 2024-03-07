package com.fptu.estate.DTO;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

@Data
public class TransactionDTO {
  private Integer id;
  private Integer accountId;
  private LocalDateTime transactionDate;
  private Integer status;
  private Double amount;
}
