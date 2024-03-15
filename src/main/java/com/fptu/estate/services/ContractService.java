package com.fptu.estate.services;

import com.fptu.estate.DTO.ContractDTO;
import com.fptu.estate.entities.ApartmentEntity;
import com.fptu.estate.entities.AppointmentEntity;
import com.fptu.estate.entities.ContractEntity;
import com.fptu.estate.mapper.ContractMapper;
import com.fptu.estate.repository.ApartmentRepository;
import com.fptu.estate.repository.AppointmentRepository;
import com.fptu.estate.repository.ContractRepository;
import com.fptu.estate.services.imp.ContractServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractService implements ContractServiceImp {
  @Autowired
  private ContractRepository contractRepository;
  @Autowired
  private AppointmentRepository appointmentRepository;
  @Autowired
  private ContractMapper contractMapper;
  @Autowired
  private ApartmentRepository apartmentRepository;

  @Override
  public ContractDTO getByAppointmentId(Integer appointId) {
    AppointmentEntity appointment = appointmentRepository.findById(appointId).orElseThrow(null);
    ContractEntity contract = contractRepository.findByAppointment(appointment);
    return contractMapper.convertToDTO(contract);
  }

  @Override
  public ContractDTO getByApartmentId(Integer apartmentId) {
    ContractEntity contract = contractRepository.findByApartmentId(apartmentId);
    return contractMapper.convertToDTO(contract);
  }

  @Override
  public ContractDTO createContract(ContractDTO contractDTO) {
    ContractEntity contract = contractMapper.revertToEntity(contractDTO);
    contractRepository.save(contract);
    ContractDTO contractDTO1 = contractMapper.convertToDTO(contract);
    return contractDTO1;
  }
}
