package com.fptu.estate.services.imp;

import com.fptu.estate.DTO.ProjectDTO;
import java.util.List;

public interface ProjectServiceImp {
  List<ProjectDTO> findAllByInvestorId(Integer id);
  ProjectDTO findByProjectId(Integer id);
  void createProject(ProjectDTO projectDTO);
  void updateProject(Integer id, ProjectDTO projectDTO);
  boolean deleteProjectById(Integer id);

}
