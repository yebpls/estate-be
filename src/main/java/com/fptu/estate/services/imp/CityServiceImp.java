package com.fptu.estate.services.imp;

import com.fptu.estate.DTO.CityDTO;
import com.fptu.estate.entities.CityEntity;
import java.util.List;

public interface CityServiceImp {
  List<CityDTO> findAllCity();
}
