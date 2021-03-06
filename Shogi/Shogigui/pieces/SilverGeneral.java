package Shogigui.pieces;

import Shogigui.Board;

/**
 * The SilverGeneral class is used to generate SilverGeneral Piece objects within the program: 
 * with all the properties corresponding to this piece
 * 
 * @Author Henry Browne – 37733273
 */
public class SilverGeneral extends Piece {

    public SilverGeneral(int x, int y, boolean is_white, String file_path, Board board,
            boolean is_promoted) {
        super(x, y, is_white, file_path, board, is_promoted);
        this.value=5;
    }

    @Override
    public boolean canMove(int destination_x, int destination_y) {

        if (!(this.isLegalMove(destination_x, destination_y))){
            return false;
        }

        // promoted moves

        if (this.is_promoted() == true) {

            this.value=6;

            if (this.isBlack() == true) {

                return (((this.getY() == destination_y + 1 && Math.abs(destination_x - this.getX()) <= 1)
                        || (this.getY() == destination_y - 1 && Math.abs(destination_x - this.getX()) == 1))
                        && (this.getY() == destination_y + 1 && Math.abs(destination_x - this.getX()) <= 1)
                        || (this.getY() == destination_y - 1 && (destination_x == this.getX()))
                        || this.getY() == destination_y && Math.abs(destination_x - this.getX()) == 1)?true:false;
            } else {
                return (((this.getY() == destination_y + 1 && Math.abs(destination_x - this.getX()) <= 1)
                        || (this.getY() == destination_y - 1 && Math.abs(destination_x - this.getX()) == 1))
                        && (this.getY() == destination_y - 1 && Math.abs(destination_x - this.getX()) <= 1)
                        || (this.getY() == destination_y + 1 && (destination_x == this.getX()))
                        || this.getY() == destination_y && Math.abs(destination_x - this.getX()) == 1)?true:false;
            }
        }
        else{
            this.value=5;
        }

        // only allow the silver general to move if it moves 1 forward in the y and the
        // change in the x is 0 or 1 or if it moves 1 back and the change in the x is
        // equal to 1

        if (this.isBlack() == true) {

            if ((this.getY() == destination_y + 1 && Math.abs(destination_x - this.getX()) <= 1)
                    || (this.getY() == destination_y - 1 && Math.abs(destination_x - this.getX()) == 1)) {
                return true;
            }

        } else {
            if ((this.getY() == destination_y - 1 && Math.abs(destination_x - this.getX()) <= 1)
                    || (this.getY() == destination_y + 1 && Math.abs(destination_x - this.getX()) == 1)) {
                return true;
            }
        }

        return false;
    }
}
