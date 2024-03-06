package com.fptu.estate.repository;

import com.fptu.estate.DTO.SubscriptionDTO;
import com.fptu.estate.entities.AppointmentEntity;
import com.fptu.estate.entities.CustomerEntity;
import com.fptu.estate.entities.SubscriptionEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Integer> {
  List<SubscriptionEntity> findAllByCustomer(CustomerEntity customer);

  List<SubscriptionEntity> findAllByAppointment(AppointmentEntity appointment);
}
