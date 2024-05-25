public class Main {
    public static void main(String[] args) {
        boolean loop = true;
        boolean exit;
        int option;

        String redColor = "\u001B[31m";
        String greenColor = "\u001B[32m";
        String resetColor = "\u001B[0m";

        Pet vPet = new Pet();

        vPet.setEnergy((int) (Math.random()*21+40));
        vPet.setFatigue((int) (Math.random()*21+40));
        vPet.setSleep((int) (Math.random()*21+40));
        vPet.setHygiene((int) (Math.random()*21+40));
        vPet.setWeight((int) (Math.random()*21+40));

        System.out.println("VIRTUAL PET\n-------------------------------------------");

        do {
            System.out.println("Do you want to have a dog(0) or a cat(1)?");
            option = Teclat.llegirInt();

            switch (option){
                case 0:
                    vPet.setPet("dog");
                    break;
                case 1:
                    vPet.setPet("cat");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }while (vPet.getPet().isEmpty());

        System.out.println("What do you want to name the " + vPet.getPet() + "?");
        vPet.setName(Teclat.llegirString());

        String title = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + vPet.getName().toUpperCase() + " THE " + vPet.getPet().toUpperCase() + "\n------------------------------------------";

        System.out.println(title);
        if (vPet.getPet().equals("dog")){
            System.out.println(
                "\t       /^-^\\\t\tATTRIBUTES:\n" +
                "\t      / o o \\\t\t---------------\n" +
                "\t     /   Y   \\\t\tEnergy:\t\t" + vPet.getEnergy() + "\n" +
                "\t     V \\ v / V\t\tFatigue:\t" + vPet.getFatigue() + "\n" +
                "\t       / - \\\t\tSleep:\t\t" + vPet.getSleep() + "\n" +
                "\t      /    |\t\tHygiene:\t" + vPet.getHygiene() +"\n" +
                "\t(    /     |\t\tWeight:\t\t" + vPet.getWeight() + "\n" +
                "\t ===/___) ||\n" +
                "------------------------------------------\n" +
                "1.- Sleep \n2.- Play \n3.- Eat \n4.- Shower \n0.- Exit application\n" +
                "------------------------------------------");
        }else {
            System.out.println(
                "      ,_     _,\n" +
                "      |\\\\___//|\t\t\tATTRIBUTES:\n" +
                "      |=0   0=|\t\t\t---------------\n" +
                "      \\=._V_.=/\t\t\tEnergy:\t\t" + vPet.getEnergy() + "\n" +
                "       )  `  (    ,\t\tFatigue:\t" + vPet.getFatigue() + "\n" +
                "      /       \\  ((\t\tSleep:\t\t" + vPet.getSleep() + "\n" +
                "      |       |   ))\tHygiene:\t" + vPet.getHygiene() + "\n" +
                "     /| |   | |\\_//\t\tWeight:\t\t" + vPet.getWeight() + "\n" +
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
                    vPet.setFatigue(vPet.getFatigue()-15);
                    vPet.setEnergy(vPet.getEnergy()+15);
                    if (vPet.getPet().equals("dog")){
                        vPet.setSleep(vPet.getSleep()-25);
                        System.out.println(
                            "\t       /^-^\\\t\tATTRIBUTES:\n" +
                            "\t      / o o \\\t\t---------------\n" +
                            "\t     /   Y   \\\t\tEnergy:\t\t" + greenColor + vPet.getEnergy() + resetColor + "\n" +
                            "\t     V \\ v / V\t\tFatigue:\t" + redColor + vPet.getFatigue() + resetColor + "\n" +
                            "\t       / - \\\t\tSleep:\t\t" + redColor + vPet.getSleep() + resetColor + "\n" +
                            "\t      /    |\t\tHygiene:\t" + vPet.getHygiene() +"\n" +
                            "\t(    /     |\t\tWeight:\t\t" + vPet.getWeight() + "\n" +
                            "\t ===/___) ||\n" +
                            "------------------------------------------\n" +
                            "1.- Sleep \n2.- Play \n3.- Eat \n4.- Shower \n0.- Exit application\n" +
                            "------------------------------------------");
                    }else {
                        vPet.setSleep(vPet.getSleep()-20);
                        System.out.println(
                            "      ,_     _,\n" +
                            "      |\\\\___//|\t\t\tATTRIBUTES:\n" +
                            "      |=0   0=|\t\t\t---------------\n" +
                            "      \\=._V_.=/\t\t\tEnergy:\t\t" + greenColor + vPet.getEnergy() + resetColor + "\n" +
                            "       )  `  (    ,\t\tFatigue:\t" + redColor + vPet.getFatigue() + resetColor + "\n" +
                            "      /       \\  ((\t\tSleep:\t\t" + redColor + vPet.getSleep() + resetColor + "\n" +
                            "      |       |   ))\tHygiene:\t" + vPet.getHygiene() + "\n" +
                            "     /| |   | |\\_//\t\tWeight:\t\t" + vPet.getWeight() + "\n" +
                            "     \\| |._.| |/-`\n" +
                            "      '\"'   '\"'\n" +
                            "------------------------------------------\n" +
                            "1.- Sleep \n2.- Play \n3.- Eat \n4.- Self-cleaning \n0.- Exit application\n" +
                            "------------------------------------------");
                    }
                    break;
                case 2:
                    vPet.setFatigue(vPet.getFatigue()+10);
                    vPet.setEnergy(vPet.getEnergy()-10);
                    vPet.setHygiene(vPet.getHygiene()-15);
                    vPet.setWeight(vPet.getWeight()-10);
                    if (vPet.getPet().equals("dog")){
                        vPet.setSleep(vPet.getSleep()-10);
                        System.out.println(
                            "\t       /^-^\\\t\tATTRIBUTES:\n" +
                            "\t      / o o \\\t\t---------------\n" +
                            "\t     /   Y   \\\t\tEnergy:\t\t" + redColor + vPet.getEnergy() + resetColor + "\n" +
                            "\t     V \\ v / V\t\tFatigue:\t" + greenColor + vPet.getFatigue() + resetColor + "\n" +
                            "\t       / - \\\t\tSleep:\t\t" + redColor + vPet.getSleep() + resetColor + "\n" +
                            "\t      /    |\t\tHygiene:\t" + redColor + vPet.getHygiene() + resetColor + "\n" +
                            "\t(    /     |\t\tWeight:\t\t" + redColor + vPet.getWeight() + resetColor + "\n" +
                            "\t ===/___) ||\n" +
                            "------------------------------------------\n" +
                            "1.- Sleep \n2.- Play \n3.- Eat \n4.- Shower \n0.- Exit application\n" +
                            "------------------------------------------");
                    }else {
                        vPet.setSleep(vPet.getSleep()+15);
                        System.out.println(
                            "      ,_     _,\n" +
                            "      |\\\\___//|\t\t\tATTRIBUTES:\n" +
                            "      |=0   0=|\t\t\t---------------\n" +
                            "      \\=._V_.=/\t\t\tEnergy:\t\t" + redColor + vPet.getEnergy() + resetColor + "\n" +
                            "       )  `  (    ,\t\tFatigue:\t" + greenColor + vPet.getFatigue() + resetColor + "\n" +
                            "      /       \\  ((\t\tSleep:\t\t" + greenColor + vPet.getSleep() + resetColor + "\n" +
                            "      |       |   ))\tHygiene:\t" + redColor + vPet.getHygiene() + resetColor + "\n" +
                            "     /| |   | |\\_//\t\tWeight:\t\t" + redColor + vPet.getWeight() + resetColor + "\n" +
                            "     \\| |._.| |/-`\n" +
                            "      '\"'   '\"'\n" +
                            "------------------------------------------\n" +
                            "1.- Sleep \n2.- Play \n3.- Eat \n4.- Self-cleaning \n0.- Exit application\n" +
                            "------------------------------------------");
                    }
                    break;
                case 3:
                    vPet.setEnergy(vPet.getEnergy()+10);
                    vPet.setHygiene(vPet.getHygiene()-10);
                    vPet.setWeight(vPet.getWeight()+10);
                    if (vPet.getPet().equals("dog")){
                        vPet.setSleep(vPet.getSleep()+15);
                        System.out.println(
                            "\t       /^-^\\\t\tATTRIBUTES:\n" +
                            "\t      / o o \\\t\t---------------\n" +
                            "\t     /   Y   \\\t\tEnergy:\t\t" + greenColor + vPet.getEnergy() + resetColor + "\n" +
                            "\t     V \\ v / V\t\tFatigue:\t" + vPet.getFatigue() + "\n" +
                            "\t       / - \\\t\tSleep:\t\t" + greenColor + vPet.getSleep() + resetColor + "\n" +
                            "\t      /    |\t\tHygiene:\t" + redColor + vPet.getHygiene() + resetColor + "\n" +
                            "\t(    /     |\t\tWeight:\t\t" + greenColor + vPet.getWeight() + resetColor + "\n" +
                            "\t ===/___) ||\n" +
                            "------------------------------------------\n" +
                            "1.- Sleep \n2.- Play \n3.- Eat \n4.- Shower \n0.- Exit application\n" +
                            "------------------------------------------");
                    }else {
                        vPet.setSleep(vPet.getSleep()+10);
                        System.out.println(
                            "      ,_     _,\n" +
                            "      |\\\\___//|\t\t\tATTRIBUTES:\n" +
                            "      |=0   0=|\t\t\t---------------\n" +
                            "      \\=._V_.=/\t\t\tEnergy:\t\t" + greenColor + vPet.getEnergy() + resetColor + "\n" +
                            "       )  `  (    ,\t\tFatigue:\t" + vPet.getFatigue() + "\n" +
                            "      /       \\  ((\t\tSleep:\t\t" + greenColor + vPet.getSleep() + resetColor + "\n" +
                            "      |       |   ))\tHygiene:\t" + redColor + vPet.getHygiene() + resetColor + "\n" +
                            "     /| |   | |\\_//\t\tWeight:\t\t" + greenColor + vPet.getWeight() + resetColor + "\n" +
                            "     \\| |._.| |/-`\n" +
                            "      '\"'   '\"'\n" +
                            "------------------------------------------\n" +
                            "1.- Sleep \n2.- Play \n3.- Eat \n4.- Self-cleaning \n0.- Exit application\n" +
                            "------------------------------------------");
                    }
                    break;
                case 4:
                    vPet.setHygiene(vPet.getHygiene()+20);
                    if (vPet.getPet().equals("dog")){
                        vPet.setSleep(vPet.getSleep()-15);
                        System.out.println(
                            "\t       /^-^\\\t\tATTRIBUTES:\n" +
                            "\t      / o o \\\t\t---------------\n" +
                            "\t     /   Y   \\\t\tEnergy:\t\t" + vPet.getEnergy() + "\n" +
                            "\t     V \\ v / V\t\tFatigue:\t" + vPet.getFatigue() + "\n" +
                            "\t       / - \\\t\tSleep:\t\t" + redColor + vPet.getSleep() + resetColor + "\n" +
                            "\t      /    |\t\tHygiene:\t" + greenColor + vPet.getHygiene() + resetColor + "\n" +
                            "\t(    /     |\t\tWeight:\t\t"+ vPet.getWeight() + "\n" +
                            "\t ===/___) ||\n" +
                            "------------------------------------------\n" +
                            "1.- Sleep \n2.- Play \n3.- Eat \n4.- Shower \n0.- Exit application\n" +
                            "------------------------------------------");
                    }else {
                        vPet.setSleep(vPet.getSleep()+10);
                        System.out.println(
                            "      ,_     _,\n" +
                            "      |\\\\___//|\t\t\tATTRIBUTES:\n" +
                            "      |=0   0=|\t\t\t---------------\n" +
                            "      \\=._V_.=/\t\t\tEnergy:\t\t" + vPet.getEnergy() + "\n" +
                            "       )  `  (    ,\t\tFatigue:\t" + vPet.getFatigue() + "\n" +
                            "      /       \\  ((\t\tSleep:\t\t" + redColor + vPet.getSleep() + resetColor + "\n" +
                            "      |       |   ))\tHygiene:\t" + greenColor + vPet.getHygiene() + resetColor + "\n" +
                            "     /| |   | |\\_//\t\tWeight:\t\t"+ vPet.getWeight() + "\n" +
                            "     \\| |._.| |/-`\n" +
                            "      '\"'   '\"'\n" +
                            "------------------------------------------\n" +
                            "1.- Sleep \n2.- Play \n3.- Eat \n4.- Self-cleaning \n0.- Exit application\n" +
                            "------------------------------------------");
                    }
                    break;
            }

            if (vPet.getEnergy() > 100){
                vPet.setDeadCause("excess energy");
                loop = false;
            }else if (vPet.getEnergy() < 0){
                vPet.setDeadCause("exhaustion");
                loop = false;
            }else if (vPet.getFatigue() > 100){
                vPet.setDeadCause("excess fatigue");
                loop = false;
            }else if (vPet.getFatigue() < 0){
                vPet.setDeadCause("lack of fatigue");
                loop = false;
            }else if (vPet.getSleep() > 100){
                vPet.setDeadCause("not sleeping");
                loop = false;
            }else if (vPet.getSleep() < 0){
                vPet.setDeadCause("sleeping too much");
                loop = false;
            }else if (vPet.getHygiene() > 100){
                vPet.setDeadCause("excess hygiene");
                loop = false;
            }else if (vPet.getHygiene() < 0){
                vPet.setDeadCause("being a pig");
                loop = false;
            }else if (vPet.getWeight() > 100){
                vPet.setDeadCause("fatness");
                loop = false;
            }else if (vPet.getWeight() < 0){
                vPet.setDeadCause("hunger");
                loop = false;
            }
        }while (loop);

        if (vPet.getDeadCause().isEmpty()){
            System.out.println(title);
            if (vPet.getPet().equals("dog")){
                System.out.println(
                        "\t       /^-^\\\t\tATTRIBUTES:\n" +
                        "\t      / o o \\\t\t---------------\n" +
                        "\t     /   Y   \\\t\tEnergy:\t\t" + vPet.getEnergy() + "\n" +
                        "\t     V \\ v / V\t\tFatigue:\t"+ vPet.getFatigue() + "\n" +
                        "\t       / - \\\t\tSleep:\t\t"+ vPet.getSleep() + "\n" +
                        "\t      /    |\t\tHygiene:\t"+ vPet.getHygiene() +"\n" +
                        "\t(    /     |\t\tWeight:\t\t"+ vPet.getWeight() + "\n" +
                        "\t ===/___) ||\n" +
                        "------------------------------------------\n");
            }else {
                System.out.println(
                        "      ,_     _,\n" +
                        "      |\\\\___//|\t\t\tATTRIBUTES:\n" +
                        "      |=0   0=|\t\t\t---------------\n" +
                        "      \\=._V_.=/\t\t\tEnergy:\t\t" + vPet.getEnergy() + "\n" +
                        "       )  `  (    ,\t\tFatigue:\t"+ vPet.getFatigue() + "\n" +
                        "      /       \\  ((\t\tSleep:\t\t"+ vPet.getSleep() + "\n" +
                        "      |       |   ))\tHygiene:\t"+ vPet.getHygiene() +"\n" +
                        "     /| |   | |\\_//\t\tWeight:\t\t"+ vPet.getWeight() + "\n" +
                        "     \\| |._.| |/-`\n" +
                        "      '\"'   '\"'\n" +
                        "------------------------------------------");
            }
        }else {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("\t\t\t\tGAME OVER " +"\n------------------------------------------");
            if (vPet.getPet().equals("dog")){
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
            System.out.println(String.format("%s%s", Character.toUpperCase(vPet.getName().charAt(0)), vPet.getName().substring(1)) + " died of " + vPet.getDeadCause() + ".");
        }
    }
}