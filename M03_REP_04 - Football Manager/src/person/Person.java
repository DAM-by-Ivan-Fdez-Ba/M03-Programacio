package person;

import ivan.Utils;

/**
 * Abstract class person
 * Attributes and functions to be inherited by persons.
 */
public abstract class Person {
    // Own attributes.
    protected String birthDate, name, surname;
    protected double annualSalary, motivationLevel;

    // Constructors
    /**
     * Constructor to create persons.
     */
    public Person() {
        this.name = Utils.introduceString("Enter the name.");
        this.surname = Utils.introduceString("Enter the surname.");
        this.birthDate = Utils.introduceString("Enter the date of birth.");
        this.motivationLevel = 5;
        this.annualSalary = Utils.introduceDouble("Enter the annual salary.");
    }

    /**
     * Constructor to import persons.
     */
    public Person(String info){
        String[] personInfo;
        personInfo = info.split(":");

        this.name = personInfo[0];
        this.surname = personInfo[1];
        this.birthDate = personInfo[2];
        this.annualSalary = Double.parseDouble(personInfo[3]);
        this.motivationLevel = Double.parseDouble(personInfo[4]);
    }

    // Getters and setters
    public double getMotivationLevel() {
        return this.motivationLevel;
    }

    // Own methods
    public String fullName(){
        return this.name + " " + this.surname;
    }

    public String training(){
        this.motivationLevel += 0.2;
        return "The new motivation level of " + this.name + " is " + this.motivationLevel + ".";
    }

    public String export(){
        String person;

        person = this.name + ":";
        person += this.surname + ":";
        person += this.birthDate + ":";
        person += this.annualSalary + ":";
        person += this.motivationLevel + ":";

        return person;
    }
}
