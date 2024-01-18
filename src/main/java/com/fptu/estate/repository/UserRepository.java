package com.fptu.estate.repository;

import com.fptu.estate.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
  UserEntity findByEmail(String email);
}
