package com.fptu.estate.mapper;

import com.fptu.estate.DTO.AgencyDTO;
import com.fptu.estate.entities.AccountEntity;
import com.fptu.estate.entities.AgencyEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgencyMapper {
  @Autowired
  private ModelMapper modelMapper;

  public AgencyDTO convertToDTO(AgencyEntity agency){
    AgencyDTO agencyDTO = modelMapper.map(agency, AgencyDTO.class);
    agencyDTO.setAccountId(agency.getAccount().getId());
    return agencyDTO;
  }

  public AgencyEntity revertToEntity(AgencyDTO agencyDTO){
    AgencyEntity agency = modelMapper.map(agencyDTO, AgencyEntity.class);
    AccountEntity account = new AccountEntity();
    account.setId(agencyDTO.getId());
    agency.setAccount(account);
    return agency;
  }
}
