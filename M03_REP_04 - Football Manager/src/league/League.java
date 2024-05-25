package league;

import java.util.ArrayList;
import java.util.Random;

import ivan.Utils;
import team.Result;
import team.Team;

/**
 * Class League
 * Methods and attributes of Leagues.
 */
public class League {
    // Own attributes.
    private final String name;
    private final ArrayList<Team> teams = new ArrayList<>();

    // Constructor
    public League(String name) {
        this.name = name;
    }

    // Getters and setters
    public ArrayList<Team> getTeams() {
        return this.teams;
    }

    // Own methods
    public boolean addTeam(Team team){
        if (teams.contains(team)){
            return false;
        }else {
            teams.add(team);
            return true;
        }
    }

    private void resetResults(){
        for (Team team:this.teams){
            team.resetResults();
        }
    }

    private int[] playMatch(Team team1, Team team2){
        Random random = new Random();
        int team1Goals = random.nextInt(3);
        int team2Goals = random.nextInt(3);

        if(team1.calculateAverageQuality() > team2.calculateAverageQuality()){
            team1Goals++;
        } else if (team1.calculateAverageQuality() == team2.calculateAverageQuality()) {
            team1Goals++;
            team2Goals++;
        } else {
            team2Goals++;
        }

        if(team1.calculateAverageMotivationLevel() > team2.calculateAverageMotivationLevel()){
            team1Goals++;
        } else if (team1.calculateAverageMotivationLevel() == team2.calculateAverageMotivationLevel()) {
            team1Goals++;
            team2Goals++;
        } else {
            team2Goals++;
        }

        return new int[]{team1Goals, team2Goals};
    }

    public void playLeague(){
        int[] matchResult;

        resetResults();
        for (Team team1:teams){
            for (Team team2:teams){
                if (!team1.equals(team2)){
                    matchResult = playMatch(team1,team2);
                    team1.getResults().addMatchPlayed();
                    team2.getResults().addMatchPlayed();
                    teams.get(teams.indexOf(team1)).getResults().addGoalsInFavor(matchResult[0]);
                    teams.get(teams.indexOf(team1)).getResults().addGoalsAgainst(matchResult[1]);
                    teams.get(teams.indexOf(team2)).getResults().addGoalsAgainst(matchResult[0]);
                    teams.get(teams.indexOf(team2)).getResults().addGoalsInFavor(matchResult[1]);

                    if (matchResult[0] > matchResult[1]){
                        teams.get(teams.indexOf(team1)).getResults().addPoints(3);
                    }else if (matchResult[0] < matchResult[1]){
                        teams.get(teams.indexOf(team2)).getResults().addPoints(3);
                    }else {
                        teams.get(teams.indexOf(team1)).getResults().addPoints(1);
                        teams.get(teams.indexOf(team2)).getResults().addPoints(1);
                    }
                }
            }
        }
    }

    public String displayResults(){
        ArrayList<Result> results = new ArrayList<>();
        String displayResults = Utils.centerText(this.name.toUpperCase(), 42)
                + "\n╔════════════════════════════════════════╗\n"
                + "║         TEAMS          TP  MP  GF  GA  ║\n";

        for (Team team:teams){
            results.add(team.getResults());
        }
        results.sort(null);

        for (int i = 0; i < results.size(); i++) {
            displayResults += "║ " + ((i+1) + ". ") + results.get(i).toString() + "  ║\n";
        }
        displayResults += "╚════════════════════════════════════════╝";

        return displayResults;
    }
}
