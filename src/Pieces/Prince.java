package Pieces;

import Game.Square;

public class Prince extends ChessPiece {

    public Prince(char color, Square locationOnBoard) {
        super(color, locationOnBoard);
        type = "prince";
    }

    /**
     * Determines whether the move to the desired location is legal for Princes.
     * Combines logic for Knight and King moves.
     *
     * @param desiredLocationToMove the desired place the piece would be moved
     * @return a boolean stating whether or not the move is legal
     */
    public boolean isMoveLegal(Square desiredLocationToMove) {
        Square currentLocationOnBoard = getLocationOnBoard();
        boolean isRankOrKingMove = moveChecker.isRank(currentLocationOnBoard, desiredLocationToMove) ||
                moveChecker.isKingMove(currentLocationOnBoard, desiredLocationToMove);
        return isRankOrKingMove;
    }
}
