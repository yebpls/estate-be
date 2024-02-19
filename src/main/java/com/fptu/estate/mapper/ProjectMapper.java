package com.fptu.estate.mapper;

import com.fptu.estate.DTO.ProjectDTO;
import com.fptu.estate.entities.InvestorEntity;
import com.fptu.estate.entities.ProjectEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
  @Autowired
  private ModelMapper modelMapper;

  public ProjectDTO convertToDTO(ProjectEntity project){
    ProjectDTO projectDTO = modelMapper.map(project, ProjectDTO.class);
    projectDTO.setInvestorId(project.getInvestor().getId());
    return projectDTO;
  }

  public ProjectEntity revertToEntity(ProjectDTO projectDTO){
    ProjectEntity project = modelMapper.map(projectDTO, ProjectEntity.class);
    InvestorEntity investor = new InvestorEntity();
    investor.setId(projectDTO.getInvestorId());
    return project;
  }
}
