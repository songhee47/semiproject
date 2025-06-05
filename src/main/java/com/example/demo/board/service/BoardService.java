package com.example.demo.board.service;

import com.example.demo.board.domain.dto.ListBoardDTO;
import java.util.List;

public interface BoardService {
    List<ListBoardDTO> readBoard(int cpg);
}
