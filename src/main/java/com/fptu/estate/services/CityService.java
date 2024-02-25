package com.fptu.estate.services;

import com.fptu.estate.DTO.CityDTO;
import com.fptu.estate.entities.CityEntity;
import com.fptu.estate.mapper.BuildingMapper;
import com.fptu.estate.mapper.CityMapper;
import com.fptu.estate.repository.CityRepository;
import com.fptu.estate.services.imp.CityServiceImp;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CityService implements CityServiceImp {
  @Autowired
  private CityMapper cityMapper;

  @Autowired
  private CityRepository cityRepository;

  @Override
  public List<CityDTO> findAllCity() {
    Sort sortByName = Sort.by("cityName").ascending();
    return cityRepository.findAll(sortByName).stream()
        .map(cityMapper::convertToDTO)
        .collect(Collectors.toList());
  }
}
