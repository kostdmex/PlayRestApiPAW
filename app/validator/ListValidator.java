package validator;

import repository.ListFinder;

import javax.inject.Singleton;

@Singleton
public class ListValidator {

    public static boolean checkIfListExists(Integer listId){
        return ListFinder.findListById(listId) != null;
    }

}
