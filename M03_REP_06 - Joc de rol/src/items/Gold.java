package items;

import characters.Character;
import dungeon.Dungeon;

public class Gold extends Item{
    public Gold(){
        this.itemId = 2;
        this.name = "Gold";
        this.imageSrc = "src/images/dungeon/gold.png";
        this.constructorPt2();
    }

    @Override
    public boolean checkCharacterColision(Character character) {
        boolean collissioned = super.checkCharacterColision(character);
        if(collissioned){
            this.randomSpawn();
            return true;
        }
        return false;
    }

}
