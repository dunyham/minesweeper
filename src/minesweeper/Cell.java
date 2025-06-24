package minesweeper;

import java.awt.Color;
import java.awt.Font;
import java.security.PublicKey;
import javax.swing.JButton;

/**
 *  This class models the cells of the game's map by adding row/column and state properties
 *  to JButton.
 */

public class Cell extends JButton {
    /** Ensure the serialized form of a class is compatible between different versions;
     *  1L used to prevent serial warning. */
    private static final long serialVersionIUD = 1L;

    /** Define named constants for JButton's colors and fonts based on cell state. */
    public static final Color BG_NOT_REVEALED = Color.GREEN;
    public static final Color FG_NOT_REVEALED = Color.RED;
    public static final Color BG_REVEALED = Color.DARK_GRAY;
    public static final Color FG_REVEALED = Color.YELLOW;
    public static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 20);

    // Define properties
    /** The row and column number of the cell. */
    int row, col;

    /** Has it been revealed? */
    boolean isRevealed;

    /** Is it a mine? */
    boolean isMined;

    /** Has it been flagged? */
    boolean isFlagged;

    /** Constructor */
    public Cell (int row, int col) {
        super();
        this.row = row;
        this.col = col;
        // Set JButton's default display properties
        super.setFont(FONT_NUMBERS);
    }

    /** Reset this cell, ready for a new game */
    public void newGame (boolean isMined) {
        this.isRevealed = false;
        this.isFlagged = false;
        this.isMined = isMined;
        super.setEnabled(true);
        super.setText("");
        paint();
    }

    /** Paint itself based on status */
    public void paint() {
        super.setForeground(isRevealed ? FG_REVEALED : FG_NOT_REVEALED);
        super.setBackground(isRevealed ? BG_REVEALED : BG_NOT_REVEALED);
    }
}