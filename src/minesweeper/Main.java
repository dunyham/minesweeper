package minesweeper;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Minesweeper game.
 * @author Dunya Hamidi
 * Left-click to reveal a cell.
 * Right-click to plant/remove a flag for marking a suspected mine.
 * You win if all the cells not containing mines are revealed.
 * You lose if you reveal a cell containing a mine.
 */
public class Main extends JFrame {
    private static final long serialVersionUID = 1L;

    // private variables
    Board board = new Board();
    JButton newGameButton = new JButton("New Game");

    // Constructor to set up UI and game components
    public Main() {
        Container cp = this.getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(board, BorderLayout.CENTER);

        // TODO Add btnNewGame to the south to restart the game
        // ......

        board.newGame();

        pack(); // To pack the UI components
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // handle window-close button
        setTitle("Minesweeper");
        setVisible(true);
    }

    // Main method to start the game
    public static void main (String[] args) {
        // TODO Run the constructor
        // ......
    }
}

