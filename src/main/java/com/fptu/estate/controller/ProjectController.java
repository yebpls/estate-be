package com.fptu.estate.controller;

import com.fptu.estate.DTO.ApartmentDTO;
import com.fptu.estate.DTO.ProjectDTO;
import com.fptu.estate.entities.ProjectEntity;
import com.fptu.estate.services.imp.ProjectServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/project")
public class ProjectController {
  @Autowired
  private ProjectServiceImp projectServiceImp;

  @Operation(summary = "Get all projects by Investor ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Load Projects", content = @Content(schema = @Schema(implementation = ProjectDTO.class))),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @GetMapping("/investor/{investor-id}")
  public ResponseEntity<?> getAllProjectByInvestorId (@PathVariable("investor-id") Integer investorId){
    try {
      List<ProjectDTO> list = projectServiceImp.findAllByInvestorId(investorId);
      return ResponseEntity.ok(list);
    } catch (Exception e) {
      return new ResponseEntity<>("No project found!!!", HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Get project details by ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Load Project", content = @Content(schema = @Schema(implementation = ProjectDTO.class))),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @GetMapping("/{project-id}")
  public ResponseEntity<?> getProjctById (@PathVariable("project-id") Integer projectId){
    try {
      ProjectDTO projectDTO = projectServiceImp.findByProjectId(projectId);
      return ResponseEntity.ok(projectDTO);
    } catch (Exception e) {
      return new ResponseEntity<>("No project found!!!", HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Create project")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Create Project Successfully!!!"),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @PostMapping("/create")
  public ResponseEntity<?> createProject(@RequestBody ProjectDTO projectDTO){
    try {
      ProjectDTO projectDTO1 = projectServiceImp.createProject(projectDTO);
      return ResponseEntity.ok(projectDTO1);
    } catch (Exception e) {
      return new ResponseEntity<>("Error!!!", HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Update project by ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Update Project Successfully!!!"),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @PutMapping("/update/{id}")
  public ResponseEntity<?> updateApartmentById(@Parameter(description = "Project ID", example = "1") @PathVariable("id") Integer id, @RequestBody ProjectDTO projectDTO){
    try {

        ProjectDTO projectDTO1 = projectServiceImp.updateProject(id, projectDTO);
        return ResponseEntity.ok(projectDTO1);

    } catch (Exception e) {
      return new ResponseEntity<>("No project found!!!", HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Delete existing project")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Project deleted"),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteProject(@PathVariable("id") Integer id) {
    if (projectServiceImp.deleteProjectById(id)) {
      return new ResponseEntity<>("Project deleted successfully", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Project deleted failed", HttpStatus.BAD_REQUEST);
    }
  }
}
