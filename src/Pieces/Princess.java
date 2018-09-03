package Pieces;

import Game.Square;

public class Princess extends ChessPiece {

    public Princess(char color, Square locationOnBoard) {
        super(color, locationOnBoard);
        type = "princess";
    }

    /**
     * Determines whether the move to the desired location is legal for Princesses.
     * Combines logic for Knight, Rook, and Bishop moves.
     *
     * @param desiredLocationToMove the desired place the piece would be moved
     * @return a boolean stating whether or not the move is legal
     */
    public boolean isMoveLegal(Square desiredLocationToMove) {
        Square currentLocationOnBoard = getLocationOnBoard();
        boolean isFileOrRankOrDiagonal = moveChecker.isRank(currentLocationOnBoard, desiredLocationToMove) ||
                moveChecker.isFile(currentLocationOnBoard, desiredLocationToMove) ||
                moveChecker.isDiagonal(currentLocationOnBoard, desiredLocationToMove);
        boolean isKnightMove = moveChecker.isKnightMove(currentLocationOnBoard, desiredLocationToMove);
        return isFileOrRankOrDiagonal || isKnightMove;
    }
}
