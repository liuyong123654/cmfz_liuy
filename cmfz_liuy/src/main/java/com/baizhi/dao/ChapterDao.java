package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDao {
    //查询总行数
    public Integer selectCount();
    //分页查询
    public List<Chapter> selectPage(@Param("Page") Integer Page, @Param("Size") Integer Size,@Param("albumId")String albumId);
    //添加章节
    public void insertChapter(Chapter chapter);
    //删除章节
    public void deleteById(String chapterId);
    //修改章节
    public void updateById(Chapter chapter);
    //修改路经
    public void updateDownpath(Chapter chapter);
}
