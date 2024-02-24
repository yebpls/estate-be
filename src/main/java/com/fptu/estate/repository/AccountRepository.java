package com.fptu.estate.repository;

import com.fptu.estate.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
  AccountEntity findByEmail(String email);

  AccountEntity findByIdAndStatus(Integer id, boolean status);

  @Query(value = "SELECT acc.* FROM account AS acc JOIN agency AS ag ON acc.id = ag.account_id JOIN bookingdistribution AS bd ON ag.id = bd.agency_id JOIN apartment AS apt ON bd.apartment_id = apt.id WHERE apt.id = ?1", nativeQuery = true)
  AccountEntity findAgencyInforByApartmentId(Integer apartmentId);

}
