package com.fptu.estate.repository;

import com.fptu.estate.entities.ApartmentEntity;
import com.fptu.estate.entities.BuildingEntity;
import com.fptu.estate.entities.ProjectEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends JpaRepository<ApartmentEntity, Integer> {
//  List<ApartmentEntity> findAllByStatus(Integer status);
List<ApartmentEntity> findAllByStatusIn(List<Integer> statuses);

//  ApartmentEntity findByIdAndStatus(Integer id, Integer status);

  List<ApartmentEntity> findAllByBuilding(BuildingEntity building);

  List<ApartmentEntity> findAllByBuildingProject(ProjectEntity project);

}
