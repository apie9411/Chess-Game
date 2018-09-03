package Pieces;

import org.junit.Test;

import static org.junit.Assert.*;
import Game.*;

public class BishopTest {

    private Square initialLocationForPiece = new Square(3, 4);
    private Bishop testBishop = new Bishop('b', initialLocationForPiece);

    /**
     * The bishop can only move diagonally, so I need to make sure this function
     * does not approve of non-diagonal moves.
     */
    @Test
    public void isMoveLegal() {
        //testing forward diagonal
        Square forwardDiagonalLocation = new Square(5, 6);
        assertTrue(testBishop.isMoveLegal(forwardDiagonalLocation));

        //testing a few bad moves
        Square illegalMove1 = new Square(1,1);
        Square fileMove = new Square(3,6);
        Square rankMove = new Square(1, 4);
        Square lMove = new Square(4,6);

        assertFalse(testBishop.isMoveLegal(illegalMove1));
        assertFalse(testBishop.isMoveLegal(fileMove));
        assertFalse(testBishop.isMoveLegal(rankMove));
        assertFalse(testBishop.isMoveLegal(lMove));
    }

}