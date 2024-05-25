package items;

import characters.Character;
import dungeon.Dungeon;

import javax.swing.*;
import java.awt.*;

public abstract class Item {
    protected int itemId;
    protected String name;
    protected String imageSrc;
    protected Point location;
    protected JLabel itemLabel;

    protected void constructorPt2(){
        ImageIcon imageIcon = new ImageIcon(imageSrc);
        Icon icon = new ImageIcon(imageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        this.itemLabel = new JLabel(icon);
        this.itemLabel.setSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        this.location = new Point();
        this.randomSpawn();
    }

    public boolean checkCharacterColision(Character character){
        if(character.getLabel().getBounds().intersects(this.itemLabel.getBounds())){
            character.addItem(this);
            this.itemLabel.setVisible(false);
            Dungeon.mapStructure[this.itemLabel.getY()/32][this.itemLabel.getX()/32] = 0;
            return true;
        }
        return false;
    }

    public JLabel getItemLabel() {
        return itemLabel;
    }

    protected void randomSpawn(){
        int x;
        int y;
        boolean bucle = true;
        do {
            x = (int) (Math.random() * Dungeon.mapStructure.length);
            y = (int) (Math.random() * Dungeon.mapStructure[0].length);
            if (Dungeon.mapStructure[y][x] == 0){
                Dungeon.mapStructure[y][x] = this.itemId;
                this.location = new Point(x*32, y*32);
                bucle = false;
            }
        }while (bucle);
        this.itemLabel.setLocation(this.location);
        this.itemLabel.setVisible(true);
    }
}
