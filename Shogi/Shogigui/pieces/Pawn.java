package Shogigui.pieces;

import Shogigui.Board;

/**
 * The Pawn class is used to generate Pawn Piece objects within the program: 
 * with all the properties corresponding to this piece
 * 
 * @Author Henry Browne – 37733273
 */
public class Pawn extends Piece {

    public Pawn(int x, int y, boolean is_white, String file_path, Board board,
            boolean is_promoted) {
        super(x, y, is_white, file_path, board, is_promoted);
        this.value=1;
    }

    @Override
    public boolean canBeDropped(int destination_x, int destination_y) {

        if (this.is_captured == true && board.getPiece(destination_x, destination_y) == null) {

            if ((this.is_promoted==false&&(this.isWhite() && destination_y > 7 || this.isBlack() && destination_y < 1)) || (this.moveIsOutOfBounds(destination_x, destination_y))) {
                return false;
            }

            for (int y = 0; y < board.getROWS(); y++) {
                if (board.getPiece(destination_x, y) != null) {
                    if (board.getPiece(destination_x, y).getClass().equals(Pawn.class)
                            && (board.getPiece(destination_x, y).isWhite() && this.isWhite()
                                    || board.getPiece(destination_x, y).isBlack() && this.isBlack()) && this.is_promoted==false) {
                        return false;
                    }
                }
            }

            // dont allow pawn to be dropped if it results in checkmate 

            if (this.isCheckMateMove(destination_x, destination_y)){
                return false;
            }

            return true;
        }
        return false;
    }

    @Override
    public boolean canMove(int destination_x, int destination_y) {

        if (!(this.isLegalMove(destination_x, destination_y))){
            return false;
        }

        if (this.is_promoted == false) {

            this.value=1;

            // if it is trying to move somewhere not in a straight line forward, more than 1
            // space forward or backwards dont let it

            if (this.getX() != destination_x) {
                return false;
            }

            if (this.isBlack() == true) {

                if (this.getY() > destination_y + 1 || this.getY() < destination_y) {
                    return false;
                }
            } else {
                if (this.getY() < destination_y - 1 || this.getY() > destination_y) {
                    return false;
                }
            }
        } else {

            this.value=7;

            // promoted moves

            if (this.isBlack() == true) {

                if ((this.getY() == destination_y + 1 && Math.abs(destination_x - this.getX()) <= 1)
                        || (this.getY() == destination_y - 1 && (destination_x == this.getX()))
                        || this.getY() == destination_y && Math.abs(destination_x - this.getX()) == 1) {
                    return true;
                }

                else {
                    return false;
                }
            } else {
                if ((this.getY() == destination_y - 1 && Math.abs(destination_x - this.getX()) <= 1)
                        || (this.getY() == destination_y + 1 && (destination_x == this.getX()))
                        || this.getY() == destination_y && Math.abs(destination_x - this.getX()) == 1) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

}
