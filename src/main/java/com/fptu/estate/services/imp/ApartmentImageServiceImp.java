package com.fptu.estate.services.imp;

import com.fptu.estate.DTO.ApartmentImageDTO;
import java.util.List;

public interface ApartmentImageServiceImp {
  List<ApartmentImageDTO> findAllByApartmentId(Integer id);
}
