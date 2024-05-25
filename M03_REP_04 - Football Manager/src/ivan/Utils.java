package ivan;

import java.util.Scanner;

public class Utils {
    private static final String redColor = "\u001B[31m";
    private static final String greenColor = "\u001B[32m";
    private static final String blueColor = "\u001B[34m";
    private static final String purpleColor = "\u001B[35m";
    private static final String yellowColor = "\u001B[33m";
    private static final String orangeColor = "\u001B[38;5;208m";
    private static final String resetColor = "\u001B[0m";

    public static int introduce(int min, int max, String message){
        boolean exit = false;
        int option = -1;

        System.out.println(message);
        do {
            try {
                option = new Scanner(System.in).nextInt();

                if (option < min || option > max){
                    System.out.println("Invalid option. Try again.");
                }else {
                    exit = true;
                }
            }catch (Exception e){
                System.out.println("Invalid option. Try again.");
            }
        }while (!exit);

        return option;
    }

    public static boolean introduce(String a, String b, String message){
        boolean exit = false;
        String option;

        System.out.println(message);
        do {
            option = new Scanner(System.in).next();
            if (option.equalsIgnoreCase(a) || option.equalsIgnoreCase(b)){
                exit = true;
            }else {
                System.out.println("Invalid option. Try again.");
            }
        }while (!exit);

        return option.equalsIgnoreCase(a);
    }

    public static int introduceInt(String message){
        boolean exit = false;
        int option = -1;

        System.out.println(message);
        do {
            try {
                option = new Scanner(System.in).nextInt();
                exit = true;
            }catch (Exception e){
                System.out.println("Invalid number. Try again.");
            }
        }while (!exit);

        return option;
    }

    public static double introduceDouble(String message){
        boolean exit = false;
        double option = -1;

        System.out.println(message);
        do {
            try {
                option = new Scanner(System.in).nextInt();
                exit = true;
            }catch (Exception e){
                System.out.println("Invalid number. Try again.");
            }
        }while (!exit);

        return option;
    }

    public static String introduceString(String message){
        System.out.println(message);
        return new Scanner(System.in).nextLine();
    }

    public static String introduceWord(String message){
        System.out.println(message);
        return new Scanner(System.in).next();
    }

    public static void enterToContinue(){
        System.out.println("\nPress enter to continue.");
        new Scanner(System.in).nextLine();
    }

    public static void space(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public static String centerText(String text, int length) {
        int spacesNeeded = length - text.length();
        int leftSpaces = spacesNeeded / 2;
        int rightSpaces = spacesNeeded - leftSpaces;

        String leftSpace = String.format("%" + leftSpaces + "s", "");
        String rightSpace = String.format("%" + rightSpaces + "s", "");

        return leftSpace + text + rightSpace;
    }


    public static String adaptText(String text, int length) {
        if (text.length() > length) {
            return text.substring(0, length);
        } else {
            String space = String.format("%" + (length - text.length()) + "s", "");
            return text + space;
        }
    }

    private static String paintText(String text, String color){
        return color + text + resetColor;
    }

    public static String paintTextRed(String text){
        return paintText(text, redColor);
    }

    public static String paintTextGreen(String text){
        return paintText(text, greenColor);
    }

    public static String paintTextBlue(String text){
        return paintText(text, blueColor);
    }

    public static String paintTextPurple(String text){
        return paintText(text, purpleColor);
    }

    public static String paintTextYellow(String text){
        return paintText(text, yellowColor);
    }

    public static String paintTextOrange(String text){
        return paintText(text, orangeColor);
    }
}
