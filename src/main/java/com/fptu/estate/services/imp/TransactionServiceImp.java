package com.fptu.estate.services.imp;

import com.fptu.estate.DTO.TransactionDTO;
import com.fptu.estate.entities.TransactionEntity;
import java.util.List;

public interface TransactionServiceImp {
  List<TransactionDTO> getAllTransaction();
  TransactionDTO getDetailById(Integer transId);
  TransactionDTO createRecharge(Integer accountId, Double amount);
  TransactionDTO createDeposit(Integer accountId, Double amount);
  TransactionDTO createBackToAgency(Integer accountId, Double amount);
  TransactionDTO createBackToInvestor(Integer accountId, Double amount);
  TransactionDTO createInvestorPay(Integer accountId, Double amount);
}
