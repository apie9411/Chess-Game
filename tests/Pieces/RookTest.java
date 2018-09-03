package Pieces;

import Game.Square;
import org.junit.Test;

import static org.junit.Assert.*;

public class RookTest {

    private Square initialRookPosition = new Square(3, 3);
    private Square anotherInitialRookPosition = new Square(7,0);
    private Rook testRook = new Rook('b', initialRookPosition);
    private Rook anotherTestRook = new Rook('w', anotherInitialRookPosition);

    @Test
    public void isMoveLegal() {
        Square fileMove1 = new Square(1,3);
        Square fileMove2 = new Square(4,3);
        Square fileMove3 = new Square(7,3);
        Square rankMove1 = new Square(3,0);
        Square rankMove2 = new Square(3, 5);

        assertTrue(testRook.isMoveLegal(fileMove1));
        assertTrue(testRook.isMoveLegal(fileMove2));
        assertTrue(testRook.isMoveLegal(fileMove3));
        assertTrue(anotherTestRook.isMoveLegal(rankMove1));
        assertTrue(testRook.isMoveLegal(rankMove1));
        assertTrue(testRook.isMoveLegal(rankMove2));

        //now to test some bad moves
        Square diagonalMove = new Square(1, 5);
        Square LMove = new Square(4, 5);
        Square LMove2 = new Square(5, 4);
        Square randomMove = new Square(0, 1);

        assertFalse(testRook.isMoveLegal(diagonalMove));
        assertFalse(testRook.isMoveLegal(LMove));
        assertFalse(testRook.isMoveLegal(LMove2));
        assertFalse(testRook.isMoveLegal(randomMove));
        assertFalse(anotherTestRook.isMoveLegal(diagonalMove));
        assertFalse(anotherTestRook.isMoveLegal(LMove));
        assertFalse(anotherTestRook.isMoveLegal(LMove2));
        assertFalse(anotherTestRook.isMoveLegal(randomMove));
    }
}