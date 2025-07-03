package org.scoula.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.mapper.BoardMapper;
import org.scoula.common.pagination.Page;
import org.scoula.common.pagination.PageRequest;
import org.scoula.common.util.UploadFiles;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final static String BASE_DIR = "c:/upload/board";
    final private BoardMapper mapper;

    @Override
    public List<BoardDTO> getList() {
        log.info("getList..........");

        return mapper.getList().stream()    // BoardVO의 스트림
                .map(BoardDTO::of)            // BoardDTO의 스트림
                .toList();                                // List<BoardDTO> 변환
    }

    @Override
    public BoardDTO get(Long no) {
        log.info("get......" + no);

        BoardDTO board = BoardDTO.of(mapper.get(no));
        return Optional
                .ofNullable(board)
                .orElseThrow(NoSuchElementException::new);

    }

    // 2개 이상의 insert 문이 실행될 수 있으므로 트랜잭션 처리 필요
    // RuntimeException인 경우만 자동 rollback.
    @Transactional
    @Override
    public BoardDTO create(BoardDTO board) {
        log.info("create......" + board);

        BoardVO boardVO = board.toVo();
        mapper.create(boardVO);

        // 파일 업로드 처리
        List<MultipartFile> files = board.getFiles();
        if (files != null && !files.isEmpty()) {    // 첨부 파일이 있는 경우
            upload(boardVO.getNo(), files);
        }

        return get(boardVO.getNo());
    }

    private void upload(Long bno, List<MultipartFile> files) {
        for (MultipartFile part : files) {
            // 빈 파일 건너뛰기
            if (part.isEmpty()) continue;
            try {
                // 파일을 서버에 저장
                String uploadPath = UploadFiles.upload(BASE_DIR, part);

                // 첨부파일을 정보를 db 에저장
                BoardAttachmentVO attach = BoardAttachmentVO.of(part, bno, uploadPath);
                mapper.createAttachment(attach);

            } catch (IOException e) {
                throw new RuntimeException(e);   // @Transactional에서 감지, 자동 rollback
            }
        }
    }

    // 게시글 수정 서비스
    @Override
    public BoardDTO update(BoardDTO board) {
        log.info("update......" + board);

        mapper.update(board.toVo());  // 게시글 수정 수행

        // 파일 업로드처리
        List<MultipartFile> files = board.getFiles();
        if (files != null && !files.isEmpty()) {
            upload(board.getNo(), files);
        }

        // 수정된 게시글 정보를 반환
        return get(board.getNo());
    }

    @Override
    public BoardDTO delete(Long no) {
        log.info("delete...." + no);
        BoardDTO board = get(no);

        mapper.delete(no);
        return board;
    }

    // 첨부파일 한 개 얻기
    @Override
    public BoardAttachmentVO getAttachment(Long no) {
        return mapper.getAttachment(no);
    }

    // 첨부파일 삭제
    @Override
    public boolean deleteAttachment(Long no) {
        return mapper.deleteAttachment(no) == 1;
    }

    @Override
    public Page<BoardDTO> getPage(PageRequest pageRequest) {
        List<BoardVO> boards = mapper.getPage(pageRequest);
        int totalCount = mapper.getTotalCount();
        return Page.of(pageRequest, totalCount,
                boards.stream().map(BoardDTO::of).toList());
    }
}
