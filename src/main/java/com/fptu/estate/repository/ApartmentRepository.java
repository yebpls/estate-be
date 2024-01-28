package com.fptu.estate.repository;

import com.fptu.estate.entities.ApartmentEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends JpaRepository<ApartmentEntity, Integer> {
  List<ApartmentEntity> findAllByStatus(Integer status);

  ApartmentEntity findByIdAndStatus(Integer id, Integer status);
}
