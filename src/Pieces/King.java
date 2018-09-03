package Pieces;

import Game.Square;

public class King extends ChessPiece {

    public King(char color, Square locationOnBoard) {
        super(color, locationOnBoard);
        type = "king";
    }

    /**
     * Determines whether the move to the desired location is legal for Kings.
     *
     * @param desiredLocationToMove the desired place the piece would be moved
     * @return a boolean stating whether or not the move is legal
     */
    public boolean isMoveLegal(Square desiredLocationToMove) {
        Square currentLocationOnBoard = getLocationOnBoard();
        boolean isKingMove = moveChecker.isKingMove(currentLocationOnBoard, desiredLocationToMove);
        return isKingMove;
    }

}
