package com.fptu.estate.services.imp;

import com.fptu.estate.DTO.BuildingDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BuildingServiceImp {
  BuildingDTO findById(Integer id);
  List<BuildingDTO> findAllBuilding();
  BuildingDTO createBuilding(BuildingDTO buildingDTO);
}
