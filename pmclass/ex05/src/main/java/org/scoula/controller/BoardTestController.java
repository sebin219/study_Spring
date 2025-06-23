package org.scoula.controller;

import org.scoula.domain.BoardVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardTestController {

    @GetMapping("/api/mapper/boards")
    public List<BoardVO> getBoardList() {

        return null;
    }

    @GetMapping("/api/annotation/boards")
    public List<BoardVO> getBoardList2() {

        return null;
    }
}
