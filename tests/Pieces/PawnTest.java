package Pieces;

import org.junit.Test;

import static org.junit.Assert.*;
import Game.*;

public class PawnTest {

    private Square intiialWhitePawnLocation = new Square(1, 3);
    private Square initialBlackPawnLocation = new Square(6, 6);

    //black and white pawns work slightly different with respect to forward movement, so both are tested
    private Pawn testPawnFirstMove = new Pawn('w', intiialWhitePawnLocation);
    private Pawn testPawnNoFirstMove = new Pawn('w', intiialWhitePawnLocation);
    private Pawn testBlackPawn = new Pawn('b', initialBlackPawnLocation);

    @Test
    public void isMoveLegal() {
        testPawnNoFirstMove.firstMoveOver();
        Square twoSquaresForward = new Square(3,3);
        assertTrue(testPawnFirstMove.isMoveLegal(twoSquaresForward));
        assertFalse(testPawnNoFirstMove.isMoveLegal(twoSquaresForward));

        //testing first move for black pawn
        Square twoSquaresForwardBlack = new Square(4, 6);
        assertTrue(testBlackPawn.isMoveLegal(twoSquaresForwardBlack));
        testBlackPawn.firstMoveOver();
        assertFalse(testBlackPawn.isMoveLegal(twoSquaresForwardBlack));

        //testing one step forward for black and white
        Square oneSquareForwardWhite = new Square(2, 3);
        Square oneSquareForwardBlack = new Square(5,6);

        assertTrue(testPawnFirstMove.isMoveLegal(oneSquareForwardWhite));
        assertTrue(testPawnNoFirstMove.isMoveLegal(oneSquareForwardWhite));
        assertTrue(testBlackPawn.isMoveLegal(oneSquareForwardBlack));
        assertFalse(testBlackPawn.isMoveLegal(oneSquareForwardWhite));
        assertFalse(testPawnFirstMove.isMoveLegal(oneSquareForwardBlack));
        assertFalse(testPawnNoFirstMove.isMoveLegal(oneSquareForwardBlack));

        //testing for correct capture movement under correct conditions
        Square diagonalUnoccupiedWhite = new Square(2, 4);
        Square diagonalUnoccupiedBlack = new Square(5, 7);

        Square diagonalOccupiedWhite = new Square(2, 2);
        diagonalOccupiedWhite.setColorOfOccupant('b'); //color set to black for opposing chess piece (capture)
        Square diagonalOccupiedBlack = new Square(5, 5);
        diagonalOccupiedBlack.setColorOfOccupant('w'); //color set to white for opposing chess piece (capture)

        assertFalse(testPawnFirstMove.isMoveLegal(diagonalUnoccupiedWhite));
        assertFalse(testPawnNoFirstMove.isMoveLegal(diagonalUnoccupiedWhite));
        assertFalse(testBlackPawn.isMoveLegal(diagonalUnoccupiedBlack));
        assertTrue(testPawnFirstMove.isMoveLegal(diagonalOccupiedWhite));
        assertTrue(testBlackPawn.isMoveLegal(diagonalOccupiedBlack));

        //do some incorrect moves
        Square rankMoveTooLarge = new Square(1, 7); //for white chess piece
        Square fileMoveTooLarge = new Square(2, 6); //for black chess piece
        assertFalse(testPawnNoFirstMove.isMoveLegal(rankMoveTooLarge));
        assertFalse(testBlackPawn.isMoveLegal(fileMoveTooLarge));
    }
}