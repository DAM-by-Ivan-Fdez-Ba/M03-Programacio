package team;

import ivan.Utils;
import menu.Menu;
import person.Coach;
import person.Person;
import person.Player;
import person.playerUtils.ComparatorByQuality;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class TeamUtils
 * Functions and sub-functions of team management.
 */
public class TeamUtils {
    /**
     * Menu for the team's management.
     * @param teams arraylist of teams.
     * @param coachesAndPlayers arraylist of available players and coaches.
     */
    public static void manageTeam(ArrayList<Team> teams, ArrayList<Person> coachesAndPlayers){
        boolean bucle = true;
        Team team = selectTeam(teams);
        if (team != null){
            do {
                switch (Menu.manageTeam(team.getName())){
                    // Exit
                    case 0:
                        bucle = false;
                        break;

                    // Remove team
                    case 1:
                        removeTeam(teams, team);
                        bucle = false;
                        break;

                    // Modify president
                    case 2:
                        modifyPresident(team);
                        break;

                    // Dismiss coach
                    case 3:
                        dismissCoach(team, coachesAndPlayers);
                        break;

                    // Sign player or coach
                    case 4:
                        signPlayerOrCoach(team, coachesAndPlayers);
                        break;

                    // Transfer player
                    case 5:
                        transferPlayer(teams, team);
                        break;
                }
            }while (bucle);
        }else {
            System.out.println(Utils.paintTextRed("This team is not in the database."));
            Utils.enterToContinue();
        }
    }

    /**
     * If there are players or coaches available it makes you select one for the team.
     * @param team a Team.
     * @param coachesAndPlayers arraylist of available players and coaches.
     */
    private static void signPlayerOrCoach(Team team, ArrayList<Person> coachesAndPlayers) {
        boolean isPlayer = Utils.introduce("p","c", "Player (p) or Coach (c)?");

        ArrayList<Person> available = new ArrayList<>();

        for (Person person:coachesAndPlayers){
            if (isPlayer && person instanceof Player) {
                available.add(person);
            }else if (!isPlayer && person instanceof Coach){
                available.add(person);
            }
        }
        Utils.space();
        printListOfAvailablePersons(available, isPlayer);
        if(!available.isEmpty()){
            int person = Utils.introduce(1, available.size(), (isPlayer) ? "Select a player:" : "Select a coach:") - 1;
            if (isPlayer){
                team.addPlayer((Player) available.get(person));
            }else {
                coachesAndPlayers.add(team.getCoach());
                team.setCoach((Coach) available.get(person));
            }
            coachesAndPlayers.remove(available.get(person));
        }else {
            System.out.println((isPlayer) ? "There are no players available." : "There are no coaches available.");
            Utils.enterToContinue();
        }
    }

    /**
     * If it is a player order the list before printing it.
     * @param available Arraylist with available persons.
     * @param isPlayer a boolean.
     */
    private static void printListOfAvailablePersons(ArrayList<Person> available, Boolean isPlayer){
        int numOfPersons = 0;

        if (isPlayer){
            ArrayList<Player> players = new ArrayList<>();

            for (Person person:available){
                if (person instanceof Player){
                    players.add((Player) person);
                }
            }

            players.sort(new ComparatorByQuality());
            available.clear();
            available.addAll(players);
        }
        System.out.println((isPlayer) ? "Players available:" : "Coaches available:");
        for (Person person:available){
            numOfPersons++;
            System.out.println(numOfPersons + ". " + person.fullName());
        }
    }

    private static void dismissCoach(Team team, ArrayList<Person> coachesAndPlayers){
        if (Utils.introduce("y","n","Are you sure you want to dismiss the coach? (Y/N)")){
            coachesAndPlayers.add(team.getCoach());
            team.dismissCoach();
        }
    }

    private static void modifyPresident(Team team){
        if (team.getPresidentName() == null){
            System.out.println("This team has no president yet.");
        }else {
            System.out.println("The current president of this team is " + team.getPresidentName() + ".");
        }
        String name = Utils.introduceString("\nEnter the new president's name:");
        Utils.space();
        System.out.println(team.modifyPresident(name));
        Utils.enterToContinue();
    }

    private static void removeTeam(ArrayList<Team> teams, Team team){
        if (Utils.introduce("y","n","Are you sure you want to remove this team? (Y/N)")){
            teams.remove(team);
        }
    }

    /**
     * Check if there is a player with that number in the team.
     * @param dorsal a Player dorsal.
     * @param team a Team.
     * @return If there is already a player with that dorsal in the team.
     */
    private static boolean checkDorsal(int dorsal, Team team){
        for(Player player: team.getPlayers()){
            if(player.getDorsal() == dorsal){
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the player is in the team by his full name and return his position in the array.
     * @param fullName player full name.
     * @param team a team.
     * @return position in the array.
     */
    private static int playerIndex(String fullName, Team team){
        for (Player player: team.getPlayers()){
            if (player.fullName().equalsIgnoreCase(fullName)){
                return team.getPlayers().indexOf(player);
            }
        }
        return -1;
    }

    private static void playersList(ArrayList<Player> players){
        for (Player player:players) {
            System.out.println("- " + player.fullName() + " " + player.getDorsal());
        }
    }

    /**
     * Transfers the selected player from the team to the selected team.
     * Before transferring, check if the player is transferable
     * and if the dorsal is available.
     * @param teams arraylist of teams
     * @param team a team
     */
    private static void transferPlayer(ArrayList<Team> teams, Team team) {
        String fullName;

        playersList(team.getPlayers());
        fullName = Utils.introduceString("Enter the player's full name.");

        Utils.space();
        if(playerIndex(fullName, team) == -1){
            System.out.println(Utils.paintTextRed("This player is not in the team."));
        }else {
            Team newTeam = selectTeam(teams);
            Player player = team.getPlayers().get(playerIndex(fullName,team));
            if (newTeam == null){
                System.out.println(Utils.paintTextRed("This team is not in the database."));
            }else {
                if (player.isTransferable()) {
                    if (checkDorsal(player.getDorsal(), newTeam)) {
                        int dorsal;
                        do {
                            System.out.println(Utils.paintTextRed("In this team there is already someone with this dorsal."));
                            dorsal = Utils.introduce(0, 99, "Introduce a new dorsal for this player.");
                        } while (checkDorsal(dorsal, newTeam));
                        player.setDorsal(dorsal);
                    }
                    team.getPlayers().remove(player);
                    player.transferToTeam(newTeam);
                    Utils.space();
                    System.out.println(Utils.paintTextGreen("The player has been successfully transferred."));
                } else {
                    System.out.println(Utils.paintTextRed("This player is not transferable."));
                }
            }
        }
        Utils.enterToContinue();
    }

    /**
     * Prints the list of teams and returns the selected team.
     * @param teams arraylist of teams.
     * @return a team
     */
    public static Team selectTeam(ArrayList<Team> teams){
        Scanner sc = new Scanner(System.in);
        String name;

        Utils.space();

        for (Team team:teams) {
            System.out.println("- " + team.getName());
        }

        System.out.println("\nEnter the team name:");
        name  = sc.nextLine();

        if (checkTeamName(teams, name)){
            Team team;
            team = teams.get(teamIndex(teams, name));
            return team;
        }
        return null;
    }

    public static void printTeamData(ArrayList<Team> teams) {
        Team team = selectTeam(teams);

        Utils.space();

        if (team == null){
            System.out.println(Utils.paintTextRed("This team is not in the database."));
        }else {
            System.out.println(team);
        }
        Utils.enterToContinue();
    }

    /**
     * It asks you to enter a name for the team, and returns the name only if it is available.
     * @param teams arraylist of teams.
     * @return team name.
     */
    public static String introduceTeamName(ArrayList<Team> teams){
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        String name;

        System.out.println("Introduce team name.");
        do {
            name = sc.nextLine();
            if (checkTeamName(teams, name)){
                System.out.println(Utils.paintTextRed("This team has already exist. Try with another name."));
            }else {
                exit = true;
            }
        }while (!exit);

        return name;
    }

    private static int teamIndex(ArrayList<Team> teams, String name){
        for (Team team: teams){
            if (team.getName().equalsIgnoreCase(name)){
                return teams.indexOf(team);
            }
        }
        return -1;
    }

    public static boolean checkTeamName(ArrayList<Team> teams, String teamName){
        for (Team team:teams){
            if(team.getName().equalsIgnoreCase(teamName)){
                return true;
            }
        }
        return false;
    }

    public static void printPlayerData(ArrayList<Team> teams) {
        Team team = selectTeam(teams);
        Utils.space();

        if (team != null){
            playersList(team.getPlayers());
            String name = Utils.introduceString("Enter the player's name.");
            int dorsal = Utils.introduce(0,99,"Enter the player's dorsal number.");

            Utils.space();
            System.out.println(team.playerData(name, dorsal));
        }else {
            System.out.println(Utils.paintTextRed("This team is not in the database."));
        }
        Utils.enterToContinue();
    }
}


