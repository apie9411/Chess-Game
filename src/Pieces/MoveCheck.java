package Pieces;

import Game.Square;

public class MoveCheck {

    /**
     * Used in all the move check methods to see what kind of move is being made.
     *
     * @param current current position to be compared
     * @param desired desired position to be compared with current position
     * @return the absolute value of the difference between the current and desired position
     */
    private int getAbsoluteDifference(int current, int desired) {

        return Math.abs(current - desired);
    }

    /**
     * Checks whether or not the desired move would be a move along rank.
     *
     * @param currentBoardLocation the current location of a chess piece on the board
     * @param desiredBoardLocation the desired location the chess piece would be moved to
     * @return whether or not this is a move along rank
     */
    public boolean isRank(Square currentBoardLocation, Square desiredBoardLocation) {

        boolean isMoving = !currentBoardLocation.isSameSquare(desiredBoardLocation);
        int differenceInRows = getAbsoluteDifference(currentBoardLocation.getRowOnBoard(),
                desiredBoardLocation.getRowOnBoard());
        boolean areRowsEqual = differenceInRows == 0;

        return areRowsEqual && isMoving;
    }

    /**
     * Checks whether or not the desired move would be a move along file.
     *
     * @param currentBoardLocation the current location of a chess piece on the board
     * @param desiredBoardLocation the desired location the chess piece would be moved to
     * @return whether or not this is a move along file
     */
    public boolean isFile(Square currentBoardLocation, Square desiredBoardLocation) {

        boolean isMoving = !currentBoardLocation.isSameSquare(desiredBoardLocation);
        int differenceInColumns = getAbsoluteDifference(currentBoardLocation.getColumnOnBoard(),
                desiredBoardLocation.getColumnOnBoard());
        boolean areColumnsEqual = differenceInColumns == 0;

        return areColumnsEqual && isMoving;
    }

    /**
     * Checks whether or not the desired move would be a diagonal move.
     *
     * @param currentBoardLocation the current location of a chess piece on the board
     * @param desiredBoardLocation the desired location the chess piece would be moved to
     * @return whether or not this is a diagonal move
     */
    public boolean isDiagonal(Square currentBoardLocation, Square desiredBoardLocation) {

        boolean isMoving = !currentBoardLocation.isSameSquare(desiredBoardLocation);
        int differenceInRows = getAbsoluteDifference(currentBoardLocation.getRowOnBoard(),
                desiredBoardLocation.getRowOnBoard());
        int differenceinColumns = getAbsoluteDifference(currentBoardLocation.getColumnOnBoard(),
                desiredBoardLocation.getColumnOnBoard());
        boolean isDiagonalMovement = differenceInRows == differenceinColumns;

        return isDiagonalMovement && isMoving;
    }

    /**
     * Checks whether or not the desired move would form an 'L' shape.
     * Two spaces in one direction and one space perpendicular to that.
     *
     * @param currentBoardLocation the current location of a chess piece on the board
     * @param desiredBoardLocation the desired location the chess piece would be moved to
     * @return whether or not this is a valid knight move
     */
    public boolean isKnightMove(Square currentBoardLocation, Square desiredBoardLocation) {
        int currentRow = currentBoardLocation.getRowOnBoard();
        int currentColumn = currentBoardLocation.getColumnOnBoard();
        int desiredRow = desiredBoardLocation.getRowOnBoard();
        int desiredColumn = desiredBoardLocation.getColumnOnBoard();

        int absoluteRowDifference = Math.abs(currentRow - desiredRow);
        int absoluteColumnDIfference = Math.abs(currentColumn - desiredColumn);

        boolean notOtherMoves = !isFile(currentBoardLocation, desiredBoardLocation) &&
                !isDiagonal(currentBoardLocation, desiredBoardLocation) &&
                !isRank(currentBoardLocation, desiredBoardLocation);
        return notOtherMoves && ((absoluteRowDifference == 2 && absoluteColumnDIfference == 1) ||
                (absoluteColumnDIfference == 2 && absoluteRowDifference == 1));
    }

    /**
     * Checks whether or not the move is a valid king move.
     *
     * @param currentBoardLocation the current location of a chess piece on the board
     * @param desiredBoardLocation the desired location the chess piece would be moved to
     * @return whether or not this is a valid king move
     */
    public boolean isKingMove(Square currentBoardLocation, Square desiredBoardLocation) {

        boolean isMoving = !currentBoardLocation.isSameSquare(desiredBoardLocation);
        int differenceInRows = getAbsoluteDifference(currentBoardLocation.getRowOnBoard(),
                desiredBoardLocation.getRowOnBoard());
        int differenceInColumns = getAbsoluteDifference(currentBoardLocation.getColumnOnBoard(),
                desiredBoardLocation.getColumnOnBoard());

        return isMoving && (differenceInRows <= 1) && (differenceInColumns <= 1);
    }
}
