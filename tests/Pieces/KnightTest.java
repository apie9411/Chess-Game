package Pieces;

import Game.Square;
import org.junit.Test;

import static org.junit.Assert.*;

public class KnightTest {

    private Square initialKnightPosition = new Square(5, 2);
    private Knight sirLancelot = new Knight('w', initialKnightPosition);


    @Test
    public void isMoveLegal() {
        //test some legal L moves
        Square horizontalL = new Square(3, 1);
        Square verticalL = new Square(6, 0);
        Square oneMoreL = new Square(4, 4);

        assertTrue(sirLancelot.isMoveLegal(horizontalL));
        assertTrue(sirLancelot.isMoveLegal(verticalL));
        assertTrue(sirLancelot.isMoveLegal(oneMoreL));

        //some bad moves
        Square diagonalMove = new Square(7, 4);
        Square fileMove = new Square(1, 2);
        Square rankMove = new Square(5, 4);
        Square randomMove = new Square(7, 6);
        Square pawnMove = new Square(4, 2);

        assertFalse(sirLancelot.isMoveLegal(diagonalMove));
        assertFalse(sirLancelot.isMoveLegal(fileMove));
        assertFalse(sirLancelot.isMoveLegal(rankMove));
        assertFalse(sirLancelot.isMoveLegal(randomMove));
        assertFalse(sirLancelot.isMoveLegal(pawnMove));
    }
}