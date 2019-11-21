package service;

import converters.board.BoardJsonPostToBoard;
import converters.board.BoardToBoardJson;
import json.board.BoardJson;
import json.board.BoardJsonPost;
import json.board.BoardJsonPut;
import models.Board;
import models.User;
import models.User_Board;
import repository.BoardFinder;
import repository.UserFinder;
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

    public BoardJson findByBoardId(Integer boardId, Integer userId){
        Board board = BoardFinder.findByUserIdAndBoardId(userId, boardId);
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

    public List<BoardJson> findAll(Integer userId){
        List<Board> boards = BoardFinder.findByUserId(userId);
        if(boards.size() == 0){
            return null;
        }

        return boards.stream().map(boardToBoardJson).collect(Collectors.toList());
    }

    public Integer createBoard(BoardJsonPost boardJsonPost, Integer userId){
        if(!BoardValidator.validateBoardPost(boardJsonPost)){
            return null;
        }

        Board board = boardJsonPostToBoard.apply(boardJsonPost);
        board.save();
        if(board.team_id == null){
            User_Board user_board = new User_Board(userId, board.id);
            user_board.save();
        }else{
            List<User> users = UserFinder.findByTeamId(board.team_id);
            users.forEach(user -> new User_Board(user.getId(), board.id).save());
        }
        return board.id;
    }

    public boolean updateBoard(Integer boardId, BoardJsonPut boardJsonPut, Integer userId){
        if(!BoardValidator.checkIfBoardExists(boardId)){
            return false;
        }

        Board boardToUpdate = BoardFinder.findByUserIdAndBoardId(userId, boardId);
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
