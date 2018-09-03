package Game;

import Pieces.*;

import java.util.ArrayList;


public class Game {

    private Player playerW;
    private Player playerB;
    private Board chessBoard;
    private boolean gameOver = false; //used in main game loop

    /**
     * Helper method to set up a player's chess pieces in their correct positions.
     *
     * @param playerToBeSet the player whose chess pieces will be initialized
     */
    private void setPlayer(Player playerToBeSet) {
        Square[][] chessSquares = chessBoard.getChessBoard();

        int pawnRow;
        int nonPawnRow;
        Square pieceLocation;
        ChessPiece playerPiece;

        //black pieces are at the top of the board (higher indices), white pieces at the bottom (lower indices)
        if(playerToBeSet.getColor() == 'w') {
            pawnRow = 1;
            nonPawnRow = 0;
        }
        //otherwise, the color is 'b' for black
        else {
            pawnRow = 6;
            nonPawnRow = 7;
        }

        for(int pawnColumn = 0; pawnColumn < 8; pawnColumn++) {
            pieceLocation = chessSquares[pawnRow][pawnColumn];
            playerPiece = new Pawn(playerToBeSet.getColor(), pieceLocation);
            setChessPieceOnBoard(playerToBeSet, pieceLocation, playerPiece);
        }

        pieceLocation = chessSquares[nonPawnRow][0];
        playerPiece = new Rook(playerToBeSet.getColor(), pieceLocation);
        setChessPieceOnBoard(playerToBeSet, pieceLocation, playerPiece);

        pieceLocation = chessSquares[nonPawnRow][1];
        playerPiece = new Knight(playerToBeSet.getColor(), pieceLocation);
        setChessPieceOnBoard(playerToBeSet, pieceLocation, playerPiece);

        pieceLocation = chessSquares[nonPawnRow][2];
        playerPiece = new Bishop(playerToBeSet.getColor(), pieceLocation);
        setChessPieceOnBoard(playerToBeSet, pieceLocation, playerPiece);

        pieceLocation = chessSquares[nonPawnRow][3];
        playerPiece = new Queen(playerToBeSet.getColor(), pieceLocation);
        setChessPieceOnBoard(playerToBeSet, pieceLocation, playerPiece);

        pieceLocation = chessSquares[nonPawnRow][4];
        playerPiece = new King(playerToBeSet.getColor(), pieceLocation);
        setChessPieceOnBoard(playerToBeSet, pieceLocation, playerPiece);

        pieceLocation = chessSquares[nonPawnRow][5];
        playerPiece = new Bishop(playerToBeSet.getColor(), pieceLocation);
        setChessPieceOnBoard(playerToBeSet, pieceLocation, playerPiece);

        pieceLocation = chessSquares[nonPawnRow][6];
        playerPiece = new Knight(playerToBeSet.getColor(), pieceLocation);
        setChessPieceOnBoard(playerToBeSet, pieceLocation, playerPiece);

        pieceLocation = chessSquares[nonPawnRow][7];
        playerPiece = new Rook(playerToBeSet.getColor(), pieceLocation);
        setChessPieceOnBoard(playerToBeSet, pieceLocation, playerPiece);
    }

    /**
     * Small helper method used in setPlayer() to save space.
     */
    private void setChessPieceOnBoard(Player playerToBeSet, Square pieceLocation, ChessPiece playerPiece) {
        playerToBeSet.availablePieces.add(playerPiece);
        pieceLocation.setColorOfOccupant(playerToBeSet.getColor());
        pieceLocation.setOccupantPiece(playerPiece);
    }

    /**
     * Helper function to decide if file-wise movement is allowed and not blocked by other pieces.
     *
     * @param numSteps the (signed) number of steps to travel across the straight path
     * @param startingPoint the point, either a row or column, where the chess piece starts
     * @param pathHolder the column or row that defines the straight path
     * @param finishingPoint the point, either a row or column, where the chess piece would end up
     * @return whether or not the specified straight path movement is unblocked and valid
     */
    private boolean goThroughStraightPath(int numSteps, int startingPoint, int pathHolder, int finishingPoint) {
        Square[][] chessSquares = chessBoard.getChessBoard();
        char currentPieceColor = chessSquares[startingPoint][pathHolder].getColorOfOccupant();
        if(numSteps != 0) {
            if(numSteps > 0) {
                int intermediatePoint = startingPoint - 1;
                while(intermediatePoint >= finishingPoint) {
                    Square intermediateSquare = chessSquares[intermediatePoint][pathHolder];
                    if(intermediateSquare.getColorOfOccupant() == 'u') {
                        intermediatePoint--;
                    }
                    else {
                        return (intermediatePoint == finishingPoint) &&
                                (intermediateSquare.getColorOfOccupant() != currentPieceColor);
                    }
                }
                return true;
            }
            else {
                int intermediatePoint = startingPoint + 1;
                while(intermediatePoint <= finishingPoint) {
                    Square intermediateSquare = chessSquares[intermediatePoint][pathHolder];
                    if(intermediateSquare.getColorOfOccupant() == 'u') {
                        intermediatePoint++;
                    }
                    else {
                        return (intermediatePoint == finishingPoint) &&
                                (intermediateSquare.getColorOfOccupant() != currentPieceColor);
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Constructor that sets up the two players and initializes the chessboard.
     */
    public Game() {
        playerW = new Player('w');
        playerB = new Player('b');
        chessBoard = new Board(8,8);

        setPlayer(playerW);
        setPlayer(playerB);
    }

    /**
     * Checks if moving the rook encounters any blocking pieces or illegal jumping over pieces.
     *
     * @param currentLocation the rook's current position on the chessboard
     * @param desiredLocation the place the rook wants to move (provided the move is legal)
     * @return whether or not the path is clear for the rook to proceed
     */
    public boolean isRookMoveValid(Square currentLocation, Square desiredLocation) {
        int currentRow = currentLocation.getRowOnBoard();
        int currentColumn = currentLocation.getColumnOnBoard();
        int desiredRow = desiredLocation.getRowOnBoard();
        int desiredColumn = desiredLocation.getColumnOnBoard();

        int rowDifference = currentRow - desiredRow;
        int columnDifference = currentColumn - desiredColumn;

        return goThroughStraightPath(rowDifference, currentRow, currentColumn, desiredRow) ||
                goThroughStraightPath(columnDifference, currentColumn, currentRow, desiredColumn);
    }

    /**
     * Checks if moving the bishop encounters any blocking pieces or illegal jumping over pieces.
     *
     * @param currentLocation the bishop's current position on the chessboard
     * @param desiredLocation the place the bishop wants to move (provided the move is legal)
     * @return whether or not the path is clear for the bishop to proceed
     */
    public boolean isBishopMoveValid(Square currentLocation, Square desiredLocation) {
        //4 if statements ugh
        Square[][] chessSquares = chessBoard.getChessBoard();
        char colorOfBishop = currentLocation.getColorOfOccupant();
        int currentRow = currentLocation.getRowOnBoard();
        int currentColumn = currentLocation.getColumnOnBoard();
        int desiredRow = desiredLocation.getRowOnBoard();
        int desiredColumn = desiredLocation.getColumnOnBoard();

        int rowDifference = currentRow - desiredRow;
        int columnDifference = currentColumn - desiredColumn;

        int intermediateRow;
        int intermediateColumn;
        Square intermediateSquare;

        if(rowDifference > 0 && columnDifference > 0) {
            intermediateRow = currentRow - 1;
            intermediateColumn = currentColumn - 1;
            while(intermediateRow >= desiredRow && intermediateColumn >= desiredColumn) {
                intermediateSquare = chessSquares[intermediateRow][intermediateColumn];
                if(intermediateSquare.getColorOfOccupant() == 'u') {
                    intermediateRow--;
                    intermediateColumn--;
                }
                else {
                    return (intermediateRow == desiredRow) && (intermediateColumn == desiredColumn)
                            && (intermediateSquare.getColorOfOccupant() != colorOfBishop);
                }
            }
            return true;
        }
        else if(rowDifference > 0 && columnDifference < 0) {
            intermediateRow = currentRow - 1;
            intermediateColumn = currentColumn + 1;
            while(intermediateRow >= desiredRow && intermediateColumn <= desiredColumn) {
                intermediateSquare = chessSquares[intermediateRow][intermediateColumn];
                if(intermediateSquare.getColorOfOccupant() == 'u') {
                    intermediateRow--;
                    intermediateColumn++;
                }
                else {
                    return (intermediateRow == desiredRow) && (intermediateColumn == desiredColumn)
                            && (intermediateSquare.getColorOfOccupant() != colorOfBishop);
                }
            }
            return true;
        }
        else if(rowDifference < 0 && columnDifference > 0) {
            intermediateRow = currentRow + 1;
            intermediateColumn = currentColumn - 1;
            while(intermediateRow <= desiredRow && intermediateColumn >= desiredColumn) {
                intermediateSquare = chessSquares[intermediateRow][intermediateColumn];
                if(intermediateSquare.getColorOfOccupant() == 'u') {
                    intermediateRow++;
                    intermediateColumn--;
                }
                else {
                    return (intermediateRow == desiredRow) && (intermediateColumn == desiredColumn)
                            && (intermediateSquare.getColorOfOccupant() != colorOfBishop);
                }
            }
            return true;
        }
        else {
            intermediateRow = currentRow + 1;
            intermediateColumn = currentColumn + 1;
            while(intermediateRow <= desiredRow && intermediateColumn <= desiredColumn) {
                intermediateSquare = chessSquares[intermediateRow][intermediateColumn];
                if(intermediateSquare.getColorOfOccupant() == 'u') {
                    intermediateRow++;
                    intermediateColumn++;
                }
                else {
                    return (intermediateRow == desiredRow) && (intermediateColumn == desiredColumn)
                            && (intermediateSquare.getColorOfOccupant() != colorOfBishop);
                }
            }
            return true;
        }
    }

    /**
     * Checks if moving the knight encounters any blocking pieces.
     *
     * @param currentLocation the knight's current position on the chessboard
     * @param desiredLocation the place the knight wants to move (provided the move is legal)
     * @return whether or not the path is clear for the knight to proceed
     */
    public boolean isKnightMoveValid(Square currentLocation, Square desiredLocation) {
        char colorOfKnight = currentLocation.getColorOfOccupant();
        if(desiredLocation.getColorOfOccupant() == 'u') {
            //the space is not occupied and knights can jump other pieces, so move is valid
            return true;
        }
        else {
            //can't capture a piece that's the same color
            return !(colorOfKnight == desiredLocation.getColorOfOccupant());
        }
    }

    /**
     * Checks if moving the pawn encounters any blocking pieces or illegal jumping over pieces.
     *
     * @param currentLocation the pawn's current position on the chessboard
     * @param desiredLocation the place the pawn wants to move (provided the move is legal)
     * @return whether or not the path is clear for the pawn to proceed
     */
    public boolean isPawnMoveValid(Square currentLocation, Square desiredLocation) {
        Square[][] chessSquares = chessBoard.getChessBoard();
        char colorOfPawn = currentLocation.getColorOfOccupant();
        int currentRow = currentLocation.getRowOnBoard();
        int currentColumn = currentLocation.getColumnOnBoard();
        int desiredRow = desiredLocation.getRowOnBoard();
        int desiredColumn = desiredLocation.getColumnOnBoard();

        int rowDifference = currentRow - desiredRow;
        int columnDifference = currentColumn - desiredColumn;

        if(columnDifference != 0) {
            //capturing is accounted for in the pawn move legality check
            return true;
        }
        else {
            if(rowDifference > 0) {
                int intermediateRow = currentRow - 1;
                while(intermediateRow >= desiredRow) {
                    Square intermediateSquare = chessSquares[intermediateRow][currentColumn];
                    if(intermediateSquare.getColorOfOccupant() == 'u') {
                        intermediateRow--;
                    }
                    else {
                        return false;
                    }
                }
                return true;
            }
            else {
                int intermediateRow = currentRow + 1;
                while(intermediateRow <= desiredRow) {
                    Square intermediateSquare = chessSquares[intermediateRow][currentColumn];
                    if(intermediateSquare.getColorOfOccupant() == 'u') {
                        intermediateRow++;
                    }
                    else {
                        return false;
                    }
                }
                return true;
            }
        }
    }

    /**
     * Checks if moving the king encounters any blocking pieces.
     *
     * @param currentLocation the king's current position on the chessboard
     * @param desiredLocation the place the king wants to move (provided the move is legal)
     * @return whether or not the path is clear for the king to proceed
     */
    public boolean isKingMoveValid(Square currentLocation, Square desiredLocation) {
        char colorOfKing = currentLocation.getColorOfOccupant();

        return !(colorOfKing == desiredLocation.getColorOfOccupant());
    }

    /**
     * Checks if moving the queen encounters any blocking pieces or illegal jumping over pieces.
     *
     * @param currentLocation the queen's current position on the chessboard
     * @param desiredLocation the place the queen wants to move (provided the move is legal)
     * @return whether or not the path is clear for the queen to proceed
     */
    public boolean isQueenMoveValid(Square currentLocation, Square desiredLocation) {
        int currentRow = currentLocation.getRowOnBoard();
        int currentColumn = currentLocation.getColumnOnBoard();
        int desiredRow = desiredLocation.getRowOnBoard();
        int desiredColumn = desiredLocation.getColumnOnBoard();

        int rowDifference = currentRow - desiredRow;
        int columnDifference = currentColumn - desiredColumn;

        //dealing with rook movement
        if(rowDifference == 0 || columnDifference == 0) {
            return isRookMoveValid(currentLocation, desiredLocation);
        }
        //if it's not rook movement, it must be bishop movement
        else {
            return isBishopMoveValid(currentLocation, desiredLocation);
        }
    }

    /**
     * Private helper function to decide if a path on the board is unblocked for the
     * different types of chess pieces.
     *
     * @param listOfAttackers the pieces that can potentially attack the king being analyzed in isInCheck()
     * @param desiredLocation the location of the king used in isInCheck()
     * @return a boolean stating whether or not an attacker has a clear path to the king
     */
    private boolean isPathUnblocked(ArrayList<ChessPiece> listOfAttackers, Square desiredLocation) {
        for(ChessPiece attacker : listOfAttackers) {
            Square currentLocation = attacker.getLocationOnBoard();
            switch(attacker.type)
            {
                case "pawn":
                    if(isPawnMoveValid(currentLocation, desiredLocation)){
                        return true;
                    }
                case "rook":
                    if(isRookMoveValid(currentLocation, desiredLocation)) {
                        return true;
                    }
                case "bishop":
                    if(isBishopMoveValid(currentLocation, desiredLocation)) {
                        return true;
                    }
                case "knight":
                    if(isKnightMoveValid(currentLocation, desiredLocation)) {
                        return true;
                    }
                case "king":
                    if(isKingMoveValid(currentLocation, desiredLocation)) {
                        return true;
                    }
                case "queen":
                    if(isQueenMoveValid(currentLocation, desiredLocation)) {
                        return true;
                    }
            }
            continue;
        }
        return false;
    }

    /**
     * Private helper function used in isInCheck(). Narrows down the possible attackers on the king
     * and checks all of them to see if any put him in check.
     *
     * @param kingLocation the king's location on the board
     * @param attacker the player that could put the king in check
     * @return whether or not the king is in check
     */
    private boolean checkPotentialAttackers(Square kingLocation, Player attacker) {
        ArrayList<ChessPiece> potentialAttackers = attacker.availablePieces;
        ArrayList<ChessPiece> inRangeAttackers = new ArrayList<ChessPiece>();
        for(ChessPiece potentialAttacker : potentialAttackers) {
            //find the chess pieces that have legal paths to attack the king (when unblocked)
            if(potentialAttacker.isMoveLegal(kingLocation)) {
                inRangeAttackers.add(potentialAttacker);
            }
        }
        return isPathUnblocked(inRangeAttackers, kingLocation);
    }

    /**
     * Checks whether a player's king is in check.
     *
     * @param playerA the player whose king is being analyzed
     * @return whether or not the player's king is in check
     */
    public boolean isInCheck(Player playerA) {
        int pieceIndex = 0;
        ChessPiece playerKing = playerA.availablePieces.get(pieceIndex);
        while(playerKing.type != "king" && pieceIndex < playerA.availablePieces.size()) {
            pieceIndex++;
            playerKing = playerA.availablePieces.get(pieceIndex);
        }
        Square kingLocation = playerKing.getLocationOnBoard();
        ArrayList<ChessPiece> potentialAttackers;

        if(playerA == playerW) {
            return checkPotentialAttackers(kingLocation, playerB);
        }
        else {
            return checkPotentialAttackers(kingLocation, playerW);
        }
    }

    /**
     * Gets all of the spaces adjacent to the space requested.
     *
     * @param currentLocation the current position to get the adjacent spaces of
     * @return an array of the adjacent spaces
     */
    private ArrayList<Square> getNeighboringSpaces(Square currentLocation) {
        ArrayList<Square> neighboringSpaces = new ArrayList<Square>();
        Square[][] chessSquares = chessBoard.getChessBoard();
        int currentRow = currentLocation.getRowOnBoard();
        int currentColumn = currentLocation.getColumnOnBoard();

        addRealNeighbors(neighboringSpaces, chessSquares, currentRow + 1,
                currentColumn);
        addRealNeighbors(neighboringSpaces, chessSquares, currentRow,
                currentColumn + 1);
        addRealNeighbors(neighboringSpaces, chessSquares, currentRow - 1,
                currentColumn);
        addRealNeighbors(neighboringSpaces, chessSquares, currentRow,
                currentColumn - 1);
        addRealNeighbors(neighboringSpaces, chessSquares, currentRow - 1,
                currentColumn - 1);
        addRealNeighbors(neighboringSpaces, chessSquares, currentRow + 1,
                currentColumn - 1);
        addRealNeighbors(neighboringSpaces, chessSquares, currentRow - 1,
                currentColumn + 1);
        addRealNeighbors(neighboringSpaces, chessSquares, currentRow + 1,
                currentColumn + 1);
        return neighboringSpaces;
    }

    /**
     * Small helper function that makes sure the neighbors are within the bounds of the chessboard.
     */
    private void addRealNeighbors(ArrayList<Square> neighboringSpaces, Square[][] chessSquares, int neighborRow,
                                  int neighborColumn) {
        if(chessBoard.isOnBoard(neighborRow, neighborColumn)) {
            neighboringSpaces.add(chessSquares[neighborRow][neighborColumn]);
        }
    }

    /**
     * A helper function used in isInStalemate() and isInCheckmate().
     * Determines if the player can make any more legal moves.
     *
     * @param playerA the player whose move options are being analyzed
     * @return whether or not the player can make any more legal moves
     */
    private boolean isOutOfLegalMoves(Player playerA) {
        int pieceIndex = 0;
        ChessPiece playerKing = playerA.availablePieces.get(pieceIndex);
        while(playerKing.type != "king" && pieceIndex < playerA.availablePieces.size()) {
            pieceIndex++;
            playerKing = playerA.availablePieces.get(pieceIndex);
        }
        Square kingLocation = playerKing.getLocationOnBoard();
        ArrayList<Square> neighboringSpaces = getNeighboringSpaces(kingLocation);
        if(playerA == playerW) {
            //checking if moving to a neighboring space will put king in check
            for(Square neighbor : neighboringSpaces) {
                if(!checkPotentialAttackers(neighbor, playerB)){
                    return false;
                }
            }
            return true;
        }
        else {
            for(Square neighbor : neighboringSpaces) {
                if(!checkPotentialAttackers(neighbor, playerW)) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Decides whether or not a player is in stalemate.
     *
     * @param playerA the player who is being analyzed
     * @return whether or not the player is in stalemate
     */
    public boolean isInStalemate(Player playerA) {
        return isOutOfLegalMoves(playerA) && !isInCheck(playerA);
    }

    /**
     * Decides whether or not a player is in checkmate.
     *
     * @param playerA the player who is being analyzed
     * @return whether or not the player is in checkmate
     */
    public boolean isInCheckmate(Player playerA) {
        return isOutOfLegalMoves(playerA) && isInCheck(playerA);
    }

    public static void main(String[] args) {
        //place game loop here
    }
}
