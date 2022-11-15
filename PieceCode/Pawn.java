package PieceCode;

import java.awt.*;
import java.util.ArrayList;

public class Pawn 
{
    public ArrayList<Point> moves(Piece piece, Piece[][] board, Point indexes)
    {
        ArrayList<Point> moves = new ArrayList<Point>();

        if(piece.color.equalsIgnoreCase("w"))
        {
            //taking another piece
            if(indexes.x == 0)
            {
                if(board[indexes.x + 1][indexes.y - 1].piece != null && board[indexes.x + 1][indexes.y - 1].color.equalsIgnoreCase("b"))
                {
                    moves.add(new Point(indexes.x + 1, indexes.y - 1));                    
                }      

                //en passant
                if(board[indexes.x + 1][indexes.y].piece != null && indexes.y == 3 && board[indexes.x + 1][indexes.y].color.equalsIgnoreCase("b") && board[indexes.x + 1][indexes.y].pieceType.equalsIgnoreCase("p") && board[indexes.x + 1][indexes.y].amountMoved < 2)
                {
                    moves.add(new Point(indexes.x + 1, indexes.y - 1));
                }
            }
            else if(indexes.x == board[0].length - 1)
            {
                if(board[indexes.x - 1][indexes.y - 1].piece != null && board[indexes.x - 1][indexes.y - 1].color.equalsIgnoreCase("b"))
                {
                    moves.add(new Point(indexes.x - 1, indexes.y - 1));   
                }

                //en passant
                if(board[indexes.x - 1][indexes.y].piece != null && indexes.y == 3 && board[indexes.x - 1][indexes.y].color.equalsIgnoreCase("b") && board[indexes.x - 1][indexes.y].pieceType.equalsIgnoreCase("p") && board[indexes.x - 1][indexes.y].amountMoved < 2)
                {
                    moves.add(new Point(indexes.x - 1, indexes.y - 1));
                }
            }
            else
            {
                if(board[indexes.x + 1][indexes.y - 1].piece != null && board[indexes.x + 1][indexes.y - 1].color.equalsIgnoreCase("b"))
                {
                    moves.add(new Point(indexes.x + 1, indexes.y - 1));   
                }
                if(board[indexes.x - 1][indexes.y - 1].piece != null && board[indexes.x - 1][indexes.y - 1].color.equalsIgnoreCase("b"))
                {
                    moves.add(new Point(indexes.x - 1, indexes.y - 1));   
                }

                //en passant
                if(board[indexes.x + 1][indexes.y].piece != null && indexes.y == 3 && board[indexes.x + 1][indexes.y].color.equalsIgnoreCase("b") && board[indexes.x + 1][indexes.y].pieceType.equalsIgnoreCase("p") && board[indexes.x + 1][indexes.y].amountMoved < 2)
                {
                    moves.add(new Point(indexes.x + 1, indexes.y - 1));
                }
                if(board[indexes.x - 1][indexes.y].piece != null && indexes.y == 3 && board[indexes.x - 1][indexes.y].color.equalsIgnoreCase("b") && board[indexes.x - 1][indexes.y].pieceType.equalsIgnoreCase("p") && board[indexes.x - 1][indexes.y].amountMoved < 2)
                {
                    moves.add(new Point(indexes.x - 1, indexes.y - 1));
                }
            }

            //moving up and double promotion
            if(board[indexes.x][indexes.y - 1].piece == null)
            {
                moves.add(new Point(indexes.x, indexes.y - 1));

                if(!board[indexes.x][indexes.y].hasMoved && board[indexes.x][indexes.y - 2].piece == null)
                {
                    moves.add(new Point(indexes.x, indexes.y - 2));
                }
            }
        }
        else
        {
            if(indexes.x == 0)
            {
                if(board[indexes.x + 1][indexes.y + 1].piece != null && board[indexes.x + 1][indexes.y + 1].color.equalsIgnoreCase("w"))
                {
                    moves.add(new Point(indexes.x + 1, indexes.y + 1));   
                }

                //en passant
                if(board[indexes.x + 1][indexes.y].piece != null && indexes.y == 4 && board[indexes.x + 1][indexes.y].color.equalsIgnoreCase("w") && board[indexes.x + 1][indexes.y].pieceType.equalsIgnoreCase("p") && board[indexes.x + 1][indexes.y].amountMoved < 2)
                {
                    moves.add(new Point(indexes.x + 1, indexes.y + 1));
                }
            }
            else if(indexes.x == board[0].length - 1)
            {
                if(board[indexes.x - 1][indexes.y + 1].piece != null && board[indexes.x - 1][indexes.y + 1].color.equalsIgnoreCase("w"))
                {
                    moves.add(new Point(indexes.x - 1, indexes.y + 1));   
                }

                //en passant
                if(board[indexes.x - 1][indexes.y].piece != null && indexes.y == 4 && board[indexes.x - 1][indexes.y].color.equalsIgnoreCase("w") && board[indexes.x - 1][indexes.y].pieceType.equalsIgnoreCase("p") && board[indexes.x - 1][indexes.y].amountMoved < 2)
                {
                    moves.add(new Point(indexes.x - 1, indexes.y - 1));
                }
            }
            else
            {
                if(board[indexes.x + 1][indexes.y + 1].piece != null && board[indexes.x + 1][indexes.y + 1].color.equalsIgnoreCase("w"))
                {
                    moves.add(new Point(indexes.x + 1, indexes.y + 1));   
                }
                if(board[indexes.x - 1][indexes.y + 1].piece != null && board[indexes.x - 1][indexes.y + 1].color.equalsIgnoreCase("w"))
                {
                    moves.add(new Point(indexes.x - 1, indexes.y + 1));   
                }

                //en passant
                if(board[indexes.x + 1][indexes.y].piece != null && indexes.y == 4 && board[indexes.x + 1][indexes.y].color.equalsIgnoreCase("w") && board[indexes.x + 1][indexes.y].pieceType.equalsIgnoreCase("p") && board[indexes.x + 1][indexes.y].amountMoved < 2)
                {
                    moves.add(new Point(indexes.x + 1, indexes.y + 1));
                }
                if(board[indexes.x - 1][indexes.y].piece != null && indexes.y == 4 && board[indexes.x - 1][indexes.y].color.equalsIgnoreCase("w") && board[indexes.x - 1][indexes.y].pieceType.equalsIgnoreCase("p") && board[indexes.x - 1][indexes.y].amountMoved < 2)
                {
                    moves.add(new Point(indexes.x - 1, indexes.y + 1));
                }
            }

            if(board[indexes.x][indexes.y + 1].piece == null)
            {
                moves.add(new Point(indexes.x, indexes.y + 1));

                if(board[indexes.x][indexes.y + 2].piece == null && !board[indexes.x][indexes.y].hasMoved)
                {
                    moves.add(new Point(indexes.x, indexes.y + 2));
                }
            }
        }

        return moves;
    }
}