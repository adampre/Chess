package PieceCode;

import java.awt.*;
import java.util.ArrayList;

public class Knight 
{
    public ArrayList<Point> moves(Piece piece, Piece[][] board, Point indexes)
    {
        ArrayList<Point> moves = new ArrayList<Point>();

        if(piece.color.equalsIgnoreCase("w"))
        {
            //left 2, up 1
            if(indexes.x - 2 >= 0 && indexes.y - 1 >= 0 && (board[indexes.x - 2][indexes.y - 1].piece == null || board[indexes.x - 2][indexes.y - 1].color.equalsIgnoreCase("b")))
            {
                moves.add(new Point(indexes.x - 2, indexes.y - 1));
            }
            //left 2, down 1
            if(indexes.x - 2 >= 0 && indexes.y + 1 < board.length && (board[indexes.x - 2][indexes.y + 1].piece == null || board[indexes.x - 2][indexes.y + 1].color.equalsIgnoreCase("b")))
            {
                moves.add(new Point(indexes.x - 2, indexes.y + 1));
            }
            //left 1, up 2
            if(indexes.x - 1 >= 0 && indexes.y - 2 >= 0 && (board[indexes.x - 1][indexes.y - 2].piece == null || board[indexes.x - 1][indexes.y - 2].color.equalsIgnoreCase("b")))
            {
                moves.add(new Point(indexes.x - 1, indexes.y - 2));
            }
            //left 1, down 2
            if(indexes.x - 1 >= 0 && indexes.y + 2 < board.length && (board[indexes.x - 1][indexes.y + 2].piece == null || board[indexes.x - 1][indexes.y + 2].color.equalsIgnoreCase("b")))
            {
                moves.add(new Point(indexes.x - 1, indexes.y + 2));
            }
            //right 1, up 2
            if(indexes.x + 1 < board[0].length && indexes.y - 2 >= 0 && (board[indexes.x + 1][indexes.y - 2].piece == null || board[indexes.x + 1][indexes.y - 2].color.equalsIgnoreCase("b")))
            {
                moves.add(new Point(indexes.x + 1, indexes.y - 2));
            }
            //right 1, down 2
            if(indexes.x + 1 < board[0].length && indexes.y + 2 < board.length && (board[indexes.x + 1][indexes.y + 2].piece == null || board[indexes.x + 1][indexes.y + 2].color.equalsIgnoreCase("b")))
            {
                moves.add(new Point(indexes.x + 1, indexes.y + 2));
            }
            //right 2, up 1
            if(indexes.x + 2 < board[0].length && indexes.y - 1 >= 0 && (board[indexes.x + 2][indexes.y - 1].piece == null || board[indexes.x + 2][indexes.y - 1].color.equalsIgnoreCase("b")))
            {
                moves.add(new Point(indexes.x + 2, indexes.y - 1));
            }
            //right 2, down 1
            if(indexes.x + 2 < board[0].length && indexes.y + 1 < board.length && (board[indexes.x + 2][indexes.y + 1].piece == null || board[indexes.x + 2][indexes.y + 1].color.equalsIgnoreCase("b")))
            {
                moves.add(new Point(indexes.x + 2, indexes.y + 1));
            }
        }
        else
        {
            //left 2, up 1
            if(indexes.x - 2 >= 0 && indexes.y - 1 >= 0 && (board[indexes.x - 2][indexes.y - 1].piece == null || board[indexes.x - 2][indexes.y - 1].color.equalsIgnoreCase("w")))
            {
                moves.add(new Point(indexes.x - 2, indexes.y - 1));
            }
            //left 2, down 1
            if(indexes.x - 2 >= 0 && indexes.y + 1 < board.length && (board[indexes.x - 2][indexes.y + 1].piece == null || board[indexes.x - 2][indexes.y + 1].color.equalsIgnoreCase("w")))
            {
                moves.add(new Point(indexes.x - 2, indexes.y + 1));
            }
            //left 1, up 2
            if(indexes.x - 1 >= 0 && indexes.y - 2 >= 0 && (board[indexes.x - 1][indexes.y - 2].piece == null || board[indexes.x - 1][indexes.y - 2].color.equalsIgnoreCase("w")))
            {
                moves.add(new Point(indexes.x - 1, indexes.y - 2));
            }
            //left 1, down 2
            if(indexes.x - 1 >= 0 && indexes.y + 2 < board.length && (board[indexes.x - 1][indexes.y + 2].piece == null || board[indexes.x - 1][indexes.y + 2].color.equalsIgnoreCase("w")))
            {
                moves.add(new Point(indexes.x - 1, indexes.y + 2));
            }
            //right 1, up 2
            if(indexes.x + 1 < board[0].length && indexes.y - 2 >= 0 && (board[indexes.x + 1][indexes.y - 2].piece == null || board[indexes.x + 1][indexes.y - 2].color.equalsIgnoreCase("w")))
            {
                moves.add(new Point(indexes.x + 1, indexes.y - 2));
            }
            //right 1, down 2
            if(indexes.x + 1 < board[0].length && indexes.y + 2 < board.length && (board[indexes.x + 1][indexes.y + 2].piece == null || board[indexes.x + 1][indexes.y + 2].color.equalsIgnoreCase("w")))
            {
                moves.add(new Point(indexes.x + 1, indexes.y + 2));
            }
            //right 2, up 1
            if(indexes.x + 2 < board[0].length && indexes.y - 1 >= 0 && (board[indexes.x + 2][indexes.y - 1].piece == null || board[indexes.x + 2][indexes.y - 1].color.equalsIgnoreCase("w")))
            {
                moves.add(new Point(indexes.x + 2, indexes.y - 1));
            }
            //right 2, down 1
            if(indexes.x + 2 < board[0].length && indexes.y + 1 < board.length && (board[indexes.x + 2][indexes.y + 1].piece == null || board[indexes.x + 2][indexes.y + 1].color.equalsIgnoreCase("w")))
            {
                moves.add(new Point(indexes.x + 2, indexes.y + 1));
            }
        }

        return moves;
    }
}
