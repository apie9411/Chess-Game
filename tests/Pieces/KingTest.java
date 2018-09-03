package Pieces;

import Game.Square;
import org.junit.Test;

import static org.junit.Assert.*;

public class KingTest {

    private Square initialKingPosition = new Square(1,6);
    private King testKing = new King('b', initialKingPosition);

    @Test
    public void isMoveLegal() {
        //moves that only take one step
        Square moveUp = new Square(2,6);
        Square moveDown = new Square(0, 6);
        Square moveRight = new Square(1, 7);
        Square moveLeft = new Square(1, 5);
        Square diagonalRightUp = new Square(2,7);
        Square diagonalLeftDown = new Square(0, 5);
        Square diagonalLeftUp = new Square(2,5);
        Square diagonalRightDown = new Square(0,7);

        assertTrue(testKing.isMoveLegal(moveDown));
        assertTrue(testKing.isMoveLegal(moveUp));
        assertTrue(testKing.isMoveLegal(moveRight));
        assertTrue(testKing.isMoveLegal(moveLeft));
        assertTrue(testKing.isMoveLegal(diagonalLeftDown));
        assertTrue(testKing.isMoveLegal(diagonalLeftUp));
        assertTrue(testKing.isMoveLegal(diagonalRightDown));
        assertTrue(testKing.isMoveLegal(diagonalRightUp));

        //do some of these moves with two steps
        Square moveUpTwoSteps = new Square(3,6);
        Square moveLeftTwoSteps = new Square(1, 4);
        Square diagonalLeftUpTwoSteps = new Square(3, 4);

        assertFalse(testKing.isMoveLegal(moveLeftTwoSteps));
        assertFalse(testKing.isMoveLegal(moveUpTwoSteps));
        assertFalse(testKing.isMoveLegal(diagonalLeftUpTwoSteps));
    }
}