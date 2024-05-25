package characters;

import items.Item;
import items.Mitra;

public class Priest extends Character {
    public Priest(String name) {
        super(name, "src/images/priest/priest_");
        this.lives = 4;
        this.speed = 8;
    }

    @Override
    public boolean hitByEnemy(Enemy enemy) {
        if (this.specialAbility){
            this.specialAbility = false;
            this.items.removeIf(item -> item instanceof Mitra);
        }else {
            this.lives--;
        }
        this.location.setLocation(startPoint.getLocation());
        return false;
    }

    @Override
    public void addItem(Item item) {
        super.addItem(item);
        if (item instanceof Mitra){
            specialAbility = true;
        }
    }
}
