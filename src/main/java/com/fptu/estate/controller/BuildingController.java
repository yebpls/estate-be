package com.fptu.estate.controller;

import com.fptu.estate.DTO.ApartmentDTO;
import com.fptu.estate.DTO.BuildingDTO;
import com.fptu.estate.services.imp.BuildingServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/building")
public class BuildingController {

  @Autowired
  private BuildingServiceImp buildingServiceImp;

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
  @Operation(summary = "Get all building")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Load all Building", content = @Content(schema = @Schema(implementation = BuildingDTO.class))),
          @ApiResponse(responseCode = "404", description = "Not found"),
          @ApiResponse(responseCode = "400", description = "Bad request"),
          @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @GetMapping("/all")
  public ResponseEntity<?> getAllBuildings() {
    try {
      List<BuildingDTO> buildings = buildingServiceImp.findAllBuilding();
      if (!buildings.isEmpty()) {
        return ResponseEntity.ok(buildings);
      } else {
        return new ResponseEntity<>("No buildings found!!!", HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @Operation(summary = "Create new building")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = BuildingDTO.class))),
          @ApiResponse(responseCode = "404", description = "Not found"),
          @ApiResponse(responseCode = "400", description = "Bad request"),
          @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @PostMapping("/create")
  public ResponseEntity<?> createBuilding(@RequestBody BuildingDTO buildingDTO) {
    try {
      buildingServiceImp.createBuilding(buildingDTO);
      return ResponseEntity.ok("Create Successfully");
    } catch (Exception e) {
      return new ResponseEntity<>("No building found!!!", HttpStatus.NOT_FOUND);
    }
  }
  @Operation(summary = "Update apartment by ID")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Update Apartment Successfully!!!"),
          @ApiResponse(responseCode = "404", description = "Not found"),
          @ApiResponse(responseCode = "400", description = "Bad request"),
          @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @PutMapping("/{id}")
  public ResponseEntity<?> updateBuildingById(@Parameter(description = "Apartment ID", example = "1") @PathVariable("id") Integer id, @RequestBody ApartmentDTO apartmentDTO){
    try {
      BuildingDTO building = buildingServiceImp.findById(id);
      if(building != null) {
        buildingServiceImp.updateBuilding(building);
      } else {
        return new ResponseEntity<>("No building found!!!", HttpStatus.NOT_FOUND);
      }
      return ResponseEntity.ok("Update successfully");
    } catch (Exception e) {
      return new ResponseEntity<>("No building found!!!", HttpStatus.NOT_FOUND);
    }
  }
  @Operation(summary = "Delete existing apartment")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Apartment deleted"),
          @ApiResponse(responseCode = "404", description = "Not found"),
          @ApiResponse(responseCode = "400", description = "Bad request"),
          @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteApartment(@PathVariable("id") Integer id) {
    if (buildingServiceImp.deleteBuilding(id)) {
      return new ResponseEntity<>("Building deleted successfully", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Building deleted failed", HttpStatus.BAD_REQUEST);
    }
  }
}
