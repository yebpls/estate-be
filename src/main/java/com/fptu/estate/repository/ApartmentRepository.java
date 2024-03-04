package com.fptu.estate.repository;

import com.fptu.estate.entities.ApartmentEntity;
import com.fptu.estate.entities.BookingDistributionEntity;
import com.fptu.estate.entities.BuildingEntity;
import com.fptu.estate.entities.ProjectEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends JpaRepository<ApartmentEntity, Integer> {
  List<ApartmentEntity> findAllByStatusIn(List<Integer> statuses);


  @Query(value = "SELECT a.* FROM apartment a JOIN bookingdistribution bd ON a.id = bd.apartment_id WHERE bd.booking_status  = 2",  nativeQuery = true)
  List<ApartmentEntity> findAllByBookingDistributions();


  @Query(value = "SELECT a.*\n"
      + "  FROM apartment a\n"
      + "  JOIN building b ON a.building_id = b.id\n"
      + "  JOIN project p ON b.project_id = p.id\n"
      + "  WHERE p.id = ?1",  nativeQuery = true)
  List<ApartmentEntity> findAllByProjectId(Integer projectId);
  List<ApartmentEntity> findAllByBuilding(BuildingEntity building);

  List<ApartmentEntity> findAllByBuildingProject(ProjectEntity project);

}
