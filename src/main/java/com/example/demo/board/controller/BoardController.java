package com.example.demo.board.controller;

import com.example.demo.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model m, @RequestParam(defaultValue = "1") int cpg) {

        // RequestParam에 defaultValue를 이용하면
        // cpg 매개변수가 전달되지 않을 경우 기본값인 1이 전달됨
        log.info("board/list 호출!!");

        m.addAttribute("bds", boardService.readBoard(cpg));

        return "views/board/list";
    }
}
