package com.fptu.estate.services.imp;

import com.fptu.estate.DTO.AccountDTO;
import com.fptu.estate.DTO.AgencyDTO;

public interface AgencyServiceImp {
  AgencyDTO findAgencyById(Integer id);

  AccountDTO findAgencyByApartmentId(Integer id);

}
