package file;

import ivan.Utils;
import person.Coach;
import person.Person;
import person.Player;
import team.Team;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class File
 * Methods to use files.
 */
public class File {
    public static String getAddress(){
        Scanner sc = new Scanner(System.in);
        String address = "src/resources/";

        System.out.println("Enter the name of the file.");
        address += sc.next();
        return address;
    }

    public static void loadTransferMarket(ArrayList<Person> coachesAndPlayers, String address) {
        Path path = Paths.get(address);

        List<String> marketImported;

        try {
            marketImported = Files.readAllLines(path);
            for (String personInfo:marketImported){
                if (personInfo.split(":").length == 7){
                    coachesAndPlayers.add(new Coach(personInfo));
                }else {
                    coachesAndPlayers.add(new Player(personInfo));
                }
            }
        } catch (IOException e) {
            System.out.println(Utils.paintTextRed("Error. File not found."));
            Utils.enterToContinue();
        }
    }

    public static void loadTeamsData(ArrayList<Team> teams, String address) {
        Path path = Paths.get(address);

        List<String> teamsImported;
        try {
            teamsImported = Files.readAllLines(path);
            for (String teamInfo:teamsImported){
                teams.add(new Team(teamInfo));
            }
        } catch (IOException e) {
            System.out.println(Utils.paintTextRed("Error. File not found."));
            Utils.enterToContinue();
        }
    }

    public static void saveTeamsData(ArrayList<Team> teams, String address) {
        Path path = Paths.get(address);

        try {
            Files.createFile(path);
        } catch (IOException e) {
            System.out.println(Utils.paintTextRed("Error: " + e));
            Utils.enterToContinue();
        }

        try {
            for(Team team:teams){
                Files.writeString(path, team.exportTeam(), StandardOpenOption.APPEND);
                Files.writeString(path, System.lineSeparator(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            System.out.println(Utils.paintTextRed("Error: " + e));
            Utils.enterToContinue();
        }
    }

    public static void saveMarketData(ArrayList<Person> coachesAndPlayers, String address) {
        Path path = Paths.get(address);

        try {
            Files.createFile(path);
        } catch (IOException e) {
            System.out.println(Utils.paintTextRed("Error: " + e));
            Utils.enterToContinue();
        }

        try {
            for(Person person:coachesAndPlayers){
                if(person instanceof Coach coach){
                    Files.writeString(path, coach.export(), StandardOpenOption.APPEND);
                }else {
                    Files.writeString(path, person.export(), StandardOpenOption.APPEND);
                }
                Files.writeString(path, System.lineSeparator(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            System.out.println(Utils.paintTextRed("Error: " + e));
            Utils.enterToContinue();
        }
    }

    public static void loadPreviousData(ArrayList<Team> teams, ArrayList<Person> coachesAndPlayers){
        File.loadTeamsData(teams,"src/resources/teams.txt");
        File.loadTransferMarket(coachesAndPlayers, "src/resources/market.txt");
    }

    public static void saveData(ArrayList<Team> teams, ArrayList<Person> coachesAndPlayers){
        if (Utils.introduce("y","n","Do you want to save the game data? (Y/N)")){
            try {
                Files.delete(Path.of("src/resources/teams.txt"));
                Files.delete(Path.of("src/resources/market.txt"));
            } catch (IOException e) {
                System.out.println(Utils.paintTextRed("Error: " + e));
            }
            File.saveTeamsData(teams, "src/resources/teams.txt");
            File.saveMarketData(coachesAndPlayers, "src/resources/market.txt");
        }
    }
}
