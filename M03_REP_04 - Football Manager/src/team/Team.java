package team;

import ivan.Utils;
import person.Coach;
import person.Player;
import person.playerUtils.ComparatorByPosition;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Class Team
 * Methods and attributes of teams.
 */
public class Team {
    // Own attributes.
    private final String city, name, stadiumName;
    private String presidentName;
    private Coach coach;
    private final int yearFounded;
    private ArrayList<Player> players = new ArrayList<>();
    private Result results;

    // Constructors
    /**
     * Constructor to create teams.
     */
    public Team(ArrayList<Team> teams){
        this.name = TeamUtils.introduceTeamName(teams);
        this.yearFounded = Utils.introduce(1500,2500, "Enter the year of foundation of the team.");;
        this.city = Utils.introduceString("Enter the team's city.");;
        this.stadiumName = Utils.introduceString("Enter the name of the team's stadium.");
        this.presidentName = Utils.introduceString("Enter the name of the team president.");
        this.results = new Result(name);
    }

    /**
     * Constructor to import teams.
     */
    public Team(String teamInfo) {
        String[] info;

        info = teamInfo.split(";");

        this.name = info[0];
        this.yearFounded = Integer.parseInt(info[1]);
        this.city = info[2];
        this.stadiumName = info[3];
        this.presidentName = info[4];
        if (!info[5].equals("null")){
            this.coach = new Coach(info[5]);
        }
        for (int i = 6; i < info.length; i++) {
            players.add(new Player(info[i]));
        }
        this.results = new Result(name);
    }

    // Getters and setters
    public Coach getCoach() {
        return this.coach;
    }

    public String getPresidentName() {
        return this.presidentName;
    }

    public void resetResults(){
        this.results = new Result(this.name);
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public Result getResults() {
        return this.results;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public String getName(){
        return this.name;
    }

    // Own methods
    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void dismissCoach(){
        this.coach = null;
    }

    /**
     * If there is a player, it returns the player's data.
     * @param name Player name
     * @param dorsal Player dorsal
     * @return Player data
     */
    public String playerData(String name, int dorsal){
        if (playerIndex(name, dorsal) == -1){
            return Utils.paintTextRed("No data has been found for this player.");
        }else {
            return players.get(playerIndex(name,dorsal)).toString();
        }
    }

    /**
     * Search if the player is in the team by name and number.
     * If it is it returns the position in the players array.
     * @param name Player name
     * @param dorsal Player dorsal
     * @return Player index
     */
    private int playerIndex(String name, int dorsal){
        for (Player player:players){
            if (player.getName().equalsIgnoreCase(name) && player.getDorsal() == dorsal){
                return players.indexOf(player);
            }
        }
        return -1;
    }

    /**
     * Change the president and return a message.
     * @param presidentName Name of the new president.
     * @return A confirmation message.
     */
    public String modifyPresident(String presidentName) {
        String message = Utils.paintTextGreen("Modification done correctly.");
        if (this.presidentName == null){
            message += "\n\nThis team had no president.";
        }else if(this.presidentName.equals(presidentName)){
            message += "\n\nThis president was already leading this team.";
        }
        this.presidentName = presidentName;
        return message;
    }

    public double calculateAverageQuality(){
        double averageQuality = 0;

        for (Player player:players){
            averageQuality += player.getScore();
        }

        return averageQuality/ players.size();
    }

    public double calculateAverageMotivationLevel(){
        int totalPersons = this.players.size();
        double averageMotivationLevel = 0.0;

        for (Player player:this.players){
            averageMotivationLevel += player.getMotivationLevel();
        }

        if (this.coach != null){
            averageMotivationLevel += coach.getMotivationLevel();
            totalPersons++;
        }

        return averageMotivationLevel/totalPersons;
    }

    public String exportTeam(){
        String team;
        team = this.name + ";";
        team += this.yearFounded + ";";
        team += this.city + ";";
        team += this.stadiumName + ";";
        team += this.presidentName + ";";

        if (this.coach != null){
            team += this.coach.export();
        }else {
            team += null;
        }

        for (Player player:this.players){
            team += player.export();
        }

        return team;
    }

    // Overwritten methods.
    @Override
    public String toString() {
        this.players.sort(new ComparatorByPosition());
        String team =
                Utils.centerText(this.name.toUpperCase(), 45)
                + "\n" +
                "╔═══════════════════════════════════════════╗\n" +
                "║ City: " + String.format("%-33s", this.city) + "\t║\n" +
                "║ Coach: ";

        if(this.coach == null) {
            team += Utils.adaptText("null",20);
        }else {
            team += Utils.adaptText(this.coach.fullName(),20);
        }
        team += "\t\t\t\t║\n" +
                    "║ President: " + Utils.adaptText(this.presidentName, 31) +  "║\n" +
                    "║ Stadium: " + Utils.adaptText(this.stadiumName, 33) + "║\n" +
                    "║ Year Founded: " + this.yearFounded + "\t\t\t\t\t\t║\n" +
                    "║ Players: \t\t\t\t\t\t\t\t\t║\n";

        for (Player player:this.players){
            if (player.getDorsal() < 10){
                team += "║\t " + player.getDorsal() + ". " + Utils.adaptText(player.fullName(), 30) + player.getPosition() +"\t║\n";
            }else {
                team += "║\t" + player.getDorsal() + ". " + Utils.adaptText(player.fullName(), 30) + player.getPosition() +"\t║\n";
            }
        }
        team += "╚═══════════════════════════════════════════╝";

        return team;
    }

    /**
     * It compares two teams by name.
     * @param o a Team
     * @return if it is the same
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(name, team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
