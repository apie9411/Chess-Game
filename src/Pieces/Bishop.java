package Pieces;

import Game.Square;

public class Bishop extends ChessPiece {

    public Bishop(char color, Square locationOnBoard) {
        super(color, locationOnBoard);
        type = "bishop";
    }

    /**
     * Determines whether the move to the desired location is legal for Bishops.
     *
     * @param desiredLocationToMove the desired place the piece would be moved
     * @return a boolean stating whether or not the move is legal
     */
    public boolean isMoveLegal(Square desiredLocationToMove) {
        Square currentLocationOnBoard = getLocationOnBoard();
        return moveChecker.isDiagonal(currentLocationOnBoard, desiredLocationToMove);
    }

}
