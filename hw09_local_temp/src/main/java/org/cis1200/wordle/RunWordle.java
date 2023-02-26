package org.cis1200.wordle;

/*
 * CIS 120 HW09 - TicTacToe Demo
 * (c) University of Pennsylvania
 * Created by Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020.
 */


import javax.swing.*;
import java.awt.*;

/**
 * This class sets up the top-level frame and widgets for the GUI.
 *
 */
public class RunWordle implements Runnable {
    public void run() {

        // Top-level frame in which game components live
        final JFrame frame = new JFrame("Wordle");
        frame.setLocation(300, 300);
        frame.setSize(700, 700);
        frame.setLayout(new BorderLayout());

        // Status panel
        final JPanel status_panel = new JPanel();
        status_panel.setBackground(Color.BLACK);
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel();
        status.setFont(new Font("Verdana", Font.BOLD, 15));
        status.setForeground(Color.LIGHT_GRAY);
        status.setBackground(Color.BLACK);
        status.setText("Setting up...");
        status_panel.add(status);

        // Game board
        final org.cis1200.wordle.GameBoard board = new org.cis1200.wordle.GameBoard(status);
        frame.add(board, BorderLayout.CENTER);

        // Control Panel
        final JPanel control_panel = new JPanel();
        control_panel.setBackground(Color.BLACK);
        control_panel.setLayout(new GridLayout(1, 3));
        frame.add(control_panel, BorderLayout.NORTH);

        // text field for inputs
        final JTextField input = new JTextField();
        control_panel.add(input);
        // when user hits enter, capture value
        input.addActionListener(e -> {
            String i;
            i = input.getText();
            board.setGuess(i);
            input.setText("");
        });
        // when user hits enter button, capture value
        final JButton enter = new JButton("Enter");
        control_panel.add(enter);
        enter.addActionListener(e -> {
            String i;
            i = input.getText();
            board.setGuess(i);
            input.setText("");
        });
        // load game from file button
        final JButton load = new JButton("Load");
        control_panel.add(load);
        load.addActionListener(e -> board.loadGame());
        // save game to file button
        final JButton save = new JButton("Save");
        control_panel.add(save);
        save.addActionListener(e -> board.saveGame());
        // reset game
        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> board.reset());
        control_panel.add(reset);
        // instructions for game button
        final JButton instructions = new JButton("Instructions");
        instructions.addActionListener(e -> {
            String message = "How to Play Wordle:" +
                    "\n " +
                    "\n The game will automatically generate a 5 letter word." +
                    "\n The goal is to guess the word within 6 attempts." +
                    "\n " +
                    "\n 1. Type your guess into the white text field in the top left corner." +
                    "\n If your guess is a word in the game's word bank, it will accept it." +
                    "\n Otherwise, it will tell you its invalid and ask you to try again." +
                    "\n " +
                    "\n 2. If your guess is accepted, the word will show up on the grid." +
                    "\n The letters will change color. If the letter is green, then its" +
                    "\n in the right spot. If its yellow, then the letter is in the word" +
                    "\n but its in a different spot. If its dark gray, then the letter is" +
                    "\n not in the word at all." +
                    "\n " +
                    "\n 3. The status bar at the bottom will tell you if you've won or lost." +
                    "\n It will also tell you how many attempts you've used and if your guess" +
                    "\n is considered invalid because its not in the word bank." +
                    "\n " +
                    "\n Rules:" +
                    "\n 1. You only have 6 attempts. " +
                    "\n 2. You can save your file at any time using the load and save file." +
                    "\n 3. The words will not have repeating letters. For example, the" +
                    "\n apple is not a possible choice." +
                    "\n 4. Reset will remove all your progress and change the word." +
                    "\n 5. It doesn't matter whether you type in lowercase or uppercase." +
                    "\n 6. The letter will be from the English alphabet." +
                    "\n " +
                    "\n Have Fun and Good Luck!";
            JOptionPane.showMessageDialog(frame, message);
        });
        control_panel.add(instructions);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start the game
        board.reset();
    }
}