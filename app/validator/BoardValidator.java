package validator;

import json.board.BoardJsonPost;
import repository.BoardFinder;

import javax.inject.Singleton;

@Singleton
public class BoardValidator {

    public static boolean validateBoardPost(BoardJsonPost boardJsonPost){

        if(boardJsonPost.getName() == null){
            return false;
        }else if (boardJsonPost.getTeam_id() != null) {
            return TeamValidator.checkIfTeamExists(boardJsonPost.getTeam_id());
        }else return true;
    }

    public static boolean checkIfBoardExists(Integer boardId){
        return BoardFinder.findById(boardId) != null;
    }

}
