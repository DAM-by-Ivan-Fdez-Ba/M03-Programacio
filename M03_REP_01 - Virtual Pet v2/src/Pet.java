public class Pet {
    private String pet;
    private String name;
    private int energy;
    private int fatigue;
    private int sleep;
    private int hygiene;
    private int weight;
    private String deadCause;

    public Pet() {
    }

    public String getPet() {
        return this.pet;
    }

    public String getName() {
        return this.name;
    }

    public int getEnergy() {
        return this.energy;
    }

    public int getFatigue() {
        return this.fatigue;
    }

    public int getSleep() {
        return this.sleep;
    }

    public int getHygiene() {
        return this.hygiene;
    }

    public int getWeight() {
        return this.weight;
    }

    public String getDeadCause() {
        return this.deadCause;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }

    public void setSleep(int sleep) {
        this.sleep = sleep;
    }

    public void setHygiene(int hygiene) {
        this.hygiene = hygiene;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setDeadCause(String deadCause) {
        this.deadCause = deadCause;
    }
}
