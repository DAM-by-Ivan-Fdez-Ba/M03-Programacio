package characters;


import java.awt.*;

public class Enemy extends Movable{
    public Enemy(Point location, int direction, int speed) {
        super(location, 42);
        this.direction = direction;
        this.speed = speed;
        this.imageSrc = "src/images/skeleton/skeleton_";
        changeImage(this.direction);
        this.label.setSize(new Dimension(this.label.getIcon().getIconWidth(), this.label.getIcon().getIconHeight()));
        this.label.setLocation(location);
        moveTimer.start();
    }

    public boolean checkCharacterColision(Character character){
        if(character.getLabel().getBounds().intersects(this.label.getBounds())){
            return character.hitByEnemy(this);
        }
        return false;
    }

    public void die(){
        this.moveTimer.stop();
        this.label.setVisible(false);
        this.label = null;
    }

    @Override
    public void move() {
        super.move();
        if (checkForWallsCollisions()){
            this.direction = switch (this.direction){
                case RIGHT -> LEFT;
                case LEFT -> RIGHT;
                case UP -> DOWN;
                case DOWN -> UP;
                default -> UP;
            };
            changeImage(this.direction);
        }
    }
}
