package minesweeper;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * 
 */
public class Main extends JFrame {
    private static final long serialVersionUID = 1L;

    Board board = new Board();
    JButton newGameButton = new JButton("New Game");

    // Constructor
    public Main() {
        Container cp = this.getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(board, BorderLayout.CENTER);

        //

        board.newGame();

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Minesweeper");
        setVisible(true);
    }

    public static void main (String[] args) {
        //
    }
}

