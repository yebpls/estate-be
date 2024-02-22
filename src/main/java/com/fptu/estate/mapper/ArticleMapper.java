package com.fptu.estate.mapper;

import com.fptu.estate.DTO.ArticleDTO;
import com.fptu.estate.entities.AgencyEntity;
import com.fptu.estate.entities.ArticleEntity;
import com.fptu.estate.entities.CityEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {
  @Autowired
  private ModelMapper modelMapper;

  public ArticleDTO convertToDTO(ArticleEntity article){
    ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);
    articleDTO.setCityId(article.getCity().getId());
    articleDTO.setAgencyId(article.getAgency().getId());
    return articleDTO;
  }

  public ArticleEntity revertToEntity(ArticleDTO articleDTO){
    ArticleEntity article = modelMapper.map(articleDTO, ArticleEntity.class);
    CityEntity city = new CityEntity();
    city.setId(articleDTO.getCityId());
    article.setCity(city);
    AgencyEntity agency = new AgencyEntity();
    agency.setId(articleDTO.getAgencyId());
    article.setAgency(agency);
    return article;
  }

}
