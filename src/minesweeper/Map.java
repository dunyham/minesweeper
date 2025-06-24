package minesweeper;

import static minesweeper.Constants.ROWS;
import static minesweeper.Constants.COLUMNS;

/** This class contains the locations of the mines. */
public class Map {
    int numMines;
    boolean[][] isMined = new boolean[ROWS][COLUMNS];

    public Map() {
        super();
    }

    public void newMap (int numMines) {
        this.numMines = numMines;
        // Hardcoded (temporarily)
        isMined[0][0] = true;
        isMined[5][2] = true;
        isMined[9][5] = true;
        isMined[6][7] = true;
        isMined[8][2] = true;
        isMined[2][4] = true;
        isMined[5][7] = true;
        isMined[7][7] = true;
        isMined[3][6] = true;
        isMined[4][8] = true;
    }
}
