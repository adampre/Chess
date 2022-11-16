package PieceCode;

import java.awt.*;
import java.util.ArrayList;

public class King 
{
    public ArrayList<Point> moves(Piece piece, Piece[][] board, Point indexes)
    {
        ArrayList<Point> moves = new ArrayList<Point>();

        if(piece.color.equalsIgnoreCase("w"))
        {
            //top left
            if((indexes.x > 0 && indexes.y > 0) && (board[indexes.x - 1][indexes.y - 1].piece == null || board[indexes.x - 1][indexes.y - 1].color.equalsIgnoreCase("b")))
            {
                moves.add(new Point(indexes.x - 1, indexes.y - 1));
            }

            //top middle
            if((indexes.y > 0) && (board[indexes.x][indexes.y - 1].piece == null || board[indexes.x][indexes.y - 1].color.equalsIgnoreCase("b")))
            {
                moves.add(new Point(indexes.x, indexes.y - 1));
            }

            //top right
            if((indexes.x < board[0].length - 1 && indexes.y > 0) && (board[indexes.x + 1][indexes.y - 1].piece == null || board[indexes.x + 1][indexes.y - 1].color.equalsIgnoreCase("b")))
            {
                moves.add(new Point(indexes.x + 1, indexes.y - 1));
            }

            //middle left
            if((indexes.x > 0) && (board[indexes.x - 1][indexes.y].piece == null || board[indexes.x - 1][indexes.y].color.equalsIgnoreCase("b")))
            {
                moves.add(new Point(indexes.x - 1, indexes.y));
            }

            //middle right
            if((indexes.x < board[0].length - 1) && (board[indexes.x + 1][indexes.y].piece == null || board[indexes.x + 1][indexes.y].color.equalsIgnoreCase("b")))
            {
                moves.add(new Point(indexes.x + 1, indexes.y));
            }

            //bottom left
            if((indexes.x > 0 && indexes.y < board.length - 1) && (board[indexes.x - 1][indexes.y + 1].piece == null || board[indexes.x - 1][indexes.y + 1].color.equalsIgnoreCase("b")))
            {
                moves.add(new Point(indexes.x - 1, indexes.y + 1));
            }

            //bottom middle
            if((indexes.y < board.length - 1) && (board[indexes.x][indexes.y + 1].piece == null || board[indexes.x][indexes.y + 1].color.equalsIgnoreCase("b")))
            {
                moves.add(new Point(indexes.x, indexes.y + 1));
            }

            //bottom right
            if((indexes.x < board[0].length - 1 && indexes.y < board.length - 1) && (board[indexes.x + 1][indexes.y + 1].piece == null || board[indexes.x + 1][indexes.y + 1].color.equalsIgnoreCase("b")))
            {
                moves.add(new Point(indexes.x + 1, indexes.y + 1));
            }

            //short castle
            if(piece.amountMoved == 0 && board[indexes.x + 1][indexes.y].piece == null && board[indexes.x + 2][indexes.y].piece == null && board[board[0].length - 1][board.length - 1].piece != null && board[board[0].length - 1][board.length - 1].pieceType.equalsIgnoreCase("r") && board[board[0].length - 1][board.length - 1].amountMoved == 0 && board[board[0].length - 1][board.length - 1].color.equalsIgnoreCase("w"))
            {
                moves.add(new Point(indexes.x + 2, indexes.y));
            }

            //long castle
            if(piece.amountMoved == 0 && board[indexes.x - 1][indexes.y].piece == null && board[indexes.x - 2][indexes.y].piece == null && board[indexes.x - 3][indexes.y].piece == null && board[0][board.length - 1].piece != null && board[0][board.length - 1].pieceType.equalsIgnoreCase("r") && board[0][board.length - 1].amountMoved == 0 && board[0][board.length - 1].color.equalsIgnoreCase("w"))
            {
                moves.add(new Point(indexes.x - 2, indexes.y));
            }
        }
        else
        {
            //top left
            if((indexes.x > 0 && indexes.y > 0) && (board[indexes.x - 1][indexes.y - 1].piece == null || board[indexes.x - 1][indexes.y - 1].color.equalsIgnoreCase("w")))
            {
                moves.add(new Point(indexes.x - 1, indexes.y - 1));
            }

            //top middle
            if((indexes.y > 0) && (board[indexes.x][indexes.y - 1].piece == null || board[indexes.x][indexes.y - 1].color.equalsIgnoreCase("w")))
            {
                moves.add(new Point(indexes.x, indexes.y - 1));
            }

            //top right
            if((indexes.x < board[0].length - 1 && indexes.y > 0) && (board[indexes.x + 1][indexes.y - 1].piece == null || board[indexes.x + 1][indexes.y - 1].color.equalsIgnoreCase("w")))
            {
                moves.add(new Point(indexes.x + 1, indexes.y - 1));
            }

            //middle left
            if((indexes.x > 0) && (board[indexes.x - 1][indexes.y].piece == null || board[indexes.x - 1][indexes.y].color.equalsIgnoreCase("w")))
            {
                moves.add(new Point(indexes.x - 1, indexes.y));
            }

            //middle right
            if((indexes.x < board[0].length - 1) && (board[indexes.x + 1][indexes.y].piece == null || board[indexes.x + 1][indexes.y].color.equalsIgnoreCase("w")))
            {
                moves.add(new Point(indexes.x + 1, indexes.y));
            }

            //bottom left
            if((indexes.x > 0 && indexes.y < board.length - 1) && (board[indexes.x - 1][indexes.y + 1].piece == null || board[indexes.x - 1][indexes.y + 1].color.equalsIgnoreCase("w")))
            {
                moves.add(new Point(indexes.x - 1, indexes.y + 1));
            }

            //bottom middle
            if((indexes.y < board.length - 1) && (board[indexes.x][indexes.y + 1].piece == null || board[indexes.x][indexes.y + 1].color.equalsIgnoreCase("w")))
            {
                moves.add(new Point(indexes.x, indexes.y + 1));
            }

            //bottom right
            if((indexes.x < board[0].length - 1 && indexes.y < board.length - 1) && (board[indexes.x + 1][indexes.y + 1].piece == null || board[indexes.x + 1][indexes.y + 1].color.equalsIgnoreCase("w")))
            {
                moves.add(new Point(indexes.x + 1, indexes.y + 1));
            }

            //short castle
            if(piece.amountMoved == 0 && board[indexes.x + 1][indexes.y].piece == null && board[indexes.x + 2][indexes.y].piece == null && board[board[0].length - 1][0].piece != null && board[board[0].length - 1][0].pieceType.equalsIgnoreCase("r") && board[board[0].length - 1][0].amountMoved == 0 && board[board[0].length - 1][0].color.equalsIgnoreCase("b"))
            {
                moves.add(new Point(indexes.x + 2, indexes.y));
            }

            //long castle
            if(piece.amountMoved == 0 && board[indexes.x - 1][indexes.y].piece == null && board[indexes.x - 2][indexes.y].piece == null && board[indexes.x - 3][indexes.y].piece == null && board[0][0].piece != null && board[0][0].pieceType.equalsIgnoreCase("r") && board[0][0].amountMoved == 0 && board[0][0].color.equalsIgnoreCase("b"))
            {
                moves.add(new Point(indexes.x - 2, indexes.y));
            }
        }

        return moves;
    }
}