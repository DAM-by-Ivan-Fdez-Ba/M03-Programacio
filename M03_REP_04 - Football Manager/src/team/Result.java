package team;

import ivan.Utils;

/**
 * Class Result
 * This class stores the results of the matches.
 */
public class Result implements Comparable<Result>{
    // Own attributes.
    private final String teamName;
    private int goalsInFavor;
    private int goalsAgainst;
    private int points;
    private int matchesPlayed = 0;

    // Constructor
    public Result(String teamName) {
        while (teamName.length()<20){
            teamName += " ";
        }
        this.teamName = teamName;
    }

    // Getters and setters
    public int getPoints() {
        return this.points;
    }

    // Own methods
    public void addGoalsInFavor(int goals){
        this.goalsInFavor += goals;
    }

    public void addGoalsAgainst(int goals){
        this.goalsAgainst += goals;
    }

    public void addPoints(int points){
        this.points += points;
    }

    public void addMatchPlayed(){
        this.matchesPlayed++;
    }

    // Overwritten methods.
    @Override
    public String toString() {
        String results = this.teamName;

        if (this.points < 10){
            results += " " + this.points;
        }else {
            results += this.points;
        }
        results += "  ";
        if (this.matchesPlayed < 10){
            results += " " + this.matchesPlayed;
        }else {
            results += this.matchesPlayed;
        }
        results += "  ";
        if (this.goalsInFavor < 10){
            results += " " + Utils.paintTextGreen(String.valueOf(this.goalsInFavor));
        }else {
            results += Utils.paintTextGreen(String.valueOf(this.goalsInFavor));
        }
        results += "  ";
        if (this.goalsAgainst < 10){
            results += " " + Utils.paintTextRed(String.valueOf(this.goalsAgainst));
        }else {
            results += Utils.paintTextRed(String.valueOf(this.goalsAgainst));
        }
        return results;
    }

    /**
     * Compare the results by total points. If they are equal, compare by goal difference.
     * @param otherResult the object to be compared.
     * @return Which result is better.
     */
    @Override
    public int compareTo(Result otherResult) {
        if (this.points < otherResult.getPoints()){
            return 1;
        } else if (this.points > otherResult.getPoints()) {
            return -1;
        }else {
            int goalDifferenceTeam1 = this.goalsInFavor - this.goalsAgainst;
            int goalDifferenceTeam2 = otherResult.goalsInFavor - otherResult.goalsAgainst;
            if (goalDifferenceTeam1 < goalDifferenceTeam2){
                return 1;
            }else {
                return -1;
            }
        }
    }
}
