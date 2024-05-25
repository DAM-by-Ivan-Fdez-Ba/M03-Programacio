package league;

import ivan.Utils;
import person.Coach;
import person.Person;
import person.Player;
import team.Team;
import team.TeamUtils;
import java.util.ArrayList;

/**
 * Class LeagueUtils
 * Functions and sub-functions of league management.
 */
public class LeagueUtils {
    /**
     * If the league exists, print the league results.
     * @param league A league
     */
    public static void viewLeagueStandings(League league) {
        Utils.space();
        if(league == null){
            System.out.println(Utils.paintTextRed("There is no league initialised."));
        }else {
            System.out.println(league.displayResults());
        }
        Utils.enterToContinue();
    }

    /**
     * If players or coaches are available, call the training function.
     * If it is a player it calls the positionChange function and if
     * it is a coach it calls the increaseSalary function.
     * @param coachesAndPlayers Arraylist of available coaches and players.
     */
    public static void conductTrainingSession(ArrayList<Person> coachesAndPlayers) {
        if(coachesAndPlayers.isEmpty()){
            System.out.println(Utils.paintTextRed("There are no players or coaches available."));
        }else {
            for (Person person:coachesAndPlayers){
                System.out.println(person.training());
                if (person instanceof Player){
                    System.out.println(((Player) person).positionChange());
                }else {
                    ((Coach) person).increaseSalary();
                }
            }
        }
        Utils.enterToContinue();
    }

    /**
     * It asks if you want to register a player or a coach,
     * calls its respective constructor and saves it in the
     * arraylist of available coachesAndPlayers.
     * @param coachesAndPlayers Arraylist of available coaches and players.
     */
    public static void registerPlayerCoach(ArrayList<Person> coachesAndPlayers) {
        boolean isPlayer = Utils.introduce("p","c", "Player (p) or Coach (c)?");

        if (isPlayer){
            coachesAndPlayers.add(new Player());
        }else {
            coachesAndPlayers.add(new Coach());
        }
        System.out.println(Utils.paintTextGreen("Registration complete."));
        Utils.enterToContinue();
    }

    /**
     * Create a league and return.
     * @param teams Arraylist of the teams.
     * @return The league created.
     */
    public static League startNewLeague(ArrayList<Team> teams) {
        int numOfTeams;
        String leagueName;
        Team team;

        leagueName = Utils.introduceString("Enter the league name.");

        League league = new League(leagueName);

        numOfTeams = Utils.introduce(2, teams.size(),"Enter the number of teams that will participate in the league. (2-" + teams.size() + ")");

        for (int i = 0; i < numOfTeams; i++) {
            team = TeamUtils.selectTeam(teams);
            if (team == null){
                System.out.println(Utils.paintTextRed("This team is not on the teams list."));
                i--;
            } else if (TeamUtils.checkTeamName(league.getTeams(), team.getName())) {
                System.out.println(Utils.paintTextRed("This team is already in the league."));
                i--;
            } else {
                if(league.addTeam(team)) {
                    System.out.println(Utils.paintTextGreen("Team added correctly."));
                }else {
                    System.out.println(Utils.paintTextRed("This team was already registered."));
                }
            }
            Utils.enterToContinue();
        }
        league.playLeague();
        return league;
    }
}
