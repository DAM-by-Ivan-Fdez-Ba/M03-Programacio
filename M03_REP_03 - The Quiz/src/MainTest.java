import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void printResultWithPercentage() {
        int correctAnswers = 10;
        int totalQuestions = 10;
        float hitPercentage = 100;

        assertEquals(Main.testPrintResultWithPercentage(correctAnswers, totalQuestions),hitPercentage);
    }

    @Test
    void checkAnswer() {
        int [] order = {4,2,3,1};
        char option = 'd';
        assertTrue(Main.testCheckAnswer(order, option));
    }
}