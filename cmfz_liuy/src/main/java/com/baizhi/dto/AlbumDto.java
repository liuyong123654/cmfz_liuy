package com.baizhi.dto;

import com.baizhi.entity.Album;
import com.baizhi.entity.Carousel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AlbumDto implements Serializable {
    private Integer page;
    private Integer total;
    private Integer records;
    private List<Album> rows;
}
