package com.fptu.estate.services;

import com.fptu.estate.DTO.ProjectDTO;
import com.fptu.estate.entities.InvestorEntity;
import com.fptu.estate.entities.ProjectEntity;
import com.fptu.estate.mapper.ProjectMapper;
import com.fptu.estate.repository.ApartmentRepository;
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
  @Autowired
  private ApartmentRepository apartmentRepository;

  @Override
  public List<ProjectDTO> findAllByInvestorId(Integer id) {
    InvestorEntity investor = investorRepository.findById(id).orElseThrow(null);
    List<ProjectDTO> list = projectRepository.findAllByInvestor(investor).stream()
        .map(projectMapper::convertToDTO).collect(
            Collectors.toList());
    return list;
  }

  @Override
  public ProjectDTO findByProjectId(Integer id) {
    ProjectDTO projectDTO = projectMapper.convertToDTO(
        projectRepository.findById(id).orElseThrow(null));
    return projectDTO;
  }

  @Override
  public ProjectDTO createProject(ProjectDTO projectDTO) {
    projectDTO.setStatus(1);
    ProjectEntity project = projectMapper.revertToEntity(projectDTO);
    try {
      projectRepository.save(project);
      ProjectDTO projectDTO1 = projectMapper.convertToDTO(project);
      return projectDTO1;
    } catch (Exception e) {
      throw new RuntimeException("Error create apartment " + e.getMessage());
    }
  }

  @Override
  public ProjectDTO updateProject(Integer id, ProjectDTO projectDTO) {
    ProjectEntity project = projectRepository.findById(id).orElseThrow(null);
    if (project.getStatus().equals(projectDTO.getStatus())) {
      try {
        project.setName(projectDTO.getName());
        project.setStartDate(projectDTO.getStartDate());
        project.setEndDate(projectDTO.getEndDate());
        project.setImage(projectDTO.getImage());
        projectRepository.save(project);
        ProjectDTO projectDTO1 = projectMapper.convertToDTO(project);
        return projectDTO1;
      } catch (Exception e) {
        throw new RuntimeException("Error update project " + e.getMessage());
      }
    } else {
      boolean allApartmentsValid = apartmentRepository.findAllByProjectId(id).stream()
          .allMatch(apartment -> apartment.getStatus() == 0);
      if (allApartmentsValid) {
        try {
          project.setName(projectDTO.getName());
          project.setStartDate(projectDTO.getStartDate());
          project.setEndDate(projectDTO.getEndDate());
          project.setImage(projectDTO.getImage());
          project.setStatus(projectDTO.getStatus());
          projectRepository.save(project);
          ProjectDTO projectDTO1 = projectMapper.convertToDTO(project);
          return projectDTO1;
        } catch (Exception e){
          throw new RuntimeException("Error update project " + e.getMessage());

        }
      } else {
        throw new RuntimeException("Error update project ");
      }
    }

  }

  @Override
  public boolean deleteProjectById(Integer id) {
    ProjectEntity project = projectRepository.findById(id).orElseThrow(null);
    if (project != null && project.getStatus() == 0) {
      projectRepository.deleteById(id);
      return true;
    }

    return false;
  }
}
