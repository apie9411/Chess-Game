package Pieces;

import Game.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueenTest {

    private Square initialLocationForPiece = new Square(2, 4);
    private Queen testQueen = new Queen('w', initialLocationForPiece);
    @Test
    public void isMoveLegal() {
        //test file and rank moves
        Square fileMoveUp = new Square(4,4);
        Square fileMoveDown = new Square(0,4);
        Square rankMoveLeft = new Square(2, 1);
        Square rankMoveRight = new Square(2, 7);

        assertTrue(testQueen.isMoveLegal(fileMoveUp));
        assertTrue(testQueen.isMoveLegal(fileMoveDown));
        assertTrue(testQueen.isMoveLegal(rankMoveLeft));
        assertTrue(testQueen.isMoveLegal(rankMoveRight));

        //test some bad moves
        Square unevenMove1 = new Square(3, 6);
        Square unevenMove2 = new Square(1,0);
        Square unevenMove3 = new Square(2,4); //not moving test
        Square unevenmove4 = new Square(7,7);

        assertFalse(testQueen.isMoveLegal(unevenMove1));
        assertFalse(testQueen.isMoveLegal(unevenMove2));
        assertFalse(testQueen.isMoveLegal(unevenMove3));
        assertFalse(testQueen.isMoveLegal(unevenmove4));
    }
}