package com.fptu.estate.services.imp;

import com.fptu.estate.DTO.ApartmentDTO;
import javassist.NotFoundException;

import java.util.List;

public interface ApartmentServiceImp {
  List<ApartmentDTO> findAll();
  ApartmentDTO findById(Integer id);
  ApartmentDTO createApartment(ApartmentDTO newApartment);

  ApartmentDTO updateApartment(ApartmentDTO updatedApartment) throws NotFoundException;
  void deleteApartment(Integer id) throws NotFoundException;
}
