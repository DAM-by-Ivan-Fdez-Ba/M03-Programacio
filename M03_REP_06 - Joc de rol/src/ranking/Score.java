package ranking;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

public class Score {
    private String playerName;
    private String character;
    private int time;
    private int lives;
    private int gold;
    private LocalDate date;

    public Score(String playerName, String character, int time, int lives, int gold) {
        this.playerName = playerName;
        this.character = character;
        this.time = time;
        this.lives = lives;
        this.gold = gold;
        this.date = LocalDate.now();
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public String getCharacter() {
        return this.character;
    }

    public int getTime() {
        return this.time;
    }

    public int getLives() {
        return this.lives;
    }

    public int getGold() {
        return this.gold;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public JScrollPane getScore() {
        String[] columnNames = {"Name", "Character", "Time", "Lives", "Gold", "Date"};
        Object[][] row = {{this.playerName, this.character, this.time, this.lives, this.gold, this.date}};

        JScrollPane jScrollPane = new JScrollPane(new JTable(new DefaultTableModel(row, columnNames)));
        jScrollPane.setPreferredSize(new Dimension(350, 150));

        return jScrollPane;
    }
}
