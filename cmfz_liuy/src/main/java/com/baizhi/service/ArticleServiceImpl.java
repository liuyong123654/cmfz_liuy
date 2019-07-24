package com.baizhi.service;

import com.baizhi.dao.ArticleDao;
import com.baizhi.dto.AlbumDto;
import com.baizhi.dto.ArticleDto;
import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao dao;
    @Override
    public ArticleDto queryByPage(Integer page, Integer rows) {
        ArticleDto dto = new ArticleDto();
        dto.setPage(page);
        Integer count = dao.selectCount();
        Integer i = count%rows == 0 ? count/rows : count/rows+1;
        dto.setTotal(i);
        dto.setRecords(count);
        dto.setRows(dao.selectPage(page, rows));
        return dto;
    }

    @Override
    public String insert(Article article) {
        String s = UUID.randomUUID().toString();
        article.setArticleId(s);
        dao.insertArticle(article);
        return s;
    }

    @Override
    public void delete(String articleId) {
        dao.deleteArticle(articleId);
    }

    @Override
    public void update(Article article) {
            dao.updateArticle(article);
    }

    @Override
    public Article queryId(String articleId) {
        Article article = dao.selectId(articleId);
        return article;
    }
}
