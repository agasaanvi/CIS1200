package org.cis1200;

import org.cis1200.wordle.Wordle;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WordleTests {

    @Test
    public void testEncapsulation() {
        Wordle t = new Wordle();
        t.setWord("beach");
        int x;
        x = t.playTurn("beach");
        int[] board2 = t.getBoard();
        for (int i = 0; i < 5; i++) {
            board2[i] = 0;
        }
        assertFalse(Arrays.equals(board2, t.getBoard()));
    }

    @Test
    public void testLastWordIsAns() {
        Wordle t = new Wordle();
        t.setWord("shock");
        int z;
        int count;
        int[] expected = new int[5];
        int[] actual;

        z = t.playTurn("angry");
        count = t.getAttempt();
        actual = t.getBoard();
        expected[0] = 8;
        expected[1] = 8;
        expected[2] = 8;
        expected[3] = 8;
        expected[4] = 8;
        assertTrue(Arrays.equals(actual, expected));
        assertEquals(count, 1);

        z = t.playTurn("arise");
        count = t.getAttempt();
        actual = t.getBoard();
        expected[0] = 8;
        expected[1] = 8;
        expected[2] = 8;
        expected[3] = 7;
        expected[4] = 8;
        assertTrue(Arrays.equals(actual, expected));
        assertEquals(count, 2);

        z = t.playTurn("magic");
        count = t.getAttempt();
        actual = t.getBoard();
        expected[0] = 8;
        expected[1] = 8;
        expected[2] = 8;
        expected[3] = 8;
        expected[4] = 7;
        assertTrue(Arrays.equals(actual, expected));
        assertEquals(count, 3);

        z = t.playTurn("white");
        count = t.getAttempt();
        actual = t.getBoard();
        expected[0] = 8;
        expected[1] = 9;
        expected[2] = 8;
        expected[3] = 8;
        expected[4] = 8;
        assertTrue(Arrays.equals(actual, expected));
        assertEquals(count, 4);

        z = t.playTurn("place");
        count = t.getAttempt();
        actual = t.getBoard();
        expected[0] = 8;
        expected[1] = 8;
        expected[2] = 8;
        expected[3] = 9;
        expected[4] = 8;
        assertTrue(Arrays.equals(actual, expected));
        assertEquals(count, 5);

        z = t.playTurn("shock");
        count = t.getAttempt();
        actual = t.getBoard();
        expected[0] = 9;
        expected[1] = 9;
        expected[2] = 9;
        expected[3] = 9;
        expected[4] = 9;
        assertTrue(Arrays.equals(actual, expected));
        assertEquals(count, 6);
    }

    @Test
    public void testFirstWordIsAns() {
        Wordle t = new Wordle();
        t.setWord("board");
        int z;
        int count;
        int[] expected = new int[5];
        int[] actual;

        z = t.playTurn("board");
        count = t.getAttempt();
        actual = t.getBoard();
        expected[0] = 9;
        expected[1] = 9;
        expected[2] = 9;
        expected[3] = 9;
        expected[4] = 9;
        assertTrue(Arrays.equals(actual, expected));
        assertEquals(count, 1);
        assertTrue(t.getGameOver());

    }

    @Test
    public void testInvalidGuesses() {
        Wordle t = new Wordle();
        t.setWord("shock");
        int z;
        int count;
        int[] expected = new int[5];
        int[] actual;

        z = t.playTurn("angry");
        count = t.getAttempt();
        actual = t.getBoard();
        expected[0] = 8;
        expected[1] = 8;
        expected[2] = 8;
        expected[3] = 8;
        expected[4] = 8;
        assertTrue(Arrays.equals(actual, expected));
        assertEquals(count, 1);

        z = t.playTurn("ijhosts");
        count = t.getAttempt();
        actual = t.getBoard();
        assertTrue(Arrays.equals(actual, expected));
        assertEquals(count, 1);

        z = t.playTurn("yuyfuy");
        count = t.getAttempt();
        actual = t.getBoard();
        assertTrue(Arrays.equals(actual, expected));
        assertEquals(count, 1);
    }

    @Test
    public void testNeverGetsAnswer() {
        Wordle t = new Wordle();
        t.setWord("shock");
        int z;
        int count;
        int[] expected = new int[5];
        int[] actual;

        z = t.playTurn("angry");
        count = t.getAttempt();
        actual = t.getBoard();
        expected[0] = 8;
        expected[1] = 8;
        expected[2] = 8;
        expected[3] = 8;
        expected[4] = 8;
        assertTrue(Arrays.equals(actual, expected));
        assertEquals(count, 1);

        z = t.playTurn("arise");
        count = t.getAttempt();
        actual = t.getBoard();
        expected[0] = 8;
        expected[1] = 8;
        expected[2] = 8;
        expected[3] = 7;
        expected[4] = 8;
        assertTrue(Arrays.equals(actual, expected));
        assertEquals(count, 2);

        z = t.playTurn("magic");
        count = t.getAttempt();
        actual = t.getBoard();
        expected[0] = 8;
        expected[1] = 8;
        expected[2] = 8;
        expected[3] = 8;
        expected[4] = 7;
        assertTrue(Arrays.equals(actual, expected));
        assertEquals(count, 3);

        z = t.playTurn("white");
        count = t.getAttempt();
        actual = t.getBoard();
        expected[0] = 8;
        expected[1] = 9;
        expected[2] = 8;
        expected[3] = 8;
        expected[4] = 8;
        assertTrue(Arrays.equals(actual, expected));
        assertEquals(count, 4);

        z = t.playTurn("place");
        count = t.getAttempt();
        actual = t.getBoard();
        expected[0] = 8;
        expected[1] = 8;
        expected[2] = 8;
        expected[3] = 9;
        expected[4] = 8;
        assertEquals(count, 5);

        z = t.playTurn("board");
        count = t.getAttempt();
        actual = t.getBoard();
        expected[0] = 8;
        expected[1] = 9;
        expected[2] = 8;
        expected[3] = 8;
        expected[4] = 8;
        assertTrue(Arrays.equals(actual, expected));
        assertEquals(count, 6);

        z = t.playTurn("place");
        z = t.playTurn("vague");
        actual = t.getBoard();
        assertEquals(0, z);
    }

    @Test
    public void testMiddleGuessIsAns() {
        Wordle t = new Wordle();
        t.setWord("magic");
        int z;
        int count;
        int[] expected = new int[5];
        int[] actual;

        z = t.playTurn("angry");
        count = t.getAttempt();
        actual = t.getBoard();
        expected[0] = 7;
        expected[1] = 8;
        expected[2] = 9;
        expected[3] = 8;
        expected[4] = 8;
        assertTrue(Arrays.equals(actual, expected));
        assertEquals(count, 1);

        z = t.playTurn("arise");
        count = t.getAttempt();
        actual = t.getBoard();
        expected[0] = 7;
        expected[1] = 8;
        expected[2] = 7;
        expected[3] = 8;
        expected[4] = 8;
        assertTrue(Arrays.equals(actual, expected));
        assertEquals(count, 2);

        z = t.playTurn("magic");
        actual = t.getBoard();
        expected[0] = 9;
        expected[1] = 9;
        expected[2] = 9;
        expected[3] = 9;
        expected[4] = 9;
        assertTrue(Arrays.equals(actual, expected));
        assertEquals(count, 2);
        assertEquals(3, z);

        t.playTurn("place");
        assertEquals(count, 2);
        assertEquals(3, z);

    }

    @Test
    public void testReset() {
        Wordle t = new Wordle();
        t.playTurn("value");
        t.reset();
        int[] actual = t.getBoard();
        int[] expected = new int[5];
        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void testGetWordBank() {
        Wordle t = new Wordle();
        List<String> wordBank = t.returnWordBank();
        int actual = wordBank.size();
        int expected = 362;
        assertEquals(expected, actual);
    }

}
