package validator;

import json.user.UserJsonPost;

import javax.inject.Singleton;

@Singleton
public class UserValidator {

    public static boolean validateUserPost(UserJsonPost userJsonPost){
        if(userJsonPost.getLogin() == null || userJsonPost.getPassword() == null || userJsonPost.getName() == null || userJsonPost.getSurname() == null){
            return false;
        }
        return true;
    }

}
