package items;

import javax.swing.*;
import java.awt.*;

public class Potion extends Item {
    public Potion(){
        this.itemId = 3;
        this.name = "Potion";
        this.imageSrc = "src/images/dungeon/potion.png";
        this.constructorPt2();
    }
}
