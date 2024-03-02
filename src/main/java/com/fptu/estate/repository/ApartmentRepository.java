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


  List<ApartmentEntity> findAllByBuilding(BuildingEntity building);

  List<ApartmentEntity> findAllByBuildingProject(ProjectEntity project);

}
