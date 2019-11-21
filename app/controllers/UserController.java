package controllers;

import json.user.UserJsonPost;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.UserService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserController extends Controller {

    @Inject
    private UserService userService;

    public Result createUser(){
        UserJsonPost userJsonPost = Json.fromJson(request().body().asJson(), UserJsonPost.class);

        Integer userId = userService.createUser(userJsonPost);
        if(userId == -1){
            return badRequest();
        }

        return created().withHeader("Location", String.valueOf(userId));
    }

}
