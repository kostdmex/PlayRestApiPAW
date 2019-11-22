package converters.user;

import json.user.UserJsonPost;
import models.User;

import java.util.function.Function;

public class UserJsonPostToUser implements Function<UserJsonPost, User> {

    @Override
    public User apply(UserJsonPost userJson) {
        return new User(userJson.getName(), userJson.getSurname(), userJson.getLogin(), userJson.getPassword(), userJson.getEmail());
    }

}
