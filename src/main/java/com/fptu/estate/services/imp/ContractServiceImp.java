package com.fptu.estate.services.imp;

import com.fptu.estate.DTO.ContractDTO;
import org.springframework.data.jpa.repository.Query;

public interface ContractServiceImp {
  ContractDTO getByAppointmentId(Integer appointId);

  ContractDTO getByApartmentId(Integer apartmentId);

  ContractDTO createContract(ContractDTO contractDTO);
}
