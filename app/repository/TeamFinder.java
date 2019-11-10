package repository;

import io.ebean.Finder;
import models.Team;

import javax.inject.Singleton;

@Singleton
public class TeamFinder {

    private static final Finder<Integer, Team> FINDER = new Finder<>(Team.class);

    public static Team findTeamById(Integer teamId) {
        return FINDER.query().where().eq("id", teamId).findOne();
    }

}
