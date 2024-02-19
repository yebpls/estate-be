package com.fptu.estate.services.imp;

import com.fptu.estate.DTO.AgencyDTO;
import com.fptu.estate.entities.AgencyEntity;

public interface AgencyServiceImp {
  AgencyDTO findAgencyById(Integer id);
}
