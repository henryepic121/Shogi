package Shogigui.pieces;

import Shogigui.Board;

/**
 * The Lance class is used to generate Lance Piece objects within the program: 
 * with all the properties corresponding to this piece
 * 
 * @Author Henry Browne – 37733273
 */
public class Lance extends Piece {

    public Lance(int x, int y, boolean is_white, String file_path, Board board,
            boolean is_promoted) {
        super(x, y, is_white, file_path, board, is_promoted);
        this.value=3;
        if (this.isWhite()){
            this.rangedAttackDirections.add("S");
        }else{
            this.rangedAttackDirections.add("N");
        }
    }

    @Override
    public boolean canBeDropped(int destination_x, int destination_y) {

        if (this.is_captured == true && board.getPiece(destination_x, destination_y) == null) {

            if (this.isWhite() && destination_y > 7 || this.isBlack() && destination_y < 1
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

        // promoted moves

        if (this.is_promoted() == true) {

            this.value=6;

            if (this.isBlack() == true) {

                return ((this.getY() == destination_y + 1 && Math.abs(destination_x - this.getX()) <= 1)
                        || (this.getY() == destination_y - 1 && (destination_x == this.getX()))
                        || this.getY() == destination_y && Math.abs(destination_x - this.getX()) == 1)? true:false; 
            } else {
                return ((this.getY() == destination_y - 1 && Math.abs(destination_x - this.getX()) <= 1)
                        || (this.getY() == destination_y + 1 && (destination_x == this.getX()))
                        || this.getY() == destination_y && Math.abs(destination_x - this.getX()) == 1)? true:false;
            }
        }
        else{
            this.value=3;
        }

        // if it is trying to move somewhere not in a straight line forward, dont let it

        if (this.getX() != destination_x) {
            return false;
        }

        if (this.isBlack() == true) {

            if (this.getY() < destination_y) {
                return false;
            }
        } else {
            if (this.getY() > destination_y) {
                return false;
            }
        }

        // make sure their is nothing in the way of the move

        if (nothingBetweenPosAndMoveDest(destination_x, destination_y) == false) {
            return false;
        }

        return true;
    }
}