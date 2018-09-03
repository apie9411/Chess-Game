package Pieces;

import Game.Square;

public abstract class ChessPiece {

    private char color;
    private boolean captured = false; //pieces are uncaptured by default
    private Square locationOnBoard;
    protected MoveCheck moveChecker = new MoveCheck(); //used in implementations of isMoveLegal() to check move types
    public String type;

    /**
     * Constructor for a generic chess piece. Sets values of member variables.
     *
     * @param color 'b' for black or 'w' for white
     * @param locationOnBoard where on the board the piece is
     */
    public ChessPiece(char color, Square locationOnBoard) {
        this.color = color;
        this.locationOnBoard = locationOnBoard;
        locationOnBoard.setOccupantPiece(this);
        locationOnBoard.setColorOfOccupant(color);
    }

    /**
     *
     * @return the color of the chess piece, 'b' for black and 'w' for white
     */
    public char getColor() {
        return color;
    }

    /**
     * Used to determine whether a chess piece is captured or not.
     *
     * @return a boolean stating if the piece is captured
     */
    public boolean isCaptured() {
        return captured;
    }

    /**
     * To be called when a piece gets captured.
     */
    public void capturePiece() {
        captured = true;
    }

    /**
     * Simple getter method for chess piece locationOnBoard.
     *
     * @return the locationOnBoard of the chess piece
     */
    public Square getLocationOnBoard() {
        return locationOnBoard;
    }

    /**
     * Simple setter method for chess piece locationOnBoard.
     *
     * @param locationOnBoard the new locationOnBoard of the chess piece
     */
    public void setLocationOnBoard(Square locationOnBoard) {
        this.locationOnBoard = locationOnBoard;
    }

    /**
     * Method kept abstract since each piece has different criteria for what constitutes a legal move.
     *
     * @param desiredLocationToMove the desired place the piece would be moved
     * @return whether or not the move is legal for a particular chess piece
     */
    public abstract boolean isMoveLegal(Square desiredLocationToMove);
}
