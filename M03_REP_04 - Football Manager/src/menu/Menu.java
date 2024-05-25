package menu;


import ivan.Utils;

/**
 * Class Menu
 * Prints a menu and returns the selected option.
 */
public class Menu {
    public static int manageTeam(String name){
        Utils.space();
        System.out.println(
                Utils.centerText("Team Manager", 42) + "\n" +
                "╔════════════════════════════════════════╗\n" +
                "║"+ Utils.centerText(name.toUpperCase(),40) + "║\n" +
                "╠════════════════════════════════════════╣\n" +
                "║ 1 - Remove team                        ║\n" +
                "║ 2 - Modify president                   ║\n" +
                "║ 3 - Dismiss coach                      ║\n" +
                "║ 4 - Sign player or coach               ║\n" +
                "║ 5 - Transfer player                    ║\n" +
                "║ 0 - Exit                               ║\n" +
                "╚════════════════════════════════════════╝");
        int option = Utils.introduce(0, 5,"Select an option:");
        Utils.space();
        return option;
    }

    public static int mainMenu(){
        Utils.space();
        System.out.println(
                "╔═════════════════════════════════════════╗\n" +
                "║ Welcome to Politècnics Football Manager ║\n" +
                "╠═════════════════════════════════════════╣\n" +
                "║  1 - View league standings              ║\n" +
                "║  2 - Manage team                        ║\n" +
                "║  3 - Register a team                    ║\n" +
                "║  4 - Register a player or coach         ║\n" +
                "║  5 - View  team data                    ║\n" +
                "║  6 - View player data                   ║\n" +
                "║  7 - Start a new league                 ║\n" +
                "║  8 - Conduct training session           ║\n" +
                "║  9 - Load teams data                    ║\n" +
                "║ 10 - Save teams data                    ║\n" +
                "║ 11 - Load transfer market               ║\n" +
                "║  0 - Exit                               ║\n" +
                "╚═════════════════════════════════════════╝");
        return Utils.introduce(0,11,"Select an option:");
    }
}
