package validator;

import json.list.ListJsonPost;
import repository.ListFinder;

import javax.inject.Singleton;

@Singleton
public class ListValidator {

    public static boolean checkIfListExists(Integer listId){
        return ListFinder.findListById(listId) != null;
    }

    public static boolean validateListPost(ListJsonPost listJson){
        if(listJson.getName() == null || listJson.getBoardId() == null || listJson.getNumberOnBoard() == null){
            return false;
        }else return BoardValidator.checkIfBoardExists(listJson.getBoardId());
    }
}
