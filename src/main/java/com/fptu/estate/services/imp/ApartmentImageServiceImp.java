package com.fptu.estate.services.imp;

import com.fptu.estate.DTO.ApartmentImageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ApartmentImageServiceImp {
  List<ApartmentImageDTO> findAllByApartmentId(Integer id);

  void uploadImage(MultipartFile file, Integer id) throws IOException;
}
