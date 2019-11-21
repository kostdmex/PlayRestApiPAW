package service;

import converters.user.UserJsonPostToUser;
import json.user.UserJsonPost;
import models.User;
import validator.UserValidator;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserService {

    @Inject
    private UserJsonPostToUser userJsonPostToUser;

    public Integer createUser(UserJsonPost userJsonPost){
        if(!UserValidator.validateUserPost(userJsonPost)){
            return -1;
        }

        User user = userJsonPostToUser.apply(userJsonPost);
        user.save();
        return user.getId();
    }

}
