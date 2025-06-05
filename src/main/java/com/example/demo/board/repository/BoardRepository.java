package com.example.demo.board.repository;

import com.example.demo.board.domain.Board;
import com.example.demo.board.domain.dto.ListBoardDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BoardRepository {

    @Select("select bno, title, userid, regdate, thumbs, views from boards order by bno desc limit #{stnum}, 25")
    List<ListBoardDTO> selectBoard(int stnum);

    @Select("select * from boards where bno = #{bno}")
    Board selectOneBoard(int bno);
}
