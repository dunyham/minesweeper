package minesweeper;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static minesweeper.Constants.ROWS;
import static minesweeper.Constants.COLUMNS;

/**
 * This class contains a 2D array of cells and a Map.
 */
public class Board extends JPanel {
    private static final long serialVersionUID = 1L;

    // Named constants for UI sizes, including cell width and height in pixels, and the width and height of the game board.
    public static final int CELL_SIZE = 60; 
    public static final int BOARD_WIDTH = CELL_SIZE * COLUMNS;
    public static final int BOARD_HEIGHT = CELL_SIZE * ROWS;

    // Define game properties
    /** Create game board made up of cells */
    Cell cells[][] = new Cell[ROWS][COLUMNS];
    /** Allocate number of mines */
    int numMines = 10;

    /** Constructor */
    public Board() {
        // Create JPanel
        super.setLayout(new GridLayout(ROWS, COLUMNS, 2, 2));

        // Allocate the 2D array of Cell and add to content-pane.
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
               cells[row][column] = new Cell(row, column);
               super.add(cells[row][column]);
            }
        }

        // Allocate a common listener as the MouseEvent listener for Cells
        CellMouseListener listener = new CellMouseListener();

        // Adds this common listener to every Cell
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                cells[row][column].addMouseListener(listener);
            }
        }

        // Set size of content-pane and pack all components under container. 
        super.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
    }

    // Initialize the game board with a new game.
    public void newGame() {
        Map mineMap = new Map();
        mineMap.newMap(numMines);

        // Reset all cells, mines, and flags.
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                // Initialize each cell with/without a mine.
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

    // Reveal the cell at (srcRow, srcCol)
    // If this cell does not have a mine, reveal the 8 neighbouring cells recursively
    private void revealCell(int srcRow, int srcCol) {
        int numMines = getSurroundingMines(srcRow, srcCol);
        cells[srcRow][srcCol].setText(numMines + "");
        cells[srcRow][srcCol].isRevealed = true;
        cells[srcRow][srcCol].paint();
        if (numMines == 0) {
            // Recursively reveal neighbouring cells
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

    // Return true if the player has won.
    public boolean hasWon() {
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                if (!cells[row][column].isRevealed && !cells[row][column].isMined) {
                    return false;
                }
            }
        }
        return true;
    }

    // Listener Inner class
    private class CellMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {   // Retrieve source object
            Cell sourceCell = (Cell)e.getSource();
            // Debug
            System.out.println("You clicked on " + sourceCell.row + ", " + sourceCell.col);

            // Left and right-click functionality; left-click to reveal a cell, right-click to plant/remove a flag.
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (sourceCell.isMined) {
                    System.out.println("Game Over");
                    sourceCell.setText("*");
                } else {
                    revealCell (sourceCell.row, sourceCell.col);
                }
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                if (sourceCell.isFlagged) {
                    sourceCell.isFlagged = false;
                    sourceCell.setText("");
                } else {
                    sourceCell.isFlagged = true;
                    sourceCell.setText("F");
                }
            }
        }
    }
}

