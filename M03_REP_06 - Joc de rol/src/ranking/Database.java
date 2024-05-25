package ranking;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Database {
    private static final String db_url = "jdbc:mysql://localhost:3306/joc_de_rol";
    private static final String db_user = "admin";
    private static final String db_pass = "admin";

    public static JScrollPane ranking(){
        String query = "select * from ranking";
        String[] columnNames = {"Name", "Character", "Time", "Lives", "Gold", "Date"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        try {
            Connection conn = DriverManager.getConnection(db_url, db_user, db_pass);
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString(1);
                String characterType = rs.getString(2);
                int time = rs.getInt(3);
                int lives = rs.getInt(4);
                int gold = rs.getInt(5);
                Date date = rs.getDate(6);

                Object[] row = {name, characterType, time, lives, gold, date};
                tableModel.addRow(row);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Database connection failed.");
        }
        JTable table = new JTable(tableModel);
        return new JScrollPane(table);
    }

    public static void insertScore(Score score){
        String insertQy = "insert into ranking values(?,?,?,?,?,?)";

        try{
            Connection conn = DriverManager.getConnection(db_url, db_user, db_pass);
            PreparedStatement ps = conn.prepareStatement(insertQy);
            ps.setString(1, score.getPlayerName());
            ps.setString(2, score.getCharacter());
            ps.setInt(3, score.getTime());
            ps.setInt(4, score.getLives());
            ps.setInt(5, score.getGold());
            ps.setDate(6, Date.valueOf(score.getDate()));
            int addRows = ps.executeUpdate();
            if(addRows > 0){
                System.out.println("Score inserted successfully.");
            }
            ps.close();
            conn.close();
        }catch (SQLException e){
            System.out.println("Database connection failed.");
        }
    }
}
