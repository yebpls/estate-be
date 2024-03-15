package com.fptu.estate.mapper;

import com.fptu.estate.DTO.ContractDTO;
import com.fptu.estate.entities.AppointmentEntity;
import com.fptu.estate.entities.ContractEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContractMapper {
  @Autowired
  private ModelMapper modelMapper;

  public ContractDTO convertToDTO(ContractEntity contractEntity) {
    ContractDTO contractDTO = modelMapper.map(contractEntity, ContractDTO.class);
    contractDTO.setAppointmentId(contractEntity.getAppointment().getId());
    return contractDTO;
  }

  public ContractEntity revertToEntity(ContractDTO contractDTO){
    ContractEntity contract = modelMapper.map(contractDTO, ContractEntity.class);
    AppointmentEntity appointment = new AppointmentEntity();
    appointment.setId(contractDTO.getAppointmentId());
    contract.setAppointment(appointment);
    return contract;
  }
}
