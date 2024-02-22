package com.fptu.estate.services;

import com.fptu.estate.DTO.ApartmentDTO;
import com.fptu.estate.entities.ApartmentEntity;
import com.fptu.estate.entities.BuildingEntity;
import com.fptu.estate.entities.ProjectEntity;
import com.fptu.estate.mapper.ApartmentMapper;
import com.fptu.estate.repository.ApartmentImageRepository;
import com.fptu.estate.repository.ApartmentRepository;
import com.fptu.estate.repository.BuildingRepository;
import com.fptu.estate.repository.ProjectRepository;
import com.fptu.estate.services.imp.ApartmentServiceImp;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApartmentService implements ApartmentServiceImp {

  @Autowired
  private ApartmentRepository apartmentRepository;

  @Autowired
  private ApartmentMapper apartmentMapper;

  @Autowired
  private ApartmentImageRepository apartmentImageRepository;
  @Autowired
  private BuildingRepository buildingRepository;
  @Autowired
  private ProjectRepository projectRepository;

  @Override
  public List<ApartmentDTO> findAll() {
    List<ApartmentEntity> apartments = apartmentRepository.findAllByStatus(1);
    return apartments.stream()
        .map(apartmentMapper::convertToDTO)
        .collect(Collectors.toList());
//    return apartmentRepository.findAllByStatus(1)
//        .stream().map(apartmentMapper::convertToDTO).collect(Collectors.toList());
  }

  @Override
  public List<ApartmentDTO> findAllByBuildingId(Integer id) {
    BuildingEntity building = buildingRepository.findById(id).orElseThrow(null);
    List<ApartmentDTO> list = apartmentRepository.findAllByBuilding(building).stream().map(apartmentMapper::convertToDTO).collect(
        Collectors.toList());
    return list;
  }

  @Override
  public List<ApartmentDTO> findAllByProjectId(Integer id) {
    ProjectEntity project = projectRepository.findById(id).orElseThrow(null);
    List<ApartmentDTO> list = apartmentRepository.findAllByBuildingProject(project).stream().map(apartmentMapper::convertToDTO).collect(
        Collectors.toList());
    return list;
  }


  @Override
  public ApartmentDTO findById(Integer id) {
    ApartmentDTO apartment = apartmentMapper.convertToDTO(apartmentRepository.findByIdAndStatus(id, 1)) ;
    return apartment;
  }

  @Override
  public void update(ApartmentDTO apartmentDTO) {
    ApartmentEntity apartment = apartmentMapper.revertToEntity(apartmentDTO);
    try {
      apartmentRepository.save(apartment);
    } catch (Exception e) {
      throw new RuntimeException("Error update apartment " + e.getMessage());
    }
  }

  @Override
  public void create(ApartmentDTO apartmentDTO) {
    ApartmentEntity apartment = apartmentMapper.revertToEntity(apartmentDTO);
    try {
      apartmentRepository.save(apartment);
    } catch (Exception e) {
      throw new RuntimeException("Error create apartment " + e.getMessage());
    }
  }

  @Override
  public boolean deleteById(Integer id) {
    ApartmentEntity apartment = apartmentRepository.findById(id).orElseThrow();
    if(apartment != null) {
      apartmentRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }
}
