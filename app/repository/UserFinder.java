package repository;

import io.ebean.Finder;
import models.User;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class UserFinder {

    private static final Finder<Integer, User> FINDER = new Finder<>(User.class);

    public static User findById(Integer id) {
        return FINDER.query().where().eq("id", id).findOne();
    }

    public static User findByLogin(String login){
        return FINDER.query().where().eq("login", login).findOne();
    }

    public static User findByLoginAndPassword(String login, String password){
        return FINDER.query().where().eq("login",login).eq("password", password).findOne();
    }

    public static List<User> findByTeamId(Integer teamId){
        return FINDER.query().where().eq("teams.id", teamId).findList();
    }
}
