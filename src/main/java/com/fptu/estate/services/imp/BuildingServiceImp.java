package com.fptu.estate.services.imp;

import com.fptu.estate.DTO.BuildingDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BuildingServiceImp {
  BuildingDTO findById(Integer id);
  List<BuildingDTO> findAllBuilding();
  void createBuilding(BuildingDTO buildingDTO);
  void updateBuilding(BuildingDTO buildingDTO);

  boolean deleteBuilding(Integer id);
}
