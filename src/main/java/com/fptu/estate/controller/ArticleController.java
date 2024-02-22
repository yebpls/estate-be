package com.fptu.estate.controller;

import com.fptu.estate.DTO.ArticleDTO;
import com.fptu.estate.DTO.BuildingDTO;
import com.fptu.estate.services.imp.ArticleServiceImp;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/article")
public class ArticleController {
  @Autowired
  private ArticleServiceImp articleServiceImp;

  @Operation(summary = "Get all articles")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Load Article", content = @Content(schema = @Schema(implementation = ArticleDTO.class))),
      @ApiResponse(responseCode = "404", description = "Not found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal error")
  })
  @GetMapping("")
  public ResponseEntity<?> getAllArticles (){
    try {
      List<ArticleDTO> listArticle =articleServiceImp.getAllArticles();
      return ResponseEntity.ok(listArticle);
    } catch (Exception e) {
      return new ResponseEntity<>("No articles found!!!", HttpStatus.NOT_FOUND);
    }
  }

}
