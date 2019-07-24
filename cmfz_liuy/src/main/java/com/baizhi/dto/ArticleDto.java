package com.baizhi.dto;

import com.baizhi.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private Integer page;
    private Integer total;
    private Integer records;
    private List<Article> rows;

}
