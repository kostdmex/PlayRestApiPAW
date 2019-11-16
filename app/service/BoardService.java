package service;

import converters.board.BoardJsonPostToBoard;
import converters.board.BoardToBoardJson;
import json.board.BoardJson;
import json.board.BoardJsonPost;
import json.board.BoardJsonPut;
import models.Board;
import repository.BoardFinder;
import validator.BoardValidator;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class BoardService {

    @Inject
    private BoardToBoardJson boardToBoardJson;

    @Inject
    private BoardJsonPostToBoard boardJsonPostToBoard;

    public BoardJson findByBoardId(Integer boardId){
        Board board = BoardFinder.findById(boardId);
        if(board == null){
            return null;
        }
        return boardToBoardJson.apply(board);
    }

    public List<BoardJson> findBoardsByTeamId(Integer teamId){
        List<Board> boards = BoardFinder.findAllTeamBoards(teamId);
        if(boards.size() == 0){
            return null;
        }

        return boards.stream().map(boardToBoardJson).collect(Collectors.toList());
    }

    public Integer createBoard(BoardJsonPost boardJsonPost){
        if(!BoardValidator.validateBoardPost(boardJsonPost)){
            return null;
        }

        Board board = boardJsonPostToBoard.apply(boardJsonPost);
        board.save();
        return board.id;
    }

    public boolean updateBoard(Integer boardId, BoardJsonPut boardJsonPut){
        if(!BoardValidator.checkIfBoardExists(boardId)){
            return false;
        }

        Board boardToUpdate = BoardFinder.findById(boardId);
        if(boardJsonPut.getName() != null){
            boardToUpdate.setName(boardJsonPut.getName());
        }

        if(boardJsonPut.getBackground() != null){
            boardToUpdate.setBackground(boardJsonPut.getBackground());
        }

        boardToUpdate.save();

        return true;
    }

}
