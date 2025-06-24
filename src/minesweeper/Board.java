package minesweeper;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static minesweeper.Constants.ROWS;
import static minesweeper.Constants.COLUMNS;

public class Board extends JPanel {
    private static final long serialVersionUID = 1L;

    // Named constants for UI sizes, including cell width and height in pixels, and the width and height of the game board.
    public static final int CELL_SIZE = 60; 
    public static final int BOARD_WIDTH = CELL_SIZE * COLUMNS;
    public static final int BOARD_HEIGHT = CELL_SIZE * ROWS;

    /** Create game board made up of cells */
    Cell cells[][] = new Cell[ROWS][COLUMNS];
    /** Allocate number of mines */
    int numMines = 10;

    /** Constructor */
    public Board() {
        // Create JPanel
        super.setLayout(new GridLayout(ROWS, COLUMNS, 2, 2));

        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
               cells[row][column] = new Cell(row, column);
               super.add(cells[row][column]);
            }
        }

        
    }
}
