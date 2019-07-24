package com.baizhi.dto;


import com.baizhi.entity.Chapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapterDto {
    private Integer page;
    private Integer total;
    private Integer records;
    private List<Chapter> rows;
}
