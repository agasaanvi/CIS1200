package org.cis1200.wordle;

/**
 * CIS 120 HW09 - TicTacToe Demo
 * (c) University of Pennsylvania
 * Created by Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020.
 */

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is a model for Wordle.
 * 
 * This game adheres to a Model-View-Controller design framework.
 *
 * 
 * This model is completely independent of the view and controller.
 * This is in keeping with the concept of modularity! We can play
 * the whole game from start to finish without ever drawing anything
 * on a screen or instantiating a Java Swing object.
 * 
 * Run this file to see the main method play a game of Wordle,
 * visualized with Strings printed to the console.
 */
public class Wordle {

    private int[] board;
    private int attempt;
    private boolean gameOver;
    private List<String> validWords;
    private String word;

    private String[] wordArr;
    private boolean finalWinner;

    /**
     * Constructor sets up game state.
     */
    public Wordle() {
        reset();
    }

    /**
     * resets all the values in the game
     */
    public void reset() {
        board = new int[5];
        attempt = 0;
        gameOver = false;
        finalWinner = false;
        validWords = new ArrayList<String>();
        getWordBank();
        word = getRadWord();
        wordArr = createWordArr(word);
    }

    /**
     * splits the guess into an array of characters
     */
    public String[] createWordArr(String w) {
        String[] arr = w.split("");
        return arr;
    }

    /**
     * gets a random word for the game to use as answer
     */
    public String getRadWord() {
        int max = validWords.size();
        int rad = (int) ((Math.random() * (max)));
        String m = validWords.get(rad);
        m = m.trim().toLowerCase();
        return m;
    }

    /**
     * gets word bank from file
     */
    public void getWordBank() {
        try {
            validWords = Files.readAllLines(Path.of("src/main/java/org/cis1200/wordle/Words.txt"));
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    /**
     * everytime the user enters in anything, playTurn is called
     * it checks whether the word is valid and if its the answer
     * it then returns a value representing the mode of the game
     * 0 - game over and you did not win
     * 1 - valid guess but not the answer
     * 2 - invalid guess
     * 3 - the correct answer
     */
    public int playTurn(String input) {
        String compare = input.trim().toLowerCase();
        if (finalWinner) {
            return 3;
        }
        if (gameOver && attempt > 5) {
            return 0;
        }
        if (attempt > 6) {
            return 0;
        }
        if (gameOver && attempt <= 5) {
            return 3;
        }
        if (compare.length() != 5) {
            return 2;
        }
        boolean check = checkValidWord(compare);
        boolean winner = checkWinner(compare);
        if (check) {
            if (winner) {
                for (int i = 0; i < 5; i++) {
                    board[i] = 9;
                }
                attempt++;
                gameOver = true;
                finalWinner = true;
                return 3;
            }
            updateBoard(compare);
            attempt++;
            return 1;
        }
        return 2;
    }

    /**
     * checks if the word is in the word bank
     */
    public boolean checkValidWord(String guess) {
        if (validWords.contains(guess)) {
            return true;
        }
        return false;
    }

    /**
     * checks if the word is the answer
     */
    public boolean checkWinner(String inputGuess) {
        if (word.equals(inputGuess)) {
            return true;
        }
        return false;
    }

    /**
     * updates the array with the colors of the new guess
     * only if the guess is valid
     * the colors are represented by different integers
     * 9 - green
     * 8 - gray
     * 7 - yellow
     */
    public void updateBoard(String i) {
        String[] temp = i.split("");

        for (int y = 0; y < 5; y++) {
            if (word.contains(temp[y])) {
                if (wordArr[y].equals(temp[y])) {
                    board[y] = 9;
                } else {
                    board[y] = 7;
                }
            } else {
                board[y] = 8;
            }
        }
    }

    public String getWord() {
        return word;
    }

    public int getAttempt() {
        return attempt;
    }

    public boolean getWinner() {
        return finalWinner;
    }

    public void setWord(String w) {
        word = w;
        wordArr = createWordArr(w);
    }

    public List<String> returnWordBank() {
        List<String> temp = new ArrayList<>();
        temp.addAll(validWords);
        return temp;
    }

    /**
     * recreates a visual version of wordle using the terminal
     */
    public void printGameState(int v, String g) {
        if (v == 0) {
            System.out.println("Game is Over. The word was " + word);
        }
        if (v == 2) {
            System.out.println("Invalid Word. Try Again! Attempt #" + attempt);
        }
        if (v == 3) {
            System.out.println("You won. The word was " + word);
        }
        if (v == 1) {
            String[] temp = g.split("");
            String m = "";
            for (int i = 0; i < 5; i++) {
                if (board[i] == 9) {
                    m = m + "\u001B[32m" + temp[i] + "\u001B[0m";
                }
                if (board[i] == 8) {
                    m = m + temp[i];
                }
                if (board[i] == 7) {
                    m = m + "\u001B[33m" + temp[i] + "\u001B[0m";
                }
            }
            System.out.println("Attempt #" + attempt + " guess: " + m);
        }

    }

    public int getCell(int c) {
        return board[c];
    }

    public int[] getBoard() {
        int[] temp = Arrays.copyOf(board, 5);
        return temp;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    /**
     * This main method illustrates how the model is completely independent of
     * the view and controller. We can play the game from start to finish
     * without ever creating a Java Swing object.
     *
     * This is modularity in action, and modularity is the bedrock of the
     * Model-View-Controller design framework.
     *
     * Run this file to see the output of this method in your console.
     */
    public static void main(String[] args) {
        Wordle t = new Wordle();
        t.setWord("shock");

        int x = t.playTurn("force");
        System.out.println(x);
        t.printGameState(x, "force");

        int y = t.playTurn("arise");
        System.out.println(y);
        t.printGameState(y, "arise");

        int v = t.playTurn("gjihi");
        System.out.println(v);
        t.printGameState(v, "gjihi");

        int w = t.playTurn("magic");
        System.out.println(w);
        t.printGameState(w, "magic");

        int z = t.playTurn("white");
        System.out.println(z);
        t.printGameState(z, "white");

        int s = t.playTurn(t.getWord());
        System.out.println(s);
        t.printGameState(s, t.getWord());

    }
}
