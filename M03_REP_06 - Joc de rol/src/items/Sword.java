package items;

import javax.swing.*;
import java.awt.*;

public class Sword extends Item {
    public Sword(){
        this.itemId = 4;
        this.name = "Sword";
        this.imageSrc = "src/images/dungeon/sword.png";
        this.constructorPt2();
    }
}
