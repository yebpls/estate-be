package com.fptu.estate.repository;

import com.fptu.estate.entities.CityEntity;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Integer> {
  List<CityEntity> findAll(Sort sort);

}
