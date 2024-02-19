package com.fptu.estate.services;

import com.fptu.estate.DTO.AgencyDTO;
import com.fptu.estate.entities.AgencyEntity;
import com.fptu.estate.mapper.AgencyMapper;
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

  @Override
  public AgencyDTO findAgencyById(Integer id) {
    AgencyDTO agencyDTO = agencyMapper.convertToDTO(agencyRepository.findById(id).orElseThrow(null)) ;
    if(agencyDTO !=null){
      return agencyDTO;
    }
    return null;
  }
}
