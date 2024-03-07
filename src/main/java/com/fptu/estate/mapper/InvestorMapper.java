package com.fptu.estate.mapper;

import com.fptu.estate.DTO.InvestorDTO;
import com.fptu.estate.entities.AccountEntity;
import com.fptu.estate.entities.InvestorEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvestorMapper {
  @Autowired
  private ModelMapper modelMapper;

  public InvestorDTO convertToDTO(InvestorEntity investor){
    InvestorDTO investorDTO = modelMapper.map(investor, InvestorDTO.class);
    investorDTO.setAccountId(investor.getAccount().getId());
    return investorDTO;
  }
  public InvestorEntity revertToEntity(InvestorDTO investorDTO){
    InvestorEntity investor = modelMapper.map(investorDTO, InvestorEntity.class);
    AccountEntity account = new AccountEntity();
    account.setId(investorDTO.getAccountId());
    investor.setAccount(account);
    return investor;
  }
}
