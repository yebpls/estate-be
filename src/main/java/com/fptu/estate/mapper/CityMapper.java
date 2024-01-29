package com.fptu.estate.mapper;

import com.fptu.estate.DTO.CityDTO;
import com.fptu.estate.entities.CityEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {
  @Autowired
  private ModelMapper modelMapper;

  public CityDTO convertToDTO(CityEntity city){
    CityDTO cityDTO = modelMapper.map(city, CityDTO.class);
    return cityDTO;
  }
}
