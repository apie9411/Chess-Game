package Pieces;

import Game.Square;

public class Knight extends ChessPiece {

    public Knight(char color, Square locationOnBoard) {
        super(color, locationOnBoard);
        type = "knight";
    }

    /**
     * Determines whether the move to the desired location is legal for Knights.
     * Uses logic for Rook, Bishop, and Queen moves to cancel out those options.
     *
     * @param desiredLocationToMove the desired place the piece would be moved
     * @return a boolean stating whether or not the move is legal
     */
    public boolean isMoveLegal(Square desiredLocationToMove) {
        Square currentLocationOnBoard = getLocationOnBoard();

        return moveChecker.isKnightMove(currentLocationOnBoard, desiredLocationToMove);
    }

}
