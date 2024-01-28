package com.fptu.estate.services;

import com.fptu.estate.entities.CityEntity;
import com.fptu.estate.mapper.BuildingMapper;
import com.fptu.estate.repository.CityRepository;
import com.fptu.estate.services.imp.CityServiceImp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class CityService implements CityServiceImp {

  @Autowired
  private CityRepository cityRepository;



  @Override
  public List<CityEntity> findAllCity() {
    List<CityEntity> listCity = cityRepository.findAll();
    return listCity;
  }
}
