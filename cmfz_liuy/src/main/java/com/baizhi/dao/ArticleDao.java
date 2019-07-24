package com.baizhi.dao;

import com.baizhi.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {
    //查询总行数
    public Integer selectCount();
    //分页查询
    public List<Article> selectPage(@Param("page") Integer page, @Param("size") Integer size);
    //添加文章
    public void insertArticle(Article article);
    //删除文章
    public void deleteArticle(String articleId);
    //修改文章
    public void updateArticle(Article article);
    //查询一个对象
    public Article selectId(String articleId);
}
