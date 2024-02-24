package com.fptu.estate.services.imp;

import com.fptu.estate.DTO.BuildingDTO;
import java.util.List;
import org.springframework.stereotype.Service;

public interface BuildingServiceImp {
  List<BuildingDTO> findAll();
  BuildingDTO findById(Integer id);
  List<BuildingDTO> findAllByProjectId(Integer id);
  void createBuilding(BuildingDTO buildingDTO);
}
