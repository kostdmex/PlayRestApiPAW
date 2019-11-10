package validator;

import json.BoardJsonPost;

import javax.inject.Singleton;

@Singleton
public class BoardValidator {

    public static boolean validateBoardPost(BoardJsonPost boardJsonPost){

        if(boardJsonPost.getName() == null && boardJsonPost.getTeam_id() == null){
            return false;
        }else return TeamValidator.checkIfTeamExists(boardJsonPost.getTeam_id());
    }

}
