package utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Clock {
    private static Timer timer;
    private static int seconds;

    public static void start(){
        seconds = 0;

        timer = new Timer(1000, new TimerActionListener());
        timer.start();
    }

    public static void stop(){
        timer.stop();
    }

    public static String getTime(){
        return String.valueOf(seconds);
    }

    private static class TimerActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds++;
            StatusBar.update();
        }
    }
}
