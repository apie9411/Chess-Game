package Pieces;

import Game.Square;

public class Rook extends ChessPiece {

    public Rook(char color, Square locationOnBoard) {
        super(color, locationOnBoard);
        type = "rook";
    }

    /**
     * Determines whether the move to the desired location is legal for Rooks
     *
     * @param desiredLocationToMove the desired place the piece would be moved
     * @return a boolean stating whether or not the move is legal
     */
    public boolean isMoveLegal(Square desiredLocationToMove) {
        Square currentLocationOnBoard = getLocationOnBoard();
        boolean isFileOrRank = moveChecker.isRank(currentLocationOnBoard, desiredLocationToMove) ||
                moveChecker.isFile(currentLocationOnBoard, desiredLocationToMove);
        return isFileOrRank;
    }

}
