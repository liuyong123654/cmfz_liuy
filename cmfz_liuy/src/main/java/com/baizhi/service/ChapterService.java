package com.baizhi.service;

import com.baizhi.dto.ChapterDto;
import com.baizhi.entity.Chapter;

public interface ChapterService {
    //分页查询
    public ChapterDto queryByPage(Integer page,Integer rows,String albumId);
    //添加章节
    public String insertById(Chapter chapter);
    //删除章节
    public void deleteById(String chapterId);
    //修改章节
    public void updateById(Chapter chapter);
    //修改路经
    public void modifyDownpath(Chapter chapter);
}
