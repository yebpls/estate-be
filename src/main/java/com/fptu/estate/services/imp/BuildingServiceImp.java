package com.fptu.estate.services.imp;

import com.fptu.estate.DTO.BuildingDTO;
import org.springframework.stereotype.Service;

public interface BuildingServiceImp {
  BuildingDTO findById(Integer id);
}
