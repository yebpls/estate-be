package com.fptu.estate.repository;

import com.fptu.estate.entities.InvestorEntity;
import com.fptu.estate.entities.ProjectEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer>{
  List<ProjectEntity> findAllByInvestor(InvestorEntity investor);
}

