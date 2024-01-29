package com.fptu.estate.mapper;

import com.fptu.estate.DTO.ApartmentImageDTO;
import com.fptu.estate.entities.ApartmentImageEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApartmentImageMapper {
  @Autowired
  private ModelMapper modelMapper;

  public ApartmentImageDTO convertoDTO (ApartmentImageEntity apartmentImage){
    ApartmentImageDTO apartmentImageDTO = modelMapper.map(apartmentImage, ApartmentImageDTO.class);
    apartmentImageDTO.setApartmentId(apartmentImage.getApartment().getId());
    return apartmentImageDTO;
  }
}
