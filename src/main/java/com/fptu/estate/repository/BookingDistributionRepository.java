package com.fptu.estate.repository;

import com.fptu.estate.entities.AgencyEntity;
import com.fptu.estate.entities.BookingDistributionEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDistributionRepository extends JpaRepository<BookingDistributionEntity, Integer> {
  List<BookingDistributionEntity> findAllByAgency(AgencyEntity agency);

  List<BookingDistributionEntity> findAllByBookingStatus(Integer status);

}
