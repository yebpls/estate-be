package com.fptu.estate.services;

import com.fptu.estate.DTO.ApartmentDTO;
import com.fptu.estate.entities.ApartmentEntity;
import com.fptu.estate.entities.BookingDistributionEntity;
import com.fptu.estate.entities.BuildingEntity;
import com.fptu.estate.entities.ProjectEntity;
import com.fptu.estate.mapper.ApartmentMapper;
import com.fptu.estate.repository.ApartmentImageRepository;
import com.fptu.estate.repository.ApartmentRepository;
import com.fptu.estate.repository.BookingDistributionRepository;
import com.fptu.estate.repository.BuildingRepository;
import com.fptu.estate.repository.ProjectRepository;
import com.fptu.estate.services.imp.ApartmentServiceImp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
  @Autowired
  private BookingDistributionRepository bookingDistributionRepository;


  private Logger log = LoggerFactory.getLogger(ApartmentService.class);

  @Override
  public List<ApartmentDTO> findAll() {
    List<ApartmentEntity> apartments = apartmentRepository.findAll();
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
  public List<ApartmentDTO> findApartmentsByStatuses() {
    List<Integer> statuses = Arrays.asList(1, 2);
    // Use the repository method to find apartments
    return apartmentRepository.findAllByStatusIn(statuses).stream().map(apartmentMapper::convertToDTO).collect(
        Collectors.toList());
  }

  @Override
  public ApartmentDTO findById(Integer id) {
    ApartmentDTO apartment = apartmentMapper.convertToDTO(apartmentRepository.findById(id).orElseThrow(null)) ;
    return apartment;
  }


  @Override
  public ApartmentDTO update(Integer id, ApartmentDTO apartmentDTO) {
//    ApartmentEntity apartment = apartmentRepository.findById(id).orElseThrow(null);
//    try {
//      apartment.setApartmentNumber(apartmentDTO.getApartmentNumber());
//      apartment.setLivingRoom(apartmentDTO.getLivingRoom());
//      apartment.setBedRoom(apartmentDTO.getBedRoom());
//      apartment.setBathRoom(apartmentDTO.getBathRoom());
//      apartment.setKitchen(apartmentDTO.getKitchen());
//      apartment.setPrice(apartmentDTO.getPrice());
//      BuildingEntity building = new BuildingEntity();
//      building.setId(apartmentDTO.getBuildingId());
//      apartment.setBuilding(building);
//      apartment.setStatus(apartmentDTO.getStatus());
//      apartment.setMainImage(apartmentDTO.getMainImage());
//      apartment.setArea(apartmentDTO.getArea());
//      apartmentRepository.save(apartment);
//      ApartmentDTO apartmentDTO1 = apartmentMapper.convertToDTO(apartment);
//      return apartmentDTO1;
//    } catch (Exception e) {
//      throw new RuntimeException("Error update apartment " + e.getMessage());
//    }
    log.info("Starting update for Apartment with ID: {}", id);
    ApartmentEntity apartment = apartmentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Apartment not found with id: " + id));
    log.info("Apartment found: {}", apartment);

    try {
      log.info("Updating apartment details");
      apartment.setApartmentNumber(apartmentDTO.getApartmentNumber());
      apartment.setLivingRoom(apartmentDTO.getLivingRoom());
      apartment.setBedRoom(apartmentDTO.getBedRoom());
      apartment.setBathRoom(apartmentDTO.getBathRoom());
      apartment.setKitchen(apartmentDTO.getKitchen());
      apartment.setPrice(apartmentDTO.getPrice());

      BuildingEntity building = buildingRepository.findById(apartmentDTO.getBuildingId()).orElseThrow(null);
      apartment.setBuilding(building);

      apartment.setStatus(apartmentDTO.getStatus());
      apartment.setMainImage(apartmentDTO.getMainImage());
      apartment.setArea(apartmentDTO.getArea());

      apartmentRepository.save(apartment);
      log.info("Apartment saved successfully");

      ApartmentDTO apartmentDTO1 = apartmentMapper.convertToDTO(apartment);
      log.info("Successfully converted ApartmentEntity to ApartmentDTO: {}", apartmentDTO1);
      return apartmentDTO1;
    } catch (Exception e) {
      log.error("Error updating apartment with ID: {}: {}", id, e.getMessage(), e);
      throw new RuntimeException("Error update apartment " + e.getMessage());
    }
  }

  @Override
  public ApartmentDTO create(ApartmentDTO apartmentDTO) {
//    apartmentDTO.setStatus(1);
//    ApartmentEntity apartment = apartmentMapper.revertToEntity(apartmentDTO);
//    log.error("Error at map revert");
//    try {
//      apartmentRepository.save(apartment);
//      log.error("Error at repo");
//      ApartmentDTO apartmentDTO1 = apartmentMapper.convertToDTO(apartment);
//      log.error("Error at map");
//      return apartmentDTO1;
//    } catch (Exception e) {
//      throw new RuntimeException("Error create apartment " + e.getMessage());
//    }
    apartmentDTO.setStatus(1);
    ApartmentEntity apartment = null;
    try {
      apartment = apartmentMapper.revertToEntity(apartmentDTO);
      log.info("Successfully mapped ApartmentDTO to ApartmentEntity: {}", apartment);
    } catch (Exception e) {
      log.error("Error during mapping from ApartmentDTO to ApartmentEntity", e);
      throw e; // Re-throw the exception or handle it as appropriate
    }

    try {
      apartmentRepository.save(apartment);
      log.info("Apartment saved to repository: {}", apartment);
      ApartmentDTO apartmentDTO1 = apartmentMapper.convertToDTO(apartment);
      log.info("Successfully converted ApartmentEntity to ApartmentDTO: {}", apartmentDTO1);
      return apartmentDTO1;
    } catch (Exception e) {
      log.error("Error create apartment: ", e);
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

  @Override
  public List<ApartmentDTO> findAllApartmentCanBuy() {
//    List<BookingDistributionEntity> listBooking = bookingDistributionRepository.findAllByBookingStatus(2);
    return apartmentRepository.findAllByBookingDistributions().stream()
        .map(apartmentMapper::convertToDTO).collect(
            Collectors.toList());

  }
}
