package Game;

import Pieces.*;

import java.util.ArrayList;

public class Player {

    private char color;
    public ArrayList<ChessPiece> availablePieces;
    public ArrayList<ChessPiece> capturedPieces;

    /**
     * Simple constructor that calls helper method to set up chess pieces
     * and their location on the board.
     *
     * @param color the color the player is in charge of, either 'w' or 'b'
     */
    public Player(char color) {
        this.color = color;
        availablePieces = new ArrayList<ChessPiece>();
        capturedPieces = new ArrayList<ChessPiece>();
    }

    /**
     * Simple getter to return player color, either 'w' or 'b'.
     *
     * @return the player's color
     */
    public char getColor() {
        return color;
    }

    /**
     * This method should only be called if appropriate move checks and validations
     * have been done.
     *
     * @param chessPieceToMove the chess piece that is being moved to a new location
     * @param desiredLocation the location to move the chess piece
     */
    public void moveChessPiece(ChessPiece chessPieceToMove, Square desiredLocation) {
        //check if chess piece is a part of available pieces for this player
        if(availablePieces.contains(chessPieceToMove)) {
            chessPieceToMove.setLocationOnBoard(desiredLocation);
            desiredLocation.setColorOfOccupant(chessPieceToMove.getColor());
            desiredLocation.setOccupantPiece(chessPieceToMove);

            //always make sure a pawn only has one first move
            if(chessPieceToMove.type == "pawn") {
                Pawn pawnMoved = (Pawn) chessPieceToMove;
                pawnMoved.firstMoveOver();
            }
        }
        else {
            return; //do nothing, player is not in charge of chess piece
        }
    }

    /**
     * Called when an opposing chess piece is captured.
     *
     * @param capturedPiece the chess piece being captured
     */
    public void captureChessPiece(ChessPiece capturedPiece) {
        if(capturedPiece.getColor() != color) {
            capturedPieces.add(capturedPiece);
            capturedPiece.capturePiece();
            capturedPiece.setLocationOnBoard(null);
        }
    }
}
