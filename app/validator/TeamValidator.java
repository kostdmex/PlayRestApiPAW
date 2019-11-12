package validator;

import repository.TeamFinder;

import javax.inject.Singleton;

@Singleton
public class TeamValidator {

    public static boolean checkIfTeamExists(Integer teamId){
        return TeamFinder.findTeamById(teamId) != null;
    }

}
