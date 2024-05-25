package characters;

import items.Item;
import items.Potion;

public class Wizard extends Character {
    public Wizard(String name) {
        super(name, "src/images/wizard/wizard_");
        this.lives = 3;
        this.speed = 10;
    }

    @Override
    public boolean hitByEnemy(Enemy enemy) {
        if (this.specialAbility){
            this.specialAbility = false;
            this.items.removeIf(item -> item instanceof Potion);
            this.lives++;
        }else {
            this.lives--;
        }
        this.location = startPoint.getLocation();
        return false;
    }

    @Override
    public void addItem(Item item) {
        super.addItem(item);
        if (item instanceof Potion){
            specialAbility = true;
        }
    }
}
