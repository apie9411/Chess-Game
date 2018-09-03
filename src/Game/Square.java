package Game;

import Pieces.*;

public class Square {

    private int rowOnBoard;
    private int columnOnBoard;
    private char colorOfOccupant = 'u'; //'b' for black, 'w' for white, 'u' for unoccupied
    private ChessPiece occupantPiece = null; //squares are unoccupied by default


    /**
     * Basic constructor that sets member values.
     *
     * @param rowOnBoard the squares permanent row position on the board
     * @param columnOnBoard the square's permanent column position on the board
     */
    public Square(int rowOnBoard, int columnOnBoard) {
        this.rowOnBoard = rowOnBoard;
        this.columnOnBoard = columnOnBoard;
    }

    /**
     * Simple getter method for square row position.
     *
     * @return the square's row position
     */
    public int getRowOnBoard() {
        return rowOnBoard;
    }

    /**
     * Simple getter method for square column position.
     *
     * @return the square's column position
     */
    public int getColumnOnBoard() {
        return columnOnBoard;
    }

    /**
     * Getter method for color of chess piece occupying square.
     *
     * @return 'b', 'w', or 'u' for color of square occupant, 'u' means unoccupied
     */
    public char getColorOfOccupant() {
        return colorOfOccupant;
    }

    /**
     * Setter method for color of chess piece occupying square.
     *
     * @param colorOfOccupant must be 'b', 'w', or 'u'
     */
    public void setColorOfOccupant(char colorOfOccupant) {
        this.colorOfOccupant = colorOfOccupant;
    }

    /**
     * Gets the chess piece (if any) occupying the square.
     *
     * @return the chess piece occupying the square
     */
    public ChessPiece getOccupantPiece() {
        return occupantPiece;
    }

    /**
     * Sets the chess piece occupying the square.
     *
     * @param occupantPiece the chess piece that will occupy the square
     */
    public void setOccupantPiece(ChessPiece occupantPiece) {
        this.occupantPiece = occupantPiece;
    }

    /**
     * Method to tell whether two squares are in different locations. Used to verify a chess piece is
     * moving in chess piece classes.
     *
     * @param other The square being compared to the current square
     * @return boolean that says whether or not the squares are in the same location
     */
    public boolean isSameSquare(Square other) {
        boolean isSameRow = rowOnBoard == other.getRowOnBoard();
        boolean isSameColumn = columnOnBoard == other.getColumnOnBoard();
        return isSameRow && isSameColumn;
    }
}
