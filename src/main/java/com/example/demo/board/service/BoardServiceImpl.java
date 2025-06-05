package com.example.demo.board.service;

import com.example.demo.board.domain.dto.ListBoardDTO;
import com.example.demo.board.repository.BoardRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardMapper;

    @Override
    public List<ListBoardDTO> readBoard(int cpg) {
        // cpg에 따라서 시작위치값 계산
        int stnum = (cpg-1)*25;
        return boardMapper.selectBoard(stnum);
    }
}
