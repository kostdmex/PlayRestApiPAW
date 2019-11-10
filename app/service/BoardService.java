package service;

import converters.BoardToBoardJson;
import json.BoardJson;
import models.Board;
import repository.BoardFinder;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class BoardService {

    @Inject
    private BoardToBoardJson boardToBoardJson;

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

}
