package characters;

import dungeon.Dungeon;
import forms.Game;
import items.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Movable {
    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    protected JLabel label;
    protected Timer moveTimer;
    protected int direction;
    protected Point location;
    protected int speed;
    protected String imageSrc;
    protected int SIZE;

    public Movable(Point location, int size) {
        this.location = location;
        this.label = new JLabel();
        this.moveTimer = new Timer(50, new TimerActionListener());
        this.moveTimer.setInitialDelay(0);
        this.SIZE = size;
    }

    public void startMoveTimer(int direction){
        this.direction = direction;
        this.moveTimer.start();
    }

    public void stopMoveTimer(){
        moveTimer.stop();
    }

    protected class TimerActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            move();
        }
    }

    public void move(){
        if (!checkForWallsCollisions()){
            switch (this.direction){
                case RIGHT:
                    this.location.x += this.speed;
                    break;
                case LEFT:
                    if (!outOfMap()){
                        this.location.x -= this.speed;
                    }
                    break;
                case UP:
                    this.location.y -= this.speed;
                    break;
                case DOWN:
                    this.location.y += this.speed;
                    break;
            }
            this.label.setLocation(this.location);
        }

        if (this instanceof Character){
            for (Item item : Dungeon.items){
                item.checkCharacterColision((Character) this);
            }
            Game.enemies.removeIf(enemy -> enemy.checkCharacterColision((Character) this));
        }
    }

    protected boolean outOfMap(){
        if (this.label.getX() - this.speed <= 0){
            this.location.x = 0;
            return true;
        }
        return false;
    }

    protected boolean checkForWallsCollisions(){
        Rectangle labelBounds = this.label.getBounds();
        switch (this.direction){
            case RIGHT:
                labelBounds.x += this.speed;
                break;
            case LEFT:
                labelBounds.x -= this.speed;
                break;
            case UP:
                labelBounds.y -= this.speed;
                break;
            case DOWN:
                labelBounds.y += this.speed;
                break;
        }
        for(Rectangle wall : Dungeon.walls){
            if(labelBounds.intersects(wall)){
                switch (this.direction){
                    case RIGHT:
                        this.location.x = wall.x - this.label.getWidth();
                        return true;
                    case LEFT:
                        this.location.x = wall.x + wall.width;
                        return true;
                    case UP:
                        this.location.y = wall.y + wall.height;
                        return true;
                    case DOWN:
                        this.location.y = wall.y - this.label.getHeight();
                        return true;
                }
            }
        }
        return false;
    }

    public void changeImage(int direction){
        ImageIcon imageIcon = new ImageIcon(this.imageSrc +
                switch (direction){
                    case RIGHT -> "right.gif";
                    case LEFT -> "left.gif";
                    case UP -> "up.gif";
                    case DOWN -> "down.gif";
                    default -> "down.gif";
                });
        Icon icon = new ImageIcon(imageIcon.getImage().getScaledInstance(this.SIZE, this.SIZE, Image.SCALE_DEFAULT));
        this.label.setIcon(icon);
    }

    public JLabel getLabel() {
        return this.label;
    }
}
