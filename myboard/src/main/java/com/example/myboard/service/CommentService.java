package com.example.myboard.service;

import com.example.myboard.model.entity.Board;
import com.example.myboard.model.request.BoardPostRequest;
import com.example.myboard.model.request.CommentPostRequest;
import com.example.myboard.model.response.BoardResponse;
import com.example.myboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final BoardRepository boardRepository;

    //댓글 등록
    @Transactional
    public BoardResponse writeComment(CommentPostRequest request) {
        Optional<Board> boardOptional = boardRepository.findBoardWithCommentsByBoardNo(request.getBoardNo());
        Board board = boardOptional.orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다"));

        board.addComment(request.getCommentBody());
        boardRepository.save(board);

        return BoardResponse.from(board);
    }

    //게시글 단건 조회(게시글 + 댓글)
}
