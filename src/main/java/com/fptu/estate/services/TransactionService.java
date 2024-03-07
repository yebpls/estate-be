package com.fptu.estate.services;

import com.fptu.estate.DTO.TransactionDTO;
import com.fptu.estate.entities.TransactionEntity;
import com.fptu.estate.mapper.TransactionMapper;
import com.fptu.estate.repository.AccountRepository;
import com.fptu.estate.repository.TransactionRepository;
import com.fptu.estate.services.imp.TransactionServiceImp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements TransactionServiceImp {
  @Autowired
  private TransactionMapper transactionMapper;

  @Autowired
  private TransactionRepository transactionRepository;
  @Autowired
  private AccountRepository accountRepository;

  @Override
  public List<TransactionDTO> getAllTransaction() {
    return transactionRepository.findAll().stream().map(transactionMapper::convertToDTO).collect(
        Collectors.toList());
  }

  @Override
  public TransactionDTO getDetailById(Integer transId) {
    return transactionMapper.convertToDTO(transactionRepository.findById(transId).orElseThrow(null));
  }

  @Override
  public TransactionDTO createRecharge(Integer accountId, Double amount) {
    TransactionEntity transaction = new TransactionEntity();
    transaction.setAccount(accountRepository.findById(accountId).orElseThrow(null));
    LocalDateTime currentTime = LocalDateTime.now(ZoneId.of("Asia/Bangkok")); // GMT+7 time zone
    transaction.setTransactionDate(currentTime);
    transaction.setStatus(0);
    transaction.setAmount(amount);
    try{
      transactionRepository.save(transaction);
      TransactionDTO transactionDTO = transactionMapper.convertToDTO(transaction);
      return transactionDTO;
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public TransactionDTO createDeposit(Integer accountId, Double amount) {
    TransactionEntity transaction = new TransactionEntity();
    transaction.setAccount(accountRepository.findById(accountId).orElseThrow(null));
    LocalDateTime currentTime = LocalDateTime.now(ZoneId.of("Asia/Bangkok")); // GMT+7 time zone
    transaction.setTransactionDate(currentTime);
    transaction.setStatus(1);
    transaction.setAmount(amount);
    try{
      transactionRepository.save(transaction);
      TransactionDTO transactionDTO = transactionMapper.convertToDTO(transaction);
      return transactionDTO;
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public TransactionDTO createBackToAgency(Integer accountId, Double amount) {
    return null;
  }

  @Override
  public TransactionDTO createBackToInvestor(Integer accountId, Double amount) {
    TransactionEntity transaction = new TransactionEntity();
    transaction.setAccount(accountRepository.findById(accountId).orElseThrow(null));
    LocalDateTime currentTime = LocalDateTime.now(ZoneId.of("Asia/Bangkok")); // GMT+7 time zone
    transaction.setTransactionDate(currentTime);
    transaction.setStatus(2);
    transaction.setAmount(amount);
    try{
      transactionRepository.save(transaction);
      TransactionDTO transactionDTO = transactionMapper.convertToDTO(transaction);
      return transactionDTO;
    } catch (Exception e) {
      return null;
    }
  }
}
