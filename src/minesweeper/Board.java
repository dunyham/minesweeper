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

        // 

        //

        // Set size of content-pane and pack all components under container. 
        super.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
    }

    // Initialize the game board with a new game.
    public void newGame() {
        Map mineMap = new Map();
        mineMap.newMap(numMines);

        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                cells[row][column].newGame(mineMap.isMined[row][column]);
            }
        }
    }

    // Return the number of mines in the neighbouring cells.
    private int getSurroundingMines(int srcRow, int srcCol) {
        int numMines = 0;
        for (int row = srcRow - 1; row <= srcRow + 1; row++) {
            for (int col = srcCol - 1; col <= srcCol + 1; col++) {
               // Need to ensure valid row and column numbers too
               if (row >= 0 && row < ROWS && col >= 0 && col < COLUMNS) {
                  if (cells[row][col].isMined) numMines++;
               }
            }
        }
        return numMines;  
    }

    //
    private void revealCell(int srcRow, int srcCol) {
        int numMines = getSurroundingMines(srcRow, srcCol);
        cells[srcRow][srcCol].setText(numMines + "");
        cells[srcRow][srcCol].isRevealed = true;
        cells[srcRow][srcCol].paint();
        if (numMines == 0) {
            for (int row = srcRow - 1; row <= srcRow + 1; row++) {
                for (int col = srcCol - 1; col <= srcCol + 1; col++) {
                    // Need to ensure valid row and column numbers too
                    if (row >= 0 && row < ROWS && col >= 0 && col < COLUMNS) {
                        if (!cells[row][col].isRevealed) revealCell(row, col);
                    }
                }
            }
        }
    }

    // Return true if the player has won

    // Define a Listener Inner class
}
