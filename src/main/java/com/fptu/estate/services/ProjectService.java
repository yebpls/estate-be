package com.fptu.estate.services;

import com.fptu.estate.DTO.ProjectDTO;
import com.fptu.estate.entities.InvestorEntity;
import com.fptu.estate.entities.ProjectEntity;
import com.fptu.estate.mapper.ProjectMapper;
import com.fptu.estate.repository.InvestorRepository;
import com.fptu.estate.repository.ProjectRepository;
import com.fptu.estate.services.imp.InvestorServiceImp;
import com.fptu.estate.services.imp.ProjectServiceImp;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService implements ProjectServiceImp {
  @Autowired
  private ProjectMapper projectMapper;

  @Autowired
  private ProjectRepository projectRepository;

  @Autowired
  private InvestorRepository investorRepository;

  @Override
  public List<ProjectDTO> findAllByInvestorId(Integer id) {
    InvestorEntity investor = investorRepository.findById(id).orElseThrow(null);
    List<ProjectDTO> list = projectRepository.findAllByInvestor(investor).stream().map(projectMapper::convertToDTO).collect(
        Collectors.toList());
    return list;
  }

  @Override
  public ProjectDTO findByProjectId(Integer id) {
    ProjectDTO projectDTO = projectMapper.convertToDTO(projectRepository.findById(id).orElseThrow(null));
    return projectDTO;
  }

  @Override
  public void createProject(ProjectDTO projectDTO) {
    projectDTO.setStatus(1);
    ProjectEntity project = projectMapper.revertToEntity(projectDTO);
    try{
      projectRepository.save(project);
    } catch (Exception e){
      throw new RuntimeException("Error create apartment " + e.getMessage());
    }
  }

  @Override
  public void updateProject(Integer id, ProjectDTO projectDTO) {
    ProjectEntity project = projectMapper.revertToEntity(projectDTO);
    project.setId(id);
    try{
      projectRepository.save(project);
    } catch (Exception e){
      throw new RuntimeException("Error update apartment " + e.getMessage());
    }
  }

  @Override
  public boolean deleteProjectById(Integer id) {
    ProjectEntity project = projectRepository.findById(id).orElseThrow(null);
    if(project != null){
      projectRepository.deleteById(id);
      return true;
    }

    return false;
  }
}
