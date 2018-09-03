package Pieces;

import Game.Square;

public class Queen extends ChessPiece {

    public Queen(char color, Square locationOnBoard) {
        super(color, locationOnBoard);
        type = "queen";
    }

    /**
     * Determines whether the move to the desired location is legal for Queens.
     * Combines logic for Rook and Bishop moves.
     *
     * @param desiredLocationToMove the desired place the piece would be moved
     * @return a boolean stating whether or not the move is legal
     */
    public boolean isMoveLegal(Square desiredLocationToMove) {
        Square currentLocationOnBoard = getLocationOnBoard();
        boolean isFileOrRankOrDiagonal = moveChecker.isRank(currentLocationOnBoard, desiredLocationToMove) ||
                moveChecker.isFile(currentLocationOnBoard, desiredLocationToMove) ||
                moveChecker.isDiagonal(currentLocationOnBoard, desiredLocationToMove);
        return isFileOrRankOrDiagonal;
    }

}
