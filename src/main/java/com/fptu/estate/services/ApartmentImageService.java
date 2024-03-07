package com.fptu.estate.services;

import com.fptu.estate.DTO.ApartmentImageDTO;
import com.fptu.estate.entities.ApartmentEntity;
import com.fptu.estate.entities.ApartmentImageEntity;
import com.fptu.estate.mapper.ApartmentImageMapper;
import com.fptu.estate.repository.ApartmentImageRepository;
import com.fptu.estate.repository.ApartmentRepository;
import com.fptu.estate.services.imp.ApartmentImageServiceImp;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

  @Override
  public void uploadImage(MultipartFile file, Integer id) {
    if(file == null || file.isEmpty()) {
      throw new IllegalArgumentException("");
    }
    if (id == null) {
      throw new IllegalArgumentException("");
    }

    ApartmentEntity apartment = apartmentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException(""));
    String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

    ApartmentImageEntity image = new ApartmentImageEntity();
    image.setImageUrl(fileName);
    image.setApartment(apartment);
    apartmentImageRepository.save(image);
  }
}
