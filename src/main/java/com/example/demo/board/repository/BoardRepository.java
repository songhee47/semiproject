package com.example.demo.board.repository;

import com.example.demo.board.domain.dto.ListBoardDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BoardRepository {

    @Select("select bno, title, userid, regdate, thumbs, views from boards order by bno desc")
    List<ListBoardDTO> selectBoard();
}
