package characters;

import items.Item;
import items.Sword;
import utils.StatusBar;

public class Warrior extends Character {
    public Warrior(String name) {
        super(name, "src/images/warrior/warrior_");
        this.lives = 5;
        this.speed = 6;
    }

    @Override
    public boolean hitByEnemy(Enemy enemy) {
        if (this.specialAbility){
            this.specialAbility = false;
            this.items.removeIf(item -> item instanceof Sword);
            StatusBar.update();
            enemy.die();
            return true;
        }else {
            this.lives--;
            this.location = startPoint.getLocation();
        }
        StatusBar.update();
        return false;
    }

    @Override
    public void addItem(Item item) {
        super.addItem(item);
        if (item instanceof Sword){
            specialAbility = true;
        }
    }
}
