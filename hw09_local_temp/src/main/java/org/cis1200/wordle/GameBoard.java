package org.cis1200.wordle;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.awt.Color.LIGHT_GRAY;

public class GameBoard extends JPanel {

    private Wordle ttt; // model for the game
    private JLabel status; // current status text

    // Game constants
    public static final int BOARD_WIDTH = 700;
    public static final int BOARD_HEIGHT = 700;

    public static final Border BORDER_COLOR = BorderFactory.createLineBorder(LIGHT_GRAY);
    private String guess;
    private JLabel[][] ar;
    private String[] guessAr;
    private ArrayList<String> allGuesses = new ArrayList<String>();

    private boolean done;

    /**
     * Initializes the game board.
     * Creates a 2D array of Jlabels
     */
    public GameBoard(JLabel statusInit) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.BLACK);
        setLayout(new GridLayout(6, 5));
        ar = new JLabel[6][5];
        for (int i = 0; i < 6; i++) {
            for (int y = 0; y < 5; y++) {
                ar[i][y] = new JLabel();
                ar[i][y].setOpaque(true);
                ar[i][y].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                add(ar[i][y]);
            }
        }

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        ttt = new Wordle(); // initializes model for the game
        status = statusInit; // initializes the status JLabel

    }

    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() {
        ttt.reset();
        done = false;
        System.out.print(ttt.getWord());
        status.setText("Guess a 5 Letter Word in English. 6 Guesses Remaining");
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 5; c++) {
                setTile("", r, c, LIGHT_GRAY);
            }
        }

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }

    /**
     * sets the Users guess to the input value
     * changes the gui and internal wordle values
     * according to the input
     */
    public void setGuess(String m) {
        this.guess = m;
        int x = ttt.playTurn(m);
        updateGui(x, m);
        updateStatus(x);
    }

    /**
     * sets each tile or value of the grid
     */
    public void setTile(String letter, int attempt, int column, Color color) {
        ar[attempt][column].setForeground(Color.BLACK);
        ar[attempt][column].setHorizontalAlignment(SwingConstants.CENTER);
        ar[attempt][column].setVerticalAlignment(SwingConstants.CENTER);
        ar[attempt][column].setFont(new Font("Verdana", Font.BOLD, 48));
        ar[attempt][column].setText(letter.toUpperCase());
        ar[attempt][column].setBackground(color);
    }

    /**
     * loads game from a file
     */
    public void loadGame() {
        reset();
        List<String> info = new ArrayList<>();
        try {
            info = Files.readAllLines(Path.of("src/main/java/org/cis1200/wordle/gameInfo.txt"));
            if (((info.get(0) != null)) && (!(info.get(0).isEmpty()))) {
                ttt.setWord((info.get(0)).trim().toLowerCase());
            }
            if (((info.get(1) != null)) && (!(info.get(1).isEmpty()))) {
                String gu = info.get(1);
                String gu2 = gu.substring(1, gu.length() - 1);
                if ((gu2 != null) && (!(gu2.isEmpty()))) {
                    String[] temp = gu2.split(", ");
                    allGuesses = new ArrayList<String>();
                    Collections.addAll(allGuesses, temp);
                }
                int z = Integer.parseInt(info.get(2));
                for (int i = 0; i < z; i++) {
                    setGuess(allGuesses.get(i));
                }

            }

        } catch (Exception e) {
            status.setText("Cannot Load Game.");
        }
    }

    /**
     * saves game into a file
     */
    public void saveGame() {
        try {
            FileWriter f;
            f = new FileWriter("src/main/java/org/cis1200/wordle/gameInfo.txt");
            String message = ttt.getWord() + "\n" + allGuesses + "\n" + ttt.getAttempt();
            f.write(message);
            f.flush();
            f.close();
            status.setText("Game is saved.");
        } catch (IOException e) {
            status.setText("Cannot save game");
        }
    }

    /**
     * Updates the JLabel to reflect the current state of the game.
     */
    private void updateStatus(int mode) {
        if (ttt.getWinner()) {
            status.setText("You won. The word was " + ttt.getWord());
        } else {
            if (mode == 0) {
                status.setText("Game Over. You Lost. The word was: " + ttt.getWord());
            }
            if (mode == 2) {
                if ((ttt.getGameOver()) || (ttt.getAttempt() >= 6)) {
                    status.setText("Game Over. You Lost. The word was: " + ttt.getWord());
                } else {
                    status.setText(
                            "Invalid Word. Try Again! You have used " + ttt.getAttempt()
                                    + " attempts."
                    );
                }
            }
            if (mode == 3) {
                status.setText("You won. The word was " + ttt.getWord());
            }
            if (mode == 1) {
                if ((ttt.getAttempt() == 6) || (ttt.getAttempt() > 6)) {
                    status.setText("Game Over. You Lost. The word was: " + ttt.getWord());
                } else {
                    status.setText("You have used " + ttt.getAttempt() + " attempts");
                }
            }
        }

    }

    /**
     * updates the gameboard itself when a new valid guess
     * is added
     */
    private void updateGui(int status, String g) {
        if ((status == 3) && (!(done))) {
            guessAr = g.split("");
            int r = ttt.getAttempt() - 1;
            for (int i = 0; i < 5; i++) {
                setTile(guessAr[i], r, i, Color.GREEN);
            }
            allGuesses.add(g);
            done = true;
        }
        if (status == 1) {
            guessAr = g.split("");
            int r = ttt.getAttempt() - 1;
            for (int i = 0; i < 5; i++) {
                int value = ttt.getCell(i);
                if (value == 9) {
                    setTile(guessAr[i], r, i, Color.GREEN);
                }
                if (value == 8) {
                    setTile(guessAr[i], r, i, Color.DARK_GRAY);
                }
                if (value == 7) {
                    setTile(guessAr[i], r, i, Color.YELLOW);
                }
            }
            allGuesses.add(g);
        }
    }

    /**
     * Draws the game board.
     *
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}
