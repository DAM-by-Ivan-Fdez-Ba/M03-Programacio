package characters;

import items.Gold;
import items.Item;
import utils.StatusBar;

import java.awt.*;
import java.util.ArrayList;

public abstract class Character extends Movable{
    protected String name;
    protected int lives;
    protected int gold;
    protected ArrayList<Item> items;
    protected boolean specialAbility;
    protected static final Point startPoint = new Point(0,112);

    public Character (String name, String imageSrc){
        super(startPoint.getLocation(), 35);
        this.name = name;
        this.items = new ArrayList<>();
        this.gold = 0;
        this.specialAbility = false;
        this.direction = RIGHT;
        this.imageSrc = imageSrc;
        changeImage(this.direction);
        this.label.setSize(new Dimension(this.label.getIcon().getIconWidth(), this.label.getIcon().getIconHeight()));
        this.label.setLocation(startPoint.getLocation());
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public int getGold(){
        return this.gold;
    }

    public String getName(){
        return this.name;
    }

    public int getLives() {
        return this.lives;
    }

    public String getType(){
        if (this instanceof Priest){
            return "Priest";
        }else if (this instanceof Warrior){
            return "Warrior";
        }else if (this instanceof Wizard){
            return "Wizard";
        }else {
            return "None";
        }
    }

    public abstract boolean hitByEnemy(Enemy enemy);

    public void addItem(Item item){
        if (item instanceof Gold){
            this.gold += 10;
        }else {
            this.items.add(item);
            StatusBar.update();
        }
    }
}
