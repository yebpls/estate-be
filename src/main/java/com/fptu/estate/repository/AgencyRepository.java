package com.fptu.estate.repository;

import com.fptu.estate.entities.AccountEntity;
import com.fptu.estate.entities.AgencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends JpaRepository<AgencyEntity, Integer> {
  AgencyEntity findByAccount(AccountEntity account);



}
