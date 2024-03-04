package com.fptu.estate.mapper;

import com.fptu.estate.DTO.TransactionDTO;
import com.fptu.estate.entities.AccountEntity;
import com.fptu.estate.entities.TransactionEntity;
import com.fptu.estate.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
  @Autowired
  private ModelMapper modelMapper;
  @Autowired
  private AccountRepository accountRepository;

  public TransactionDTO convertToDTO(TransactionEntity transaction){
    TransactionDTO transactionDTO = modelMapper.map(transaction, TransactionDTO.class);
    transactionDTO.setAccountId(transaction.getAccount().getId());
    return transactionDTO;
  }

  public TransactionEntity revertToEntity(TransactionDTO transactionDTO){
    TransactionEntity transaction = modelMapper.map(transactionDTO, TransactionEntity.class);
    AccountEntity account = accountRepository.findById(transactionDTO.getAccountId()).orElseThrow(null);
    transaction.setAccount(account);
    return transaction;
  }
}
