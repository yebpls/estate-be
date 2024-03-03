package com.fptu.estate.services.imp;

import com.fptu.estate.DTO.BuildingDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BuildingServiceImp {
  List<BuildingDTO> findAll();
  BuildingDTO findById(Integer id);
  List<BuildingDTO> findAllBuilding();
  BuildingDTO createBuilding(BuildingDTO buildingDTO);
  BuildingDTO updateBuilding(BuildingDTO buildingDTO);

  boolean deleteBuilding(Integer id);

  List<BuildingDTO> findAllByProjectId(Integer id);

}
