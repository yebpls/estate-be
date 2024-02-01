package com.fptu.estate.mapper;

import com.fptu.estate.DTO.AccountDTO;
import com.fptu.estate.entities.AccountEntity;
import com.fptu.estate.entities.CityEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
  @Autowired
  private ModelMapper modelMapper;

  public AccountDTO convertToDTO(AccountEntity account){
    AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);
    accountDTO.setCityId(account.getCity().getId());
    return accountDTO;
  }

  public AccountEntity revertToEntity(AccountDTO accountDTO){
    AccountEntity account = modelMapper.map(accountDTO, AccountEntity.class);
    CityEntity city = new CityEntity();
    city.setId(accountDTO.getCityId());
    account.setCity(city);
    return account;
  }

}
