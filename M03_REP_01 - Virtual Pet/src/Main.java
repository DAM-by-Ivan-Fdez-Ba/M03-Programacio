public class Main {
    public static void main(String[] args) {
        String pet = "";
        String name;
        String deadCause = "";
        boolean loop = true;
        boolean exit;
        int option;

        int energy = (int) (Math.random()*21+40);
        int fatigue = (int) (Math.random()*21+40);
        int sleep = (int) (Math.random()*21+40);
        int hygiene = (int) (Math.random()*21+40);
        int weight = (int) (Math.random()*21+40);

        String redColor = "\u001B[31m";
        String greenColor = "\u001B[32m";
        String resetColor = "\u001B[0m";

        System.out.println("VIRTUAL PET\n-------------------------------------------");

        do {
            System.out.println("Do you want to have a dog(0) or a cat(1)?");
            option = Teclat.llegirInt();

            if (option == 0){
                pet = "dog";
            }else if (option == 1){
                pet = "cat";
            }else {
                System.out.println("Invalid option.");
            }
        }while (pet.isEmpty());

        System.out.println("What do you want to name the " + pet + "?");
        name = Teclat.llegirString();

        String title = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + name.toUpperCase() + " THE " + pet.toUpperCase() + "\n------------------------------------------";

        System.out.println(title);
        if (pet.equals("dog")){
            System.out.println(
                "\t       /^-^\\\t\tATTRIBUTES:\n" +
                "\t      / o o \\\t\t---------------\n" +
                "\t     /   Y   \\\t\tEnergy:\t\t" + energy + "\n" +
                "\t     V \\ v / V\t\tFatigue:\t" + fatigue + "\n" +
                "\t       / - \\\t\tSleep:\t\t" + sleep + "\n" +
                "\t      /    |\t\tHygiene:\t" + hygiene +"\n" +
                "\t(    /     |\t\tWeight:\t\t" + weight + "\n" +
                "\t ===/___) ||\n" +
                "------------------------------------------\n" +
                "1.- Sleep \n2.- Play \n3.- Eat \n4.- Shower \n0.- Exit application\n" +
                "------------------------------------------");
        }else {
            System.out.println(
                "      ,_     _,\n" +
                "      |\\\\___//|\t\t\tATTRIBUTES:\n" +
                "      |=0   0=|\t\t\t---------------\n" +
                "      \\=._V_.=/\t\t\tEnergy:\t\t" + energy + "\n" +
                "       )  `  (    ,\t\tFatigue:\t" + fatigue + "\n" +
                "      /       \\  ((\t\tSleep:\t\t" + sleep + "\n" +
                "      |       |   ))\tHygiene:\t" + hygiene + "\n" +
                "     /| |   | |\\_//\t\tWeight:\t\t" + weight + "\n" +
                "     \\| |._.| |/-`\n" +
                "      '\"'   '\"'\n" +
                "------------------------------------------\n" +
                "1.- Sleep \n2.- Play \n3.- Eat \n4.- Self-cleaning \n0.- Exit application\n" +
                "------------------------------------------");
        }

        do {
            exit = false;
            do {
                option = Teclat.llegirInt();
                if (option < 0 || option > 4){
                    System.out.println("Invalid option. Try again.");
                }else {
                    exit = true;
                }
            }while (!exit);

            if (option != 0){
                System.out.println(title);
            }

            switch (option){
                case 0:
                    loop = false;
                    break;
                case 1:
                    fatigue -= 15;
                    energy += 15;
                    if (pet.equals("dog")){
                        sleep -= 25;
                        System.out.println(
                            "\t       /^-^\\\t\tATTRIBUTES:\n" +
                            "\t      / o o \\\t\t---------------\n" +
                            "\t     /   Y   \\\t\tEnergy:\t\t" + greenColor + energy + resetColor + "\n" +
                            "\t     V \\ v / V\t\tFatigue:\t" + redColor + fatigue + resetColor + "\n" +
                            "\t       / - \\\t\tSleep:\t\t" + redColor + sleep + resetColor + "\n" +
                            "\t      /    |\t\tHygiene:\t" + hygiene +"\n" +
                            "\t(    /     |\t\tWeight:\t\t" + weight + "\n" +
                            "\t ===/___) ||\n" +
                            "------------------------------------------\n" +
                            "1.- Sleep \n2.- Play \n3.- Eat \n4.- Shower \n0.- Exit application\n" +
                            "------------------------------------------");
                    }else {
                        sleep -= 20;
                        System.out.println(
                            "      ,_     _,\n" +
                            "      |\\\\___//|\t\t\tATTRIBUTES:\n" +
                            "      |=0   0=|\t\t\t---------------\n" +
                            "      \\=._V_.=/\t\t\tEnergy:\t\t" + greenColor + energy + resetColor + "\n" +
                            "       )  `  (    ,\t\tFatigue:\t" + redColor + fatigue + resetColor + "\n" +
                            "      /       \\  ((\t\tSleep:\t\t" + redColor + sleep + resetColor + "\n" +
                            "      |       |   ))\tHygiene:\t" + hygiene + "\n" +
                            "     /| |   | |\\_//\t\tWeight:\t\t" + weight + "\n" +
                            "     \\| |._.| |/-`\n" +
                            "      '\"'   '\"'\n" +
                            "------------------------------------------\n" +
                            "1.- Sleep \n2.- Play \n3.- Eat \n4.- Self-cleaning \n0.- Exit application\n" +
                            "------------------------------------------");
                    }
                    break;
                case 2:
                    fatigue += 10;
                    energy -= 10;
                    hygiene -= 15;
                    weight -= 10;
                    if (pet.equals("dog")){
                        sleep -= 10;
                        System.out.println(
                            "\t       /^-^\\\t\tATTRIBUTES:\n" +
                            "\t      / o o \\\t\t---------------\n" +
                            "\t     /   Y   \\\t\tEnergy:\t\t" + redColor + energy + resetColor + "\n" +
                            "\t     V \\ v / V\t\tFatigue:\t" + greenColor + fatigue + resetColor + "\n" +
                            "\t       / - \\\t\tSleep:\t\t" + redColor + sleep + resetColor + "\n" +
                            "\t      /    |\t\tHygiene:\t" + redColor + hygiene + resetColor + "\n" +
                            "\t(    /     |\t\tWeight:\t\t" + redColor + weight + resetColor + "\n" +
                            "\t ===/___) ||\n" +
                            "------------------------------------------\n" +
                            "1.- Sleep \n2.- Play \n3.- Eat \n4.- Shower \n0.- Exit application\n" +
                            "------------------------------------------");
                    }else {
                        sleep += 15;
                        System.out.println(
                            "      ,_     _,\n" +
                            "      |\\\\___//|\t\t\tATTRIBUTES:\n" +
                            "      |=0   0=|\t\t\t---------------\n" +
                            "      \\=._V_.=/\t\t\tEnergy:\t\t" + redColor + energy + resetColor + "\n" +
                            "       )  `  (    ,\t\tFatigue:\t" + greenColor + fatigue + resetColor + "\n" +
                            "      /       \\  ((\t\tSleep:\t\t" + greenColor + sleep + resetColor + "\n" +
                            "      |       |   ))\tHygiene:\t" + redColor + hygiene + resetColor + "\n" +
                            "     /| |   | |\\_//\t\tWeight:\t\t" + redColor + weight + resetColor + "\n" +
                            "     \\| |._.| |/-`\n" +
                            "      '\"'   '\"'\n" +
                            "------------------------------------------\n" +
                            "1.- Sleep \n2.- Play \n3.- Eat \n4.- Self-cleaning \n0.- Exit application\n" +
                            "------------------------------------------");
                    }
                    break;
                case 3:
                    energy += 10;
                    hygiene -= 10;
                    weight += 10;
                    if (pet.equals("dog")){
                        sleep += 15;
                        System.out.println(
                            "\t       /^-^\\\t\tATTRIBUTES:\n" +
                            "\t      / o o \\\t\t---------------\n" +
                            "\t     /   Y   \\\t\tEnergy:\t\t" + greenColor + energy + resetColor + "\n" +
                            "\t     V \\ v / V\t\tFatigue:\t" + fatigue + "\n" +
                            "\t       / - \\\t\tSleep:\t\t" + greenColor + sleep + resetColor + "\n" +
                            "\t      /    |\t\tHygiene:\t" + redColor + hygiene + resetColor + "\n" +
                            "\t(    /     |\t\tWeight:\t\t" + greenColor + weight + resetColor + "\n" +
                            "\t ===/___) ||\n" +
                            "------------------------------------------\n" +
                            "1.- Sleep \n2.- Play \n3.- Eat \n4.- Shower \n0.- Exit application\n" +
                            "------------------------------------------");
                    }else {
                        sleep += 10;
                        System.out.println(
                            "      ,_     _,\n" +
                            "      |\\\\___//|\t\t\tATTRIBUTES:\n" +
                            "      |=0   0=|\t\t\t---------------\n" +
                            "      \\=._V_.=/\t\t\tEnergy:\t\t" + greenColor + energy + resetColor + "\n" +
                            "       )  `  (    ,\t\tFatigue:\t" + fatigue + "\n" +
                            "      /       \\  ((\t\tSleep:\t\t" + greenColor + sleep + resetColor + "\n" +
                            "      |       |   ))\tHygiene:\t" + redColor + hygiene + resetColor + "\n" +
                            "     /| |   | |\\_//\t\tWeight:\t\t" + greenColor + weight + resetColor + "\n" +
                            "     \\| |._.| |/-`\n" +
                            "      '\"'   '\"'\n" +
                            "------------------------------------------\n" +
                            "1.- Sleep \n2.- Play \n3.- Eat \n4.- Self-cleaning \n0.- Exit application\n" +
                            "------------------------------------------");
                    }
                    break;
                case 4:
                    hygiene+= 20;
                    if (pet.equals("dog")){
                        sleep -= 15;
                        System.out.println(
                            "\t       /^-^\\\t\tATTRIBUTES:\n" +
                            "\t      / o o \\\t\t---------------\n" +
                            "\t     /   Y   \\\t\tEnergy:\t\t" + energy + "\n" +
                            "\t     V \\ v / V\t\tFatigue:\t" + fatigue + "\n" +
                            "\t       / - \\\t\tSleep:\t\t" + redColor + sleep + resetColor + "\n" +
                            "\t      /    |\t\tHygiene:\t" + greenColor + hygiene + resetColor + "\n" +
                            "\t(    /     |\t\tWeight:\t\t"+ weight + "\n" +
                            "\t ===/___) ||\n" +
                            "------------------------------------------\n" +
                            "1.- Sleep \n2.- Play \n3.- Eat \n4.- Shower \n0.- Exit application\n" +
                            "------------------------------------------");
                    }else {
                        sleep += 10;
                        System.out.println(
                            "      ,_     _,\n" +
                            "      |\\\\___//|\t\t\tATTRIBUTES:\n" +
                            "      |=0   0=|\t\t\t---------------\n" +
                            "      \\=._V_.=/\t\t\tEnergy:\t\t" + energy + "\n" +
                            "       )  `  (    ,\t\tFatigue:\t" + fatigue + "\n" +
                            "      /       \\  ((\t\tSleep:\t\t" + redColor + sleep + resetColor + "\n" +
                            "      |       |   ))\tHygiene:\t" + greenColor + hygiene + resetColor + "\n" +
                            "     /| |   | |\\_//\t\tWeight:\t\t"+ weight + "\n" +
                            "     \\| |._.| |/-`\n" +
                            "      '\"'   '\"'\n" +
                            "------------------------------------------\n" +
                            "1.- Sleep \n2.- Play \n3.- Eat \n4.- Self-cleaning \n0.- Exit application\n" +
                            "------------------------------------------");
                    }
                    break;
            }

            if (energy > 100){
                deadCause = "excess energy";
                loop = false;
            }else if (energy < 0){
                deadCause = "exhaustion";
                loop = false;
            }else if (fatigue > 100){
                deadCause = "excess fatigue";
                loop = false;
            }else if (fatigue < 0){
                deadCause = "lack of fatigue";
                loop = false;
            }else if (sleep > 100){
                deadCause = "not sleeping";
                loop = false;
            }else if (sleep < 0){
                deadCause = "sleeping too much";
                loop = false;
            }else if (hygiene > 100){
                deadCause = "excess hygiene";
                loop = false;
            }else if (hygiene < 0){
                deadCause = "being a pig";
                loop = false;
            }else if (weight > 100){
                deadCause = "fatness";
                loop = false;
            }else if (weight < 0){
                deadCause = "hunger";
                loop = false;
            }
        }while (loop);

        if (deadCause.isEmpty()){
            System.out.println(title);
            if (pet.equals("dog")){
                System.out.println(
                        "\t       /^-^\\\t\tATTRIBUTES:\n" +
                        "\t      / o o \\\t\t---------------\n" +
                        "\t     /   Y   \\\t\tEnergy:\t\t" + energy + "\n" +
                        "\t     V \\ v / V\t\tFatigue:\t"+ fatigue + "\n" +
                        "\t       / - \\\t\tSleep:\t\t"+ sleep + "\n" +
                        "\t      /    |\t\tHygiene:\t"+ hygiene +"\n" +
                        "\t(    /     |\t\tWeight:\t\t"+ weight + "\n" +
                        "\t ===/___) ||\n" +
                        "------------------------------------------\n");
            }else {
                System.out.println(
                        "      ,_     _,\n" +
                        "      |\\\\___//|\t\t\tATTRIBUTES:\n" +
                        "      |=0   0=|\t\t\t---------------\n" +
                        "      \\=._V_.=/\t\t\tEnergy:\t\t" + energy + "\n" +
                        "       )  `  (    ,\t\tFatigue:\t"+ fatigue + "\n" +
                        "      /       \\  ((\t\tSleep:\t\t"+ sleep + "\n" +
                        "      |       |   ))\tHygiene:\t"+ hygiene +"\n" +
                        "     /| |   | |\\_//\t\tWeight:\t\t"+ weight + "\n" +
                        "     \\| |._.| |/-`\n" +
                        "      '\"'   '\"'\n" +
                        "------------------------------------------");
            }
        }else {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("\t\t\t\tGAME OVER " +"\n------------------------------------------");
            if (pet.equals("dog")){
                System.out.println(
                        "\t\t\t       /^-^\\\n" +
                        "\t\t\t      / X X \\\n" +
                        "\t\t\t     /   Y   \\\n" +
                        "\t\t\t     V \\ v / V\n" +
                        "\t\t\t       / - \\\n" +
                        "\t\t\t      /    |\n" +
                        "\t\t\t(    /     |\n" +
                        "\t\t\t ===/___) ||\n" +
                        "------------------------------------------");
            }else {
                System.out.println(
                        "\t\t        ,_     _,\n" +
                        "\t\t        |\\\\___//|\n" +
                        "\t\t        |=X   X=|\n" +
                        "\t\t        \\=._V_.=/\n" +
                        "\t\t         )  `  (    ,\n" +
                        "\t\t        /       \\  ((\n" +
                        "\t\t        |       |   ))\n" +
                        "\t\t       /| |   | |\\_//\n" +
                        "\t\t       \\| |._.| |/-`\n" +
                        "\t\t        '\"'   '\"'\n" +
                        "------------------------------------------");
            }
            System.out.println(String.format("%s%s", Character.toUpperCase(name.charAt(0)), name.substring(1)) + " died of " + deadCause + ".");
        }
    }
}