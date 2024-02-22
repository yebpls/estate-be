package com.fptu.estate.services;

import com.fptu.estate.DTO.ArticleDTO;
import com.fptu.estate.mapper.ArticleMapper;
import com.fptu.estate.repository.ArticleRepository;
import com.fptu.estate.services.imp.ArticleServiceImp;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService implements ArticleServiceImp {
  @Autowired
  private ArticleRepository articleRepository;

  @Autowired
  private ArticleMapper articleMapper;

  @Override
  public List<ArticleDTO> getAllArticles() {
    return articleRepository.findAll().stream().map(articleMapper::convertToDTO).collect(Collectors.toList());
  }
}
