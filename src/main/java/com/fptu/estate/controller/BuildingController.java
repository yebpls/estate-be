package com.fptu.estate.controller;

import com.fptu.estate.DTO.BuildingDTO;
import com.fptu.estate.services.imp.BuildingServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/building")
public class BuildingController {

  @Autowired
  private BuildingServiceImp buildingServiceImp;

  @Operation(summary = "Get all buildings")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Load Building", content = @Content(schema = @Schema(implementation = BuildingDTO.class))),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @GetMapping("")
  public ResponseEntity<?> getAllBuildingByProjectId (){
    try {
      List<BuildingDTO> list = buildingServiceImp.findAll();
      return ResponseEntity.ok(list);
    } catch (Exception e) {
      return new ResponseEntity<>("No building found!!!", HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Get building details by ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Load Building", content = @Content(schema = @Schema(implementation = BuildingDTO.class))),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @GetMapping("/{id}")
  public ResponseEntity<?> getBuildingById (@PathVariable("id") Integer id){
    try {
      BuildingDTO building = buildingServiceImp.findById(id);
      return ResponseEntity.ok(building);
    } catch (Exception e) {
      return new ResponseEntity<>("No building found!!!", HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Get all buildings by Project ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Load Building", content = @Content(schema = @Schema(implementation = BuildingDTO.class))),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @GetMapping("/project/{project-id}")
  public ResponseEntity<?> getAllBuildingByProjectId (@PathVariable("project-id") Integer id){
    try {
      List<BuildingDTO> list = buildingServiceImp.findAllByProjectId(id);
      return ResponseEntity.ok(list);
    } catch (Exception e) {
      return new ResponseEntity<>("No building found!!!", HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Create building")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Create Building Successfully!!!", content = @Content(schema = @Schema(implementation = BuildingDTO.class))),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @PostMapping("/create")
  public ResponseEntity<?> createBuilding (@RequestBody BuildingDTO buildingDTO){
    try {
      buildingServiceImp.createBuilding(buildingDTO);
      return ResponseEntity.ok("Create successfully!!!");
    } catch (Exception e) {
      return new ResponseEntity<>("Create error!!!", HttpStatus.NOT_FOUND);
    }
  }
}
