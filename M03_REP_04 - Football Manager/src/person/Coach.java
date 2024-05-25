package person;

import ivan.Utils;

/**
 * Class Coach
 * Inherits from the class person.
 */
public class Coach extends Person {
    // Own attributes.
    private int tournamentsWon;
    private boolean nationalCoach;

    // Constructors
    /**
     * Constructor to create coaches.
     */
    public Coach() {
        super();
        this.tournamentsWon = Utils.introduceInt("Enter the number of tournaments won.");;
        this.nationalCoach = Utils.introduce("y","n", "Is he a national coach? (Y,N)");;
    }

    /**
     * Constructor to import trainers.
     */
    public Coach(String info){
        super(info);
        String[] coachInfo;
        coachInfo = info.split(":");
        this.tournamentsWon = Integer.parseInt(coachInfo[5]);
        this.nationalCoach = Boolean.parseBoolean(coachInfo[6]);
    }

    // Own methods
    public void increaseSalary(){
        this.annualSalary *= 1.005;
    }

    // Overwritten methods.
    @Override
    public String training(){
        if (this.nationalCoach){
           this.motivationLevel += 0.3;
        }else {
            this.motivationLevel += 0.15;
        }
        return "The new motivation level of " + this.fullName() + " is " + this.motivationLevel + ".";
    }

    @Override
    public String export(){
        String coach = super.export();

        coach += this.tournamentsWon + ":";
        coach += this.nationalCoach + ";";

        return coach;
    }
}
