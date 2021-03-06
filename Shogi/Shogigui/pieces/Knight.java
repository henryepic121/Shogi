package Shogigui.pieces;

import Shogigui.Board;

/**
 * The Knight class is used to generate Knight Piece objects within the program: 
 * with all the properties corresponding to this piece
 * 
 * @Author Henry Browne – 37733273
 */
public class Knight extends Piece {

    public Knight(int x, int y, boolean is_white, String file_path, Board board,
            boolean is_promoted) {
        super(x, y, is_white, file_path, board, is_promoted);
        this.value=4;
    }

    @Override
    public boolean canBeDropped(int destination_x, int destination_y) {

        if (this.is_captured == true && board.getPiece(destination_x, destination_y) == null) {

            if (this.isWhite() && destination_y > 6 || this.isBlack() && destination_y < 2
                    || (this.moveIsOutOfBounds(destination_x, destination_y))) {
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

        if (this.is_promoted() == false) {

            this.value=4;

            // the knight can only move forward

            if (this.isBlack() == true) {
                if (destination_y > this.getY()) {
                    return false;
                }
            } else {
                if (destination_y < this.getY()) {
                    return false;
                }
            }

            // if the knight moves two squares in the y and moves 1
            // square in the x its a valid move

            if (Math.abs(destination_x - this.getX()) == 1 && Math.abs(destination_y - this.getY()) == 2) {
                return true;
            }

        } else {

            this.value=6;

            // promoted moves

            if (this.isBlack() == true) {

                return ((this.getY() == destination_y + 1 && Math.abs(destination_x - this.getX()) <= 1)
                        || (this.getY() == destination_y - 1 && (destination_x == this.getX()))
                        || this.getY() == destination_y && Math.abs(destination_x - this.getX()) == 1) ? true
                                : false;
            } else {
                return ((this.getY() == destination_y - 1 && Math.abs(destination_x - this.getX()) <= 1)
                        || (this.getY() == destination_y + 1 && (destination_x == this.getX()))
                        || this.getY() == destination_y && Math.abs(destination_x - this.getX()) == 1) ? true
                                : false;
            }

        }

        return false;
    }
}
