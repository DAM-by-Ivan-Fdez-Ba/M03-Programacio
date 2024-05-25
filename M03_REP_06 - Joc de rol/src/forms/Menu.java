package forms;

import ranking.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Menu {
    public JLayeredPane menu;
    private int selected = 1;
    private final JLabel wizard = new JLabel();
    private final JLabel warrior = new JLabel();
    private final JLabel priest = new JLabel();
    private final JTextField nameField = new JTextField();
    private final JFrame frame;

    public Menu(JFrame frame){
        menu = new JLayeredPane();
        menu.setLayout(null);
        menu.setPreferredSize(new Dimension(800, 800));
        menu.setMinimumSize(new Dimension(800, 800));
        menu.setBounds(0,0,800,800);

        this.frame = frame;

        JLabel background = new JLabel();
        ImageIcon imageIcon = new ImageIcon("src/images/background.png");
        Icon icon = new ImageIcon(imageIcon.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH));
        background.setIcon(icon);
        background.setBounds(0, 0, 800, 800);

        menu.add(background, JLayeredPane.DEFAULT_LAYER);
        menu.addKeyListener(new Menu.PanelMenuListener());
        menu.setFocusable(true);
        title();
        characters();
        nameBox();
        playButton();
        rankingButton();
    }

    private class playClick extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if (nameField.getText().isEmpty()){
                JOptionPane.showMessageDialog(frame, "Please enter your name");
            }else {
                frame.remove(menu);
                frame.setContentPane(Game.start(selected, nameField.getText(), frame));
            }
        }
    }

    private class rankingClick extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            JOptionPane.showMessageDialog(frame, Database.ranking(), "RANKING", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private class characterClick extends MouseAdapter {
        private final int character;

        characterClick(int character){
            this.character = character;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            selected = character;
            changeSelection();
            menu.requestFocus();
        }
    }

    private class PanelMenuListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);

            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT, KeyEvent.VK_D:
                    if (selected < 2) {
                        selected++;
                        changeSelection();
                    }
                    break;
                case KeyEvent.VK_LEFT, KeyEvent.VK_A:
                    if (selected > 0) {
                        selected--;
                        changeSelection();
                    }
                    break;
            }
        }
    }

    private void title(){
        JLabel text = new JLabel("JOC DE ROL");
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/game_of_thrones.ttf"));
            text.setFont(font.deriveFont(Font.PLAIN, 50));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setBounds(0,0, 800,200);
        menu.add(text, JLayeredPane.PALETTE_LAYER);
    }

    private void playButton(){
        JButton play = new JButton("PLAY");
        play.setBounds(250, 650, 300, 50);
        play.addMouseListener(new playClick());
        menu.add(play, JLayeredPane.PALETTE_LAYER);
    }

    private void rankingButton(){
        JButton ranking = new JButton("RANKING");
        ranking.setBounds(300, 720, 200, 40);
        ranking.addMouseListener(new rankingClick());
        menu.add(ranking, JLayeredPane.PALETTE_LAYER);
    }

    private void nameBox(){

        JLabel text = new JLabel("Name: ");

        text.setFont(text.getFont().deriveFont(Font.BOLD,20));

        text.setBounds(60,510, 600,40);
        nameField.setBounds(53, 550, 694, 40);

        menu.add(text,JLayeredPane.PALETTE_LAYER);
        menu.add(nameField, JLayeredPane.PALETTE_LAYER);
    }

    private void changeSelection() {
        ImageIcon imageWizard;
        ImageIcon imageWarrior;
        ImageIcon imagePriest;
        Icon iconWizard;
        Icon iconWarrior;
        Icon iconPriest;

        switch (selected) {
            case 0:
                imageWizard = new ImageIcon("src/images/wizard/wizard_down.gif");
                iconWizard = new ImageIcon(imageWizard.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
                wizard.setIcon(iconWizard);

                imageWarrior = new ImageIcon("src/images/warrior/warrior.png");
                iconWarrior = new ImageIcon(imageWarrior.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                warrior.setIcon(iconWarrior);

                imagePriest = new ImageIcon("src/images/priest/priest.png");
                iconPriest = new ImageIcon(imagePriest.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                priest.setIcon(iconPriest);
                break;
            case 1:
                imageWarrior = new ImageIcon("src/images/warrior/warrior_down.gif");
                iconWarrior = new ImageIcon(imageWarrior.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
                warrior.setIcon(iconWarrior);

                imageWizard = new ImageIcon("src/images/wizard/wizard.png");
                iconWizard = new ImageIcon(imageWizard.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                wizard.setIcon(iconWizard);

                imagePriest = new ImageIcon("src/images/priest/priest.png");
                iconPriest = new ImageIcon(imagePriest.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                priest.setIcon(iconPriest);
                break;
            case 2:
                imagePriest = new ImageIcon("src/images/priest/priest_down.gif");
                iconPriest = new ImageIcon(imagePriest.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
                priest.setIcon(iconPriest);

                imageWarrior = new ImageIcon("src/images/warrior/warrior.png");
                iconWarrior = new ImageIcon(imageWarrior.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                warrior.setIcon(iconWarrior);

                imageWizard = new ImageIcon("src/images/wizard/wizard.png");
                iconWizard = new ImageIcon(imageWizard.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                wizard.setIcon(iconWizard);
                break;
        }
    }

    private void characters(){
        wizard.setBounds(53, 250, 200, 200);
        menu.add(wizard, JLayeredPane.PALETTE_LAYER);

        warrior.setBounds(306, 250, 200, 200);
        menu.add(warrior, JLayeredPane.PALETTE_LAYER);

        priest.setBounds(559, 250, 200, 200);
        menu.add(priest, JLayeredPane.PALETTE_LAYER);

        priest.addMouseListener(new characterClick(2));
        warrior.addMouseListener(new characterClick(1));
        wizard.addMouseListener(new characterClick(0));
        changeSelection();
    }

    private static class FrameWindowsListener extends WindowAdapter {
        JFrame frame;

        public FrameWindowsListener(JFrame frame) {
            this.frame = frame;
        }

        @Override
        public void windowClosing(WindowEvent e){
            super.windowClosing(e);

            int confirmado = JOptionPane.showConfirmDialog(null,
                    "¿Estás seguro de cerrar la ventana?",
                    "Mensaje",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (confirmado == JOptionPane.YES_OPTION) {
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            } else {
                frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Joc de Rol");
        frame.setContentPane(new Menu(frame).menu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        frame.addWindowListener(new FrameWindowsListener(frame));
    }
}
