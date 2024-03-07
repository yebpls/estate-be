package com.fptu.estate.services;

import com.fptu.estate.DTO.ApartmentImageDTO;
import com.fptu.estate.entities.ApartmentEntity;
import com.fptu.estate.entities.ApartmentImageEntity;
import com.fptu.estate.mapper.ApartmentImageMapper;
import com.fptu.estate.repository.ApartmentImageRepository;
import com.fptu.estate.repository.ApartmentRepository;
import com.fptu.estate.services.imp.ApartmentImageServiceImp;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ApartmentImageService implements ApartmentImageServiceImp {

  private final Path root = Paths.get("upload");
  @Autowired
  private ApartmentImageMapper apartmentImageMapper;

  @Autowired
  private ApartmentImageRepository apartmentImageRepository;

  @Autowired
  private ApartmentRepository apartmentRepository;

  @Override
  public List<ApartmentImageDTO> findAllByApartmentId(Integer id) {
    ApartmentEntity apartment = apartmentRepository.findById(id).orElse(null);
    List<ApartmentImageDTO> listApartmentImageDTO = apartmentImageRepository.findAllByApartment(apartment)
        .stream().map(apartmentImageMapper::convertoDTO).collect(Collectors.toList());
    return listApartmentImageDTO;
  }

  @Override
  public void uploadImage(MultipartFile file) {
    try {
      Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
    } catch (IOException e) {
        throw new RuntimeException(e);
    } catch (Exception e) {
      if (e instanceof FileAlreadyExistsException) {
        throw new RuntimeException("A file of that name already exists.");
      }
      throw new RuntimeException(e.getMessage());
    }
  }
}
