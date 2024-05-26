import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


/**
 * THE QUIZ
 *
 * @author Ivàn Fernàndez
 * @version 2.0
 */
public class Main {
    // Declaracion de valores
    private static final String quizName = "PREGUNTATRES";
    private static final int numOfCategories = 6;
    private static final String playerName = introducePlayerName();
    private static final String redColor = "\u001B[31m";
    private static final String greenColor = "\u001B[32m";
    private static final String blueColor = "\u001B[34m";
    private static final String purpleColor = "\u001B[35m";
    private static final String yellowColor = "\u001B[33m";
    private static final String orangeColor = "\u001B[38;5;208m";
    private static final String resetColor = "\u001B[0m";


    /**
     * main
     *
     * It starts the game as many times as the user wants.
     */
    public static void main(String[] args) throws InterruptedException {
        boolean loop;

        do {
            switch (selectMode()){
                case 1:
                    // You choose a category and the number of questions.
                    mode1(generateQuestions(selectCategory()), selectNumQuestions());
                    break;
                case 2:
                    // These are all the questions in the game.
                    mode2(generateQuestions(0));
                    break;
            }
            loop = askPlayAgain();
        }while (loop);
    }

    /**
     * introducePlayerName
     *
     * He asks the player what his name is and returns it.
     * @return the player's name.
     */
    private static String introducePlayerName(){
        System.out.println("Whats your name?");
        return new Scanner(System.in).nextLine();
    }

    /**
     * askPlayAgain
     *
     * Ask the player if he wants to play again.
     * @return the player's answer in boolean form.
     */
    private static boolean askPlayAgain(){
        System.out.println("\nDo you want to play again? (Y, N)");
        return introduceYN();
    }

    /**
     * selectMode
     *
     * Print a menu and read the player's selection.
     * @return player selection.
     */
    private static int selectMode(){
        space();
        System.out.println(
                orangeColor + "\n-------------------------" +
                "\n|\t\t\t\t\t\t|" +
                "\n|\t  " + quizName + "  \t|" +
                "\n|\t\t\t\t\t\t|" +
                "\n-------------------------\n" + resetColor +
                "\n-------------------------" +
                "\n|\t\tGAME MODES\t\t|" +
                "\n-------------------------\n|" +
                blueColor + " 1. Classic\t\t\t" + resetColor + "|\n|" +
                redColor + " 2. Extreme\t\t\t" + resetColor + "|" +
                "\n-------------------------");
        System.out.print("Select a game mode: ");
        return introduceNum(1, 2);
    }

    /**
     * selectNumQuestions
     *
     * Ask the player the number of questions he wants to answer.
     * @return the number of questions.
     */
    private static int selectNumQuestions(){
        space();
        System.out.println("How many questions do you want to answer? (5-20)");
        return introduceNum(5,20);
    }

    /**
     * selectCategory
     *
     * It prints a list of the categories and asks the player to select one.
     * @return player selection.
     */
    private static int selectCategory(){
        space();
        System.out.println(
                "\n-------------------------" +
                "\n|\t\tCATEGORIES\t\t|" +
                "\n-------------------------\n|" +
                blueColor + " 1. Geography\t\t\t" + resetColor + "|\n|" +
                greenColor + " 2. Science\t\t\t" + resetColor + "|\n|" +
                yellowColor + " 3. History\t\t\t" + resetColor + "|\n|" +
                orangeColor + " 4. Sports\t\t\t\t" + resetColor + "|\n|" +
                redColor + " 5. Art\t\t\t\t" + resetColor + "|\n|" +
                purpleColor + " 6. Entertainment" + resetColor +
                "\t\t|\n-------------------------");
        System.out.print("Select a category: ");

        return introduceNum(1, numOfCategories);
    }

    /**
     * mode1
     *
     * This function is the classic mode of the game. It prints the questions and saves the number of correct answers.
     * At the end of the game, print the result of the game with percentages and save the game.
     * @param questions two-dimensional array of questions and answers.
     * @param numQuestions the number of questions the player wants to answer.
     */
    private static void mode1(String[][] questions, int numQuestions) throws InterruptedException {
        char option;
        int correctAnswers = 0;

        for (int i = 0; i < numQuestions; i++) {
            int[] order = randomizer(1, 4, 4);
            printQuestion(questions[i], order);
            option = introduceABCD();

            if (checkAnswer(order, option)){
                correctAnswers++;
            }

            visualAnswer(order, option, questions[i]);
            System.out.print("Please wait");
            Thread.sleep(2000);
        }
        printResultWithPercentage(correctAnswers, questions.length);
        saveGame(gameInfo(correctAnswers, questions.length));
    }

    /**
     * gameInfo CLASSIC
     *
     * This function is in charge of structuring the game information in the classic way before saving it.
     * The structure is player name, date, game mode, correct answers, incorrect answers.
     * @param correctAnswers number of correct answers.
     * @param totalQuestions number of questions answered by the user.
     * @return a String with the structured information.
     */
    private static String gameInfo(int correctAnswers, int totalQuestions){
        return playerName + "," +
                getDate() + "," +
                "CLASSIC" + "," +
                correctAnswers + "," +
                (totalQuestions-correctAnswers) + ";";
    }

    /**
     * gameInfo EXTREME
     *
     * This function is in charge of structuring the game information in the extreme mode before saving it.
     * The structure is player name, date, game mode, points.
     * @param score points scored by the player.
     * @return a String with the structured information.
     */
    private static String gameInfo(int score){
        return playerName + "," +
                getDate() + "," +
                "EXTREME" + "," +
                score + ";";
    }

    /**
     * mode2
     *
     * This function is the extreme mode of the game. It prints out all the questions in the game
     * and adds points for each correct answer plus a bonus after 3 correct answers in a row.
     * You also have 3 lives, which you lose every time you fail, and you get them back when you
     * correctly answer the questions. The game will end when you finish all the questions or
     * when you run out of lives.
     * At the end of the game, print the result of the game with the total points and save the game.
     * @param questions two-dimensional array of questions and answers.
     */
    private static void mode2(String[][] questions) throws InterruptedException {
        char option;
        int bonus = 0, lives = 3, questionNumber = 0, score = 0;

        do {
            int[] order = randomizer(1, 4, 4);
            printQuestion(questions[questionNumber], order);
            System.out.println("\nScore: " + score);
            option = introduceABCD();

            if (checkAnswer(order, option)){
                bonus++;
                score += 100;
                lives = 3;
                if (bonus > 2){
                    score += bonus*20;
                }
            }else {
                bonus = 0;
                lives--;
            }
            visualAnswer(order, option, questions[questionNumber]);
            System.out.println("\nScore: " + score + "\nPlease wait");
            Thread.sleep(2000);
            questionNumber++;
        }while (lives != 0 && questionNumber < questions.length);
        space();
        if (questionNumber == questions.length){
            System.out.println(greenColor + "YOU WIN! " + resetColor + "with a score of " + score + ".");
        }else {
            System.out.println(redColor + "GAME OVER" + resetColor +
                    ". You have made 3 consecutive errors. You have obtained a total score of " + score + ".");
        }
        saveGame(gameInfo(score));
    }

    /**
     * printResultWithPercentage
     *
     * This function calculates the hit percentage and prints a customised message depending on the hit percentage value.
     * @param correctAnswers number of correct answers.
     * @param totalQuestions total number of questions.
     */
    private static void printResultWithPercentage(int correctAnswers, int totalQuestions){
        float hitPercentage = (float) (correctAnswers * 100 / totalQuestions);

        String formattedPercentage = String.format("%.2f", hitPercentage);

        space();

        if (hitPercentage >= 0 && hitPercentage <= 33){
            System.out.println("Don't worry! You answered around " + formattedPercentage + "%. Every mistake is an opportunity to learn - keep trying!");
        } else if (hitPercentage >= 34 && hitPercentage <= 66) {
            System.out.println("You're on the right track! You have answered about " + formattedPercentage + "%. You're getting more than half right - keep it up and get better every day!");
        } else if (hitPercentage >= 67 && hitPercentage <= 99) {
            System.out.println("Awesome! You have answered about " + formattedPercentage + "%. You're mastering most of the questions - keep it up and you'll reach 100% soon!");
        }else {
            System.out.println("Awesome! You've answered all the questions correctly " + formattedPercentage + "! You're an expert at this!");
        }
    }

    /**
     * printQuestion
     *
     * This function prints out a question with possible answers.
     * @param question two-dimensional array of questions and answers.
     * @param order order in which the answers should be printed.
     */
    private static void printQuestion(String[] question, int[] order){
        space();
        System.out.println(question[0]);
        System.out.println("a) " + question[order[0]]);
        System.out.println("b) " + question[order[1]]);
        System.out.println("c) " + question[order[2]]);
        System.out.println("d) " + question[order[3]]);
    }

    /**
     * visualAnswer
     *
     * This function is similar to printQuestion with the difference that the user has already
     * answered the question. What it does is reprint the question by highlighting the player's
     * choice. Green if it is correct and red if it is incorrect.
     * @param order order in which the answers should be printed.
     * @param option option selected by the player.
     * @param question two-dimensional array of questions and answers.
     */
    private static void visualAnswer(int [] order, char option, String[] question) {
        String color = checkAnswer(order, option) ? greenColor : redColor;
        space();
        System.out.println(question[0]);

        switch (option){
            case 'a':
                System.out.println(color + "a) " + question[order[0]] + resetColor);
                System.out.println("b) " + question[order[1]]);
                System.out.println("c) " + question[order[2]]);
                System.out.println("d) " + question[order[3]]);
                break;
            case 'b':
                System.out.println("a) " + question[order[0]]);
                System.out.println(color + "b) " + question[order[1]] + resetColor);
                System.out.println("c) " + question[order[2]]);
                System.out.println("d) " + question[order[3]]);
                break;
            case 'c':
                System.out.println("a) " + question[order[0]]);
                System.out.println("b) " + question[order[1]]);
                System.out.println(color + "c) " + question[order[2]] + resetColor);
                System.out.println("d) " + question[order[3]]);
                break;
            case 'd':
                System.out.println("a) " + question[order[0]]);
                System.out.println("b) " + question[order[1]]);
                System.out.println("c) " + question[order[2]]);
                System.out.println(color + "d) " + question[order[3]] + resetColor);
                break;
        }
    }

    /**
     * checkAnswer
     *
     * This function checks if the player's option is correct and returns a boolean.
     * @param order order of the answers.
     * @param option player's choice.
     * @return if the answer is correct.
     */
    private static boolean checkAnswer(int[] order, char option){
        boolean correct = false;

        switch (option){
            case 'a':
                if (order[0] == 1){
                    correct = true;
                }
                break;
            case 'b':
                if (order[1] == 1){
                    correct = true;
                }
                break;
            case 'c':
                if (order[2] == 1){
                    correct = true;
                }
                break;
            case 'd':
                if (order[3] == 1){
                    correct = true;
                }
                break;
        }
        return correct;
    }

    /**
     * getDate
     *
     * This function returns the date and time to the system in String format.
     * @return The date and time.
     */
    private static String getDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * introduceABCD
     *
     * This function asks the user to enter 'a', 'b', 'c' or 'd'. It repeats this until
     * the user enters one of these values.
     * @return 'a', 'b', 'c' or 'd'.
     */
    private static char introduceABCD() {
        boolean exit = false;
        char option;

        do {
            option = Character.toLowerCase(Teclat.llegirChar());
            switch (option){
                case 'a', 'b', 'c', 'd':
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }while (!exit);

        return option;
    }

    /**
     * introduceYN
     *
     * This function asks the user to enter 'Y' or 'N' and returns true if 'Y' or false if 'N'.
     * @return true or false.
     */
    private static Boolean introduceYN(){
        boolean exit = false;
        String option;

        do {
            option = Teclat.llegirString().toUpperCase();
            if (option.equals("Y") || option.equals("N")){
                exit = true;
            }else {
                System.out.println("Invalid option. Try again.");
            }
        }while (!exit);

        return option.equals("Y");
    }

    /**
     * introduceNum
     *
     * Asks the user to enter a number between min and max.
     * @param min minimum number.
     * @param max maximum number.
     * @return the number introduced by the user.
     */
    private static int introduceNum(int min, int max){
        boolean exit = false;
        int option;

        do {
            option = Teclat.llegirInt();
            if (option < min || option > max){
                System.out.println("Invalid option. Try again.");
            }else {
                exit = true;
            }
        }while (!exit);

        return option;
    }

    /**
     * randomizer
     *
     * This function creates an array of size numOfNumbers and stores unrepeated random
     * values between the min and max number.
     * @param min minimum number.
     * @param max maximum number.
     * @param numOfNumbers the number of numbers to be returned.
     * @return an array with random numbers.
     */
    private static int[] randomizer(int min, int max, int numOfNumbers){
        int[] positionsRandom = new int[numOfNumbers];
        int aux;

        for (int i = 0; i < numOfNumbers; i++){
            boolean isDuplicated = false;
            aux = (int) (Math.random() * max + min);

            for (int j = 0; j < i; j++) {
                if (positionsRandom[j] == aux){
                    isDuplicated = true;
                    break;
                }
            }
            if (isDuplicated){
                i--;
            }else{
                positionsRandom[i] = aux;
            }
        }
        return positionsRandom;
    }

    /**
     * generateQuestions
     *
     * This function creates an array of questions from the selected category.
     * If the category is 0 it will make an array of questions from all categories.
     * @param category category number.
     * @return an array of randomly ordered questions and answers.
     */
    private static String[][] generateQuestions(int category) {
        String[][] questions;

        if (category == 0){
            int destPost = 0;
            questions = new String[120][5];
            for (int i = 1; i <= numOfCategories; i++) {
                String[][] aux = loadQuestions(selectPath(i));
                System.arraycopy(aux,0,questions,destPost,aux.length);
                destPost += aux.length;
            }
        }else {
            questions = loadQuestions(selectPath(category));
        }

        return randomAssign(questions);
    }

    /**
     * randomAssign
     *
     * This function receives an array of questions and unorder it.
     * @param questions an array of questions and answers.
     * @return an array of randomly ordered questions and answers.
     */
    private static String[][] randomAssign(String[][] questions){
        String[][] unorderedQuestions = new String[questions.length][5];
        int[] unorderedOrder = randomizer(0, questions.length, questions.length);

        for (int i = 0; i < unorderedOrder.length; i++) {
            System.arraycopy(questions[unorderedOrder[i]], 0, unorderedQuestions[i], 0, questions[i].length);
        }

        return unorderedQuestions;
    }

    /**
     * selectPath
     *
     * This function is in charge of sending the address of the questions file of the selected category.
     * @param category the category number.
     * @return the address of the selected category.
     */
    private static Path selectPath (int category){
        String address = "src/resources/";
        switch (category) {
            case 1:
                address += "geography.txt";
                break;
            case 2:
                address += "science.txt";
                break;
            case 3:
                address += "history.txt";
                break;
            case 4:
                address += "sports.txt";
                break;
            case 5:
                address += "art.txt";
                break;
            case 6:
                address += "entertainment.txt";
                break;
        }
        return Paths.get(address);
    }

    /**
     * loadQuestions
     *
     * This function loads the questions into an array.
     * @param path address of the archive.
     * @return array with loaded data.
     */
    private static String[][] loadQuestions(Path path){
        String[][] questions = new String[20][5];
        List<String> questionsImported;

        try {
            questionsImported = Files.readAllLines(path);

            for (int i = 0; i < questionsImported.size(); i++) {
                String[] questionInParts = questionsImported.get(i).split(":");
                System.arraycopy(questionInParts, 0, questions[i], 0, questionInParts.length);
            }
        }catch (IOException e){
            System.out.println("Error. File not found.");
        }
        return questions;
    }

    /**
     * saveGame
     *
     * This function is in charge of saving the game data in a file.
     * @param gameInfo data of the game.
     */
    private static void saveGame(String gameInfo){
        Path path = Paths.get("src/resources/savedGame.txt");
        try {
            Files.createFile(path);
        } catch (IOException e) {
            try {
                Files.writeString(path, System.lineSeparator(), StandardOpenOption.APPEND);
            } catch (IOException ignored) {
            }
        }
        try {
            Files.writeString(path, gameInfo, StandardOpenOption.APPEND);
        }catch (IOException e){
            System.out.println("Error: " + e);
        }
    }

    private static void space(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}
