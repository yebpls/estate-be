package com.fptu.estate.services.imp;

import com.fptu.estate.DTO.ApartmentDTO;
import java.util.List;

public interface ApartmentServiceImp {
  List<ApartmentDTO> findAll();
  List<ApartmentDTO> findAllByBuildingId(Integer id);
  List<ApartmentDTO> findAllByProjectId(Integer id);

  List<ApartmentDTO> findApartmentsByStatuses();
  ApartmentDTO findById(Integer id);


  ApartmentDTO update(Integer id, ApartmentDTO apartmentDTO);
  ApartmentDTO create(ApartmentDTO apartmentDTO);
  boolean deleteById(Integer id);

  List<ApartmentDTO> findAllApartmentCanBuy();



}
