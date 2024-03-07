package com.fptu.estate.repository;

import com.fptu.estate.entities.ApartmentEntity;
import com.fptu.estate.entities.ApartmentImageEntity;

import java.io.IOException;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface ApartmentImageRepository extends JpaRepository<ApartmentImageEntity, Integer> {
  List<ApartmentImageEntity> findAllByApartment(ApartmentEntity apartment);

  void uploadImage(MultipartFile file, Integer id) throws IOException;
}
