package Game;

public class Board {

    private int numRowsOnBoard;
    private int numColumnsOnBoard;
    private Square[][] chessBoard;

    /**
     * Helper function to put the squares on the chess board.
     * Each square is initialized to be unoccupied.
     */
    private void setUpChessBoard() {
        for(int boardRow = 0; boardRow < numRowsOnBoard; boardRow++) {
            for(int boardColumn = 0; boardColumn < numColumnsOnBoard; boardColumn++) {
                chessBoard[boardRow][boardColumn] = new Square(boardRow, boardColumn);
            }
        }
    }

    /**
     * Simple constructor that sets the board size and initializes the board.
     *
     * @param numRowsOnBoard the number of rows the board should have
     * @param numColumnsOnBoard the number of columns the board should have
     */
    public Board(int numRowsOnBoard, int numColumnsOnBoard) {
        this.numRowsOnBoard = numRowsOnBoard;
        this.numColumnsOnBoard = numColumnsOnBoard;

        this.chessBoard = new Square[numRowsOnBoard][numColumnsOnBoard];

        setUpChessBoard();
    }

    /**
     * Simple getter method for the chessboard.
     * @return the 2D chessboard array
     */
    public Square[][] getChessBoard() {
        return chessBoard;
    }

    /**
     * Verifies if a square is within the bounds of the board.
     *
     * @param row the row of the location that needs to be verified
     * @param column the column of the location that needs to be verified
     * @return whether or not the specified location is in the board
     */
    public boolean isOnBoard(int row, int column) {
        int squareRow = row;
        int squareColumn = column;

        if (squareRow < 0 || squareRow >= numRowsOnBoard ||
                squareColumn < 0 || squareColumn >= numColumnsOnBoard) {
            return false;
        }
        else {
            return true;
        }
    }
}
