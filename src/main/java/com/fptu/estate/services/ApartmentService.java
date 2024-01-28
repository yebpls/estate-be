package com.fptu.estate.services;

import com.fptu.estate.DTO.ApartmentDTO;
import com.fptu.estate.entities.ApartmentEntity;
import com.fptu.estate.mapper.ApartmentMapper;
import com.fptu.estate.repository.ApartmentRepository;
import com.fptu.estate.services.imp.ApartmentServiceImp;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApartmentService implements ApartmentServiceImp {

  @Autowired
  private ApartmentRepository apartmentRepository;

  @Autowired
  private ApartmentMapper apartmentMapper;

  @Override
  public List<ApartmentDTO> findAll() {
    return apartmentRepository.findAllByStatus(1)
        .stream().map(apartmentMapper::convertToDTO).collect(Collectors.toList());
  }

  @Override
  public ApartmentDTO findById(Integer id) {
    ApartmentDTO apartment = apartmentMapper.convertToDTO(apartmentRepository.findByIdAndStatus(id, 1)) ;
    return apartment;
  }
}
