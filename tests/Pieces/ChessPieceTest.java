package Pieces;

import org.junit.Test;
import Game.*;

import static org.junit.Assert.*;

public class ChessPieceTest {

    private Square squareForPieceLocation = new Square(0, 7);
    private Square squareForPieceLocation2 = new Square(5, 6);
    private Pawn aChessPiece = new Pawn('b', squareForPieceLocation);
    private Rook anotherPiece = new Rook('w', squareForPieceLocation2);

    @Test
    public void getColor() {
        char shouldBeBlack = aChessPiece.getColor();
        char shouldBeWhite = anotherPiece.getColor();
        assertEquals('b', shouldBeBlack);
        assertEquals('w', shouldBeWhite);
    }

    @Test
    public void isCaptured() {
        boolean firstNotCaptured = aChessPiece.isCaptured();
        boolean secondNotCaptured = anotherPiece.isCaptured();
        assertEquals(false, firstNotCaptured);
        assertEquals(false, secondNotCaptured);
        aChessPiece.capturePiece();
        boolean firstIsCaptured = aChessPiece.isCaptured();

        assertEquals(true, firstIsCaptured);
    }

    @Test
    public void capturePiece() {
        anotherPiece.capturePiece();
        assertTrue(anotherPiece.isCaptured());
    }

    @Test
    public void getLocationOnBoard() {
        Square currentLocation1 = aChessPiece.getLocationOnBoard();
        Square currentLocation2 = anotherPiece.getLocationOnBoard();
        assertEquals(squareForPieceLocation, currentLocation1);
        assertEquals(squareForPieceLocation2, currentLocation2);
    }

    @Test
    public void setLocationOnBoard() {
        Square newLocationForPiece = new Square(4, 1);
        aChessPiece.setLocationOnBoard(newLocationForPiece);
        assertEquals(newLocationForPiece, aChessPiece.getLocationOnBoard());
    }
}