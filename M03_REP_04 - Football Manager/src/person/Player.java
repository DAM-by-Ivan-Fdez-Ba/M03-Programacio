package person;
import team.*;
import ivan.Utils;

import java.util.Objects;

/**
 * Class Player
 * Inherits from the class person.
 */
public class Player extends Person implements Transferable {
    // Own attributes.
    private int dorsal;
    private enum Position{
        GK, DEF, MID, FWD
    }
    private Position position;
    private double score;
    private final boolean transferable;

    // Constructors
    /**
     * Constructor to create players.
     */
    public Player() {
        super();
        this.dorsal = Utils.introduce(1, 99, "Enter the player's dorsal number.");
        this.position = randonPosition();
        this.score = (int) (Math.random()*70+30);
        this.transferable = ((int) Math.round(Math.random())) == 1;
    }

    /**
     * Constructor to import players.
     */
    public Player(String info){
        super(info);
        String[] playerInfo;
        playerInfo = info.split(":");

        this.dorsal = Integer.parseInt(playerInfo[5]);
        this.position = Position.valueOf(playerInfo[6]);
        this.score = Double.parseDouble(playerInfo[7]);
        this.transferable = Boolean.parseBoolean(playerInfo[8]);

    }

    // Getters and setters
    public int getDorsal() {
        return this.dorsal;
    }

    public String getName(){
        return this.name;
    }

    public Position getPosition() {
        return position;
    }

    public double getScore() {
        return this.score;
    }

    public String getSurame(){
        return this.surname;
    }

    public void setDorsal(int dorsal){
        this.dorsal = dorsal;
    }

    // Own methods
    private Position randonPosition(){
        int probability = (int) (Math.random()*3+1);

        return switch (probability) {
            case 1 -> Position.DEF;
            case 2 -> Position.GK;
            case 3 -> Position.MID;
            case 4 -> Position.FWD;
            default -> null;
        };
    }

    /**
     * Sometimes the position of the player changes.
     * @return A message with the changes that have been made.
     */
    public String positionChange(){
        int probability = (int) (Math.random()*99+1);
        String message = "The position of " + this.fullName() + " has not changed.";

        if (probability <= 5){
            Position newPosition;

            message = "The position of " + this.fullName() + " has changed from " + this.position.name();

            do {
                newPosition = randonPosition();
            }while (this.position == newPosition);

            this.position = newPosition;

            message += " to " + this.position.name() + ".";

            this.score++;
        }
        return message;
    }

    // Overwritten methods.
    @Override
    public boolean isTransferable(){
        return this.transferable;
    }

    @Override
    public void transferToTeam(Team team){
        team.addPlayer(this);
    }

    @Override
    public String training(){
        super.training();
        int probability = (int) (Math.random()*99+1);

        if (probability <= 10){
            this.score += 0.3;
        } else if (probability <= 30){
            this.score += 0.2;
        } else{
            this.score += 0.1;
        }

        return "The new score of " + this.fullName() + " is " + this.score + ".";
    }

    @Override
    public String export(){
        String player = super.export();

        player += this.dorsal + ":";
        player += this.position + ":";
        player += this.score + ":";
        player += this.transferable + ";";

        return player;
    }

    @Override
    public String toString() {
        return
                Utils.centerText(fullName().toUpperCase(),39) + "\n" +
                "╔═════════════════════════════════════╗\n" +
                "║ Annual Salary: " + Utils.adaptText(String.valueOf(this.annualSalary), 21) + "║\n" +
                "║ Birth date: " + Utils.adaptText(this.birthDate, 24) + "║\n" +
                "║ Dorsal: " + Utils.adaptText(String.valueOf(this.dorsal),28) +  "║\n" +
                "║ Motivation Level: " + Utils.adaptText(String.valueOf(this.motivationLevel), 18) + "║\n" +
                "║ Position: " + Utils.adaptText(String.valueOf(this.position), 26) + "║\n" +
                "║ Score: " + Utils.adaptText(String.valueOf(this.score), 29) + "║\n" +
                "║ Transferable: " + Utils.adaptText(String.valueOf(this.transferable), 22) + "║\n" +
                "╚═════════════════════════════════════╝";
    }

    /**
     * Tells if two players are the same by comparing name and dorsal number.
     * @param o a player
     * @return if it is the same or not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return dorsal == player.dorsal && Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dorsal);
    }
}
