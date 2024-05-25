package utils;

import forms.Game;

import javax.swing.*;
import java.awt.*;

public class StatusBar {
    private static JPanel statusBar;

    public static void create(){
        statusBar = new JPanel();
        statusBar.setBounds(32,16,736,32);
        statusBar.setPreferredSize(new Dimension(736,32));
        statusBar.setMinimumSize(new Dimension(736,32));
        statusBar.setLayout(null);
        statusBar.setBackground(Color.LIGHT_GRAY);
        Game.game.add(statusBar, JLayeredPane.MODAL_LAYER);
        update();
    }

    public static void update(){
        statusBar.removeAll();

        for (int i = 0; i < Game.character.getLives(); i++) {
            ImageIcon imageIcon = new ImageIcon("src/images/dungeon/heart.png");
            Icon icon = new ImageIcon(imageIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
            JLabel label = new JLabel(icon);
            label.setSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
            label.setLocation(4 + i*36, 0);
            statusBar.add(label);
        }

        JLabel goldLabel = new JLabel();
        JLabel goldQuantity = new JLabel(String.valueOf(Game.character.getGold()));
        ImageIcon goldImage = new ImageIcon("src/images/dungeon/gold.png");
        Icon goldIcon = new ImageIcon(goldImage.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
        goldLabel.setIcon(goldIcon);
        goldLabel.setBounds(500,0,32,32);
        goldQuantity.setBounds(540,0,32,32);
        statusBar.add(goldLabel);
        statusBar.add(goldQuantity);

        for (int i = 0; i < Game.character.getItems().size(); i++) {
            Game.character.getItems().get(i).getItemLabel().setLocation(630 + i*36, 4);
            statusBar.add(Game.character.getItems().get(i).getItemLabel());
            Game.character.getItems().get(i).getItemLabel().setVisible(true);
        }

        JLabel timerLabel = new JLabel();
        timerLabel.setBounds((736/2),0,128,32);
        ImageIcon clockImage = new ImageIcon("src/images/clock.png");
        Icon clockIcon = new ImageIcon(clockImage.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
        timerLabel.setIcon(clockIcon);

        JLabel timerTime = new JLabel(Clock.getTime());
        timerTime.setBounds((736/2)+32,0,128,32);

        statusBar.add(timerLabel);
        statusBar.add(timerTime);

        statusBar.repaint();
        Game.game.requestFocus();
    }

}
