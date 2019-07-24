package com.baizhi.service;

import com.baizhi.dto.ArticleDto;
import com.baizhi.entity.Article;

public interface ArticleService {
    //分页查询
    public ArticleDto queryByPage(Integer page,Integer rows);
    //添加文章
    public String insert(Article article);
    //删除文章
    public void delete(String articleId);
    //修改文章
    public void update(Article article);
    //查一个对象
    public Article queryId(String articleId);
}
