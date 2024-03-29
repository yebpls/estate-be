package com.fptu.estate.services;

import com.fptu.estate.DTO.AccountDTO;
import com.fptu.estate.DTO.AgencyDTO;
import com.fptu.estate.entities.AccountEntity;
import com.fptu.estate.entities.AgencyEntity;
import com.fptu.estate.mapper.AccountMapper;
import com.fptu.estate.mapper.AgencyMapper;
import com.fptu.estate.repository.AccountRepository;
import com.fptu.estate.repository.AgencyRepository;
import com.fptu.estate.services.imp.AgencyServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgencyService implements AgencyServiceImp {
  @Autowired
  private AgencyRepository agencyRepository;

  @Autowired
  private AgencyMapper agencyMapper;
  @Autowired
  private AccountMapper accountMapper;
  @Autowired
  private AccountRepository accountRepository;

  @Override
  public AgencyDTO findAgencyById(Integer id) {
    AccountEntity account = accountRepository.findByIdAndStatus(id, true);
    AgencyDTO agencyDTO = agencyMapper.convertToDTO(agencyRepository.findByAccount(account)) ;
    if(agencyDTO !=null){
      return agencyDTO;
    }
    return null;
  }

  @Override
  public AccountDTO findAgencyByApartmentId(Integer id) {
    AccountEntity account = accountRepository.findAgencyInforByApartmentId(id);
    AgencyEntity agency = new AgencyEntity();
    agency.setAccount(account);
    if(account != null){
      return accountMapper.convertToDTO(agency.getAccount());

    }
    return null;
  }
}
