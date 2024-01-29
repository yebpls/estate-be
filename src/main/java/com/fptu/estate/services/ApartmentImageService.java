package com.fptu.estate.services;

import com.fptu.estate.DTO.ApartmentImageDTO;
import com.fptu.estate.entities.ApartmentEntity;
import com.fptu.estate.mapper.ApartmentImageMapper;
import com.fptu.estate.repository.ApartmentImageRepository;
import com.fptu.estate.repository.ApartmentRepository;
import com.fptu.estate.services.imp.ApartmentImageServiceImp;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApartmentImageService implements ApartmentImageServiceImp {
  @Autowired
  private ApartmentImageMapper apartmentImageMapper;

  @Autowired
  private ApartmentImageRepository apartmentImageRepository;

  @Autowired
  private ApartmentRepository apartmentRepository;

  @Override
  public List<ApartmentImageDTO> findAllByApartmentId(Integer id) {
    ApartmentEntity apartment = apartmentRepository.findById(id).orElse(null);
    List<ApartmentImageDTO> listApartmentImageDTO = apartmentImageRepository.findAllByApartment(apartment)
        .stream().map(apartmentImageMapper::convertoDTO).collect(Collectors.toList());
    return listApartmentImageDTO;
  }
}
