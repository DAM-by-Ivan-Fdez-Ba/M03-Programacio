import league.*;
import person.*;
import team.Team;
import menu.Menu;
import file.File;
import team.TeamUtils;
import java.util.ArrayList;

/**
 * Football Manager
 *
 * @author Ivàn Fernàndez
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        League league = null;
        ArrayList<Team> teams = new ArrayList<>();
        ArrayList<Person> coachesAndPlayers = new ArrayList<>();
        boolean bucle = true;

        //Loads the last saved data.
        File.loadPreviousData(teams, coachesAndPlayers);

        do {
            switch (Menu.mainMenu()){
                // Exit
                case 0:
                    File.saveData(teams,coachesAndPlayers);
                    bucle = false;
                    break;

                // View league standings
                case 1:
                    LeagueUtils.viewLeagueStandings(league);
                    break;

                // Manage team
                case 2:
                    TeamUtils.manageTeam(teams, coachesAndPlayers);
                    break;

                // Register a team
                case 3:
                    teams.add(new Team(teams));
                    break;

                // Register a player or coach
                case 4:
                    LeagueUtils.registerPlayerCoach(coachesAndPlayers);
                    break;

                // View team data
                case 5:
                    TeamUtils.printTeamData(teams);
                    break;

                // View player data
                case 6:
                    TeamUtils.printPlayerData(teams);
                    break;

                // Start a new league
                case 7:
                    league = LeagueUtils.startNewLeague(teams);
                    break;

                // Conduct training session
                case 8:
                    LeagueUtils.conductTrainingSession(coachesAndPlayers);
                    break;

                // Load teams data
                case 9:
                    File.loadTeamsData(teams, File.getAddress());
                    break;

                // Save teams data
                case 10:
                    File.saveTeamsData(teams, File.getAddress());
                    break;

                // Load transfer market
                case 11:
                    File.loadTransferMarket(coachesAndPlayers, File.getAddress());
                    break;
            }
        }while (bucle);
    }
}