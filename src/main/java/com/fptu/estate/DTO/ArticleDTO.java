package com.fptu.estate.DTO;

import java.util.Date;
import lombok.Data;

@Data
public class ArticleDTO {
  private Integer id;

  private String title;

  private String contentBody;

  private Date createDate;

  private Date updateDate;

  private Integer status;

  private Integer cityId;

  private Integer agencyId;

  private String mainImage;
}
