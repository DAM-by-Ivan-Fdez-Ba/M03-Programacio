import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int numErrors = 0;
        boolean exit, loop;
        char letter;
        char[] wrongLetters = new char[26];
        String[] words = insertWords();
        int numWord = (int) (Math.random()*30);
        String correctWord = words[numWord];
        char[] word = new char[correctWord.length()];
        Arrays.fill(word, '_');

        do {
            view(numErrors, word, wrongLetters);

            System.out.println("\n\nEnter a letter.");

            exit = false;
            do {
                letter = Character.toUpperCase(Teclat.llegirChar());
                if (check(letter, word, wrongLetters)){
                    System.out.println("Invalid option. Try again.");
                }else {
                    numErrors = introduce(correctWord, letter, numErrors, word, wrongLetters);
                    exit = true;
                }
            }while (!exit);

            loop = !finish(correctWord, numErrors, word, wrongLetters);
        }while (loop);
    }

    private static boolean check(char letter, char[] word, char[] wrongLetters){
        if (Character.isDigit(letter)){
            return true;
        }

        for (char c : word) {
            if (c == letter) {
                return true;
            }
        }

        for (char c : wrongLetters) {
            if (c == letter) {
                return true;
            }
        }

        return false;
    }
    private static boolean finish(String correctWord, int numErrors, char[] word, char[] wrongLetters){
        int total = 0;

        for (char c:word) {
            if (c == '_'){
                if (numErrors == 6){
                    view(numErrors, word, wrongLetters);
                    System.out.println("\n\nGAME OVER. You have made 6 mistakes. The correct word was " + correctWord + ".");
                    return true;
                }
            }else {
                total++;
            }
        }

        if (total == word.length){
            view(numErrors, word, wrongLetters);
            System.out.println("\n\nCongratulations. You have found all the letters.");
            return true;
        }

        return false;
    }
    private static int introduce(String correctWord, char letter, int numErrors, char[] word, char[] wrongLetters){
        boolean correct = false;

        for (int i = 0; i < word.length; i++){
            if (letter == correctWord.charAt(i)){
                word[i] = letter;
                correct = true;
            }
        }

        if (!correct) {
            wrongLetters[numErrors] = letter;
            numErrors++;
        }

        return numErrors;
    }

    private static void view(int numErrors, char[] word, char[] wrongLetters) {

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        switch (numErrors){
            case 0:
                System.out.println(
                        "    \n" +
                        "    \n" +
                        "    \n" +
                        "    \n" +
                        "    \n" +
                        "    \n" +
                        "    \n" +
                        "    \n");
                break;
            case 1:
                System.out.println(
                        "    \n" +
                        "    \n" +
                        "    \n" +
                        "    \n" +
                        "    \n" +
                        "    \n" +
                        "    \n" +
                        "_________\n");
                break;
            case 2:
                System.out.println(
                        "     \n" +
                        "    |\n" +
                        "    |\n" +
                        "    |\n" +
                        "    |\n" +
                        "    |\n" +
                        "    |\n" +
                        "____|____\n");
                break;
            case 3:
                System.out.println(
                        "     _______\n" +
                        "    |/\n" +
                        "    |\n" +
                        "    |\n" +
                        "    |\n" +
                        "    |\n" +
                        "    |\n" +
                        "____|____\n");
                break;
            case 4:
                System.out.println(
                        "     _______\n" +
                        "    |/      |\n" +
                        "    |\n" +
                        "    |\n" +
                        "    |\n" +
                        "    |\n" +
                        "    |\n" +
                        "____|____\n");
                break;
            case 5:
                System.out.println(
                        "     _______\n" +
                        "    |/      |\n" +
                        "    |       👶🏼\n" +
                        "    |\n" +
                        "    |\n" +
                        "    |\n" +
                        "    |\n" +
                        "____|____\n");
                break;
            case 6:
                System.out.println(
                        "     _______\n" +
                        "    |/      |\n" +
                        "    |       😵\n" +
                        "    |      \\|/\n" +
                        "    |       |\n" +
                        "    |      / \\\n" +
                        "    |\n" +
                        "____|____\n");
                break;
        }

        for (char letter:word) {
            System.out.print(letter + " ");
        }

        System.out.println("\n");

        for (char letter:wrongLetters) {
            if (letter != '\u0000') {
                System.out.print(letter + " ");
            }
        }
    }
    private static String[] insertWords(){
        return new String[]{ "CAT", "DOG", "MOUSE", "ELEPHANT", "TIGER", "LION", "GIRAFFE", "ZEBRA",
                "APPLE", "BANANA", "ORANGE", "GRAPE", "WATERMELON", "PEAR", "KIWI", "STRAWBERRY",
                "SUN", "MOON", "STAR", "CLOUD", "RAIN", "SNOW", "WIND", "STORM",
                "MOUNTAIN", "BEACH", "RIVER", "LAKE", "DESERT", "JUNGLE"
        };
    }
}