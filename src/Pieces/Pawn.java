package Pieces;

import Game.Square;

public class Pawn extends ChessPiece {

    boolean firstMove = true;

    public Pawn(char color, Square locationOnBoard) {
        super(color, locationOnBoard);
        type = "pawn";
    }

    /**
     * Permanently sets first move to false once the pawn's first move has passed.
     */
    public void firstMoveOver() {
        firstMove = false;
    }

    /**
     * Checks if moving to the desired location is legal for the pawn. Considers special cases such as
     * first move and diagonal capture.
     *
     * @param desiredLocationToMove the desired place the piece would be moved
     * @return whether or not the move is legal for a pawn
     */
    public boolean isMoveLegal(Square desiredLocationToMove) {

        Square currentLocationOnBoard = getLocationOnBoard();

        int currentRow = currentLocationOnBoard.getRowOnBoard();
        int currentColumn = currentLocationOnBoard.getColumnOnBoard();
        int desiredRow = desiredLocationToMove.getRowOnBoard();
        int desiredColumn = desiredLocationToMove.getColumnOnBoard();

        int rowDifference = currentRow - desiredRow;
        int absoluteColumnDifference = Math.abs(currentColumn - desiredColumn);

        char desiredSquareOccupied = desiredLocationToMove.getColorOfOccupant();
        boolean isMoving = !currentLocationOnBoard.isSameSquare(desiredLocationToMove);
        char chessPieceColor = getColor();

        if(isMoving) {
            //forward and backward movement changes depending on which side of the board you're on
            //need to account for both cases
            if(chessPieceColor == 'b') {
                if(rowDifference == 1) {
                    if(absoluteColumnDifference == 0) {
                        return true;
                    }
                    //special case for capturing
                    else if(absoluteColumnDifference == 1 && desiredSquareOccupied == 'w') {
                        return true;
                    }
                }
                else if(rowDifference == 2 && absoluteColumnDifference == 0 && firstMove) {
                    return true;
                }
            }
            else {
                if(rowDifference == -1) {
                    if(absoluteColumnDifference == 0) {
                        return true;
                    }
                    //special case for capturing
                    else if(absoluteColumnDifference == 1 && desiredSquareOccupied == 'b') {
                        return true;
                    }
                }
                else if(rowDifference == -2 && absoluteColumnDifference == 0 && firstMove) {
                    return true;
                }
            }
        }
        return false;
    }

}
