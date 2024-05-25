package forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import characters.*;
import characters.Character;
import dungeon.Dungeon;
import items.*;
import ranking.Database;
import ranking.Score;
import utils.Clock;
import utils.StatusBar;


public class Game {
    public static ArrayList<Enemy> enemies;
    public static JLayeredPane game = new JLayeredPane();
    public static Character character;
    public static Dungeon dungeon = new Dungeon();
    private static JFrame frame;

    public static JLayeredPane start(int characterType, String name, JFrame frame){
        createCharacter(characterType, name);
        Game.frame = frame;

        game.setLayout(null);
        game.setPreferredSize(new Dimension(800, 800));
        game.setMinimumSize(new Dimension(800, 800));
        game.setBounds(0,0,800,800);

        game.add(dungeon.getMap(), JLayeredPane.DEFAULT_LAYER);

        game.add(character.getLabel(),JLayeredPane.PALETTE_LAYER);

        generateItems();
        generateEnemies();

        Clock.start();
        StatusBar.create();

        game.addKeyListener(new PanelMainListener());
        game.setFocusable(true);
        return game;
    }

    public static void finish(){
        if (character.getGold() >= 50){
            dungeon.openDoor();
            if (character.getLabel().getX() > 800){
                stop();
            }
        }else if (character.getLives() <= 0){
            stop();
        }
    }

    private static void saveGame(){
        Score score = new Score(character.getName(), character.getType(), Integer.parseInt(Clock.getTime()), character.getLives(), character.getGold());
        Database.insertScore(score);
        JOptionPane.showMessageDialog(frame, score.getScore(), "Score", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void stop() {
        Clock.stop();
        saveGame();
        frame.dispose();
    }

    private static void createCharacter(int characterType, String name){
        switch (characterType){
            case 0:
                character = new Wizard(name);
                break;
            case 1:
                character = new Warrior(name);
                break;
            case 2:
                character = new Priest(name);
                break;
        }
    }

    private static void generateItems() {
        for(Item item : Dungeon.items) {
            game.add(item.getItemLabel(), JLayeredPane.PALETTE_LAYER);
        }
    }

    private static void generateEnemies(){
        enemies = new ArrayList<>();

        Enemy enemy0 = new Enemy(new Point(80,700), Movable.DOWN, 3);
        Enemy enemy1 = new Enemy(new Point(700, 112), Movable.RIGHT, 5);
        Enemy enemy2 = new Enemy(new Point(80, 325), Movable.RIGHT, 3);
        Enemy enemy3 = new Enemy(new Point(700, 400), Movable.RIGHT, 4);
        Enemy enemy4 = new Enemy(new Point(425, 700), Movable.UP, 3);
        Enemy enemy5 = new Enemy(new Point(200, 112), Movable.UP, 2);

        enemies.add(enemy0);
        enemies.add(enemy1);
        enemies.add(enemy2);
        enemies.add(enemy3);
        enemies.add(enemy4);
        enemies.add(enemy5);

        for (Enemy enemy : enemies){
            game.add(enemy.getLabel(),JLayeredPane.PALETTE_LAYER);
        }
    }

    private static class PanelMainListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT, KeyEvent.VK_D:
                    character.changeImage(Movable.RIGHT);
                    character.startMoveTimer(Character.RIGHT);
                    break;
                case KeyEvent.VK_LEFT, KeyEvent.VK_A:
                    character.changeImage(Movable.LEFT);
                    character.startMoveTimer(Character.LEFT);
                    break;
                case KeyEvent.VK_UP, KeyEvent.VK_W:
                    character.changeImage(Movable.UP);
                    character.startMoveTimer(Character.UP);
                    break;
                case KeyEvent.VK_DOWN, KeyEvent.VK_S:
                    character.changeImage(Movable.DOWN);
                    character.startMoveTimer(Character.DOWN);
                    break;
            }
            game.requestFocus();
            finish();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT, KeyEvent.VK_D, KeyEvent.VK_DOWN, KeyEvent.VK_S, KeyEvent.VK_UP, KeyEvent.VK_W,
                     KeyEvent.VK_LEFT, KeyEvent.VK_A:
                    character.stopMoveTimer();
                    break;
            }
        }
    }
}
