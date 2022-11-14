package PieceCode;

import java.awt.*;
import java.util.ArrayList;

public class Bishop 
{
    public ArrayList<Point> moves(Piece piece, Piece[][] board, Point indexes)
    {
        ArrayList<Point> moves = new ArrayList<Point>();

        if(piece.color.equalsIgnoreCase("w"))
        {
            //checking left up
            if(indexes.x > 0 && indexes.y > 0)
            {
                A: for(int i = 1; i <= Math.min(indexes.x, indexes.y); i++)
                {
                    if(board[indexes.x - i][indexes.y - i].color != null && board[indexes.x - i][indexes.y - i].color.equalsIgnoreCase("w"))
                    {
                        break A;
                    }

                    if(board[indexes.x - i][indexes.y - i].piece == null || board[indexes.x - i][indexes.y - i].color.equalsIgnoreCase("b"))
                    {
                        moves.add(new Point(indexes.x - i, indexes.y - i));

                        if(board[indexes.x - i][indexes.y - i].color != null && board[indexes.x - i][indexes.y - i].color.equalsIgnoreCase("b"))
                        {
                            break A;
                        }
                    }
                }
            }

            //checking right up
            if(indexes.x < board[0].length - 1 && indexes.y > 0)
            {
                A: for(int i = 1; i <= Math.min(board[0].length - 1 - indexes.x, indexes.y); i++)
                {
                    if(board[indexes.x + i][indexes.y - i].color != null && board[indexes.x + i][indexes.y - i].color.equalsIgnoreCase("w"))
                    {
                        break A;
                    }

                    if(board[indexes.x + i][indexes.y - i].piece == null || board[indexes.x + i][indexes.y - i].color.equalsIgnoreCase("b"))
                    {
                        moves.add(new Point(indexes.x + i, indexes.y - i));

                        if(board[indexes.x + i][indexes.y - i].color != null && board[indexes.x + i][indexes.y - i].color.equalsIgnoreCase("b"))
                        {
                            break A;
                        }
                    }
                }
            }

            //checking left down
            if(indexes.x > 0 && indexes.y < board.length - 1)
            {
                A: for(int i = 1; i <= Math.min(indexes.x, board.length - 1 - indexes.y); i++)
                {
                    if(board[indexes.x - i][indexes.y + i].color != null && board[indexes.x - i][indexes.y + i].color.equalsIgnoreCase("w"))
                    {
                        break A;
                    }

                    if(board[indexes.x - i][indexes.y + i].piece == null || board[indexes.x - i][indexes.y + i].color.equalsIgnoreCase("b"))
                    {
                        moves.add(new Point(indexes.x - i, indexes.y + i));

                        if(board[indexes.x - i][indexes.y + i].color != null && board[indexes.x - i][indexes.y + i].color.equalsIgnoreCase("b"))
                        {
                            break A;
                        }
                    }
                }
            }

            //checking right down
            if(indexes.x < board[0].length - 1 && indexes.y < board.length - 1)
            {
                A: for(int i = 1; i <= Math.min(board[0].length - 1 - indexes.x, board.length - 1 - indexes.y); i++)
                {
                    if(board[indexes.x + i][indexes.y + i].color != null && board[indexes.x + i][indexes.y + i].color.equalsIgnoreCase("w"))
                    {
                        break A;
                    }

                    if(board[indexes.x + i][indexes.y + i].piece == null || board[indexes.x + i][indexes.y + i].color.equalsIgnoreCase("b"))
                    {
                        moves.add(new Point(indexes.x + i, indexes.y + i));

                        if(board[indexes.x + i][indexes.y + i].color != null && board[indexes.x + i][indexes.y + i].color.equalsIgnoreCase("b"))
                        {
                            break A;
                        }
                    }
                }
            }
        }
        else
        {
            //checking left up
            if(indexes.x > 0 && indexes.y > 0)
            {
                A: for(int i = 1; i <= Math.min(indexes.x, indexes.y); i++)
                {
                    if(board[indexes.x - i][indexes.y - i].color != null && board[indexes.x - i][indexes.y - i].color.equalsIgnoreCase("b"))
                    {
                        break A;
                    }

                    if(board[indexes.x - i][indexes.y - i].piece == null || board[indexes.x - i][indexes.y - i].color.equalsIgnoreCase("w"))
                    {
                        moves.add(new Point(indexes.x - i, indexes.y - i));

                        if(board[indexes.x - i][indexes.y - i].color != null && board[indexes.x - i][indexes.y - i].color.equalsIgnoreCase("w"))
                        {
                            break A;
                        }
                    }
                }
            }

            //checking right up
            if(indexes.x < board[0].length - 1 && indexes.y > 0)
            {
                A: for(int i = 1; i <= Math.min(board[0].length - 1 - indexes.x, indexes.y); i++)
                {
                    if(board[indexes.x + i][indexes.y - i].color != null && board[indexes.x + i][indexes.y - i].color.equalsIgnoreCase("b"))
                    {
                        break A;
                    }

                    if(board[indexes.x + i][indexes.y - i].piece == null || board[indexes.x + i][indexes.y - i].color.equalsIgnoreCase("w"))
                    {
                        moves.add(new Point(indexes.x + i, indexes.y - i));

                        if(board[indexes.x + i][indexes.y - i].color != null && board[indexes.x + i][indexes.y - i].color.equalsIgnoreCase("w"))
                        {
                            break A;
                        }
                    }
                }
            }

            //checking left down
            if(indexes.x > 0 && indexes.y < board.length - 1)
            {
                A: for(int i = 1; i <= Math.min(indexes.x, board.length - 1 - indexes.y); i++)
                {
                    if(board[indexes.x - i][indexes.y + i].color != null && board[indexes.x - i][indexes.y + i].color.equalsIgnoreCase("b"))
                    {
                        break A;
                    }

                    if(board[indexes.x - i][indexes.y + i].piece == null || board[indexes.x - i][indexes.y + i].color.equalsIgnoreCase("w"))
                    {
                        moves.add(new Point(indexes.x - i, indexes.y + i));

                        if(board[indexes.x - i][indexes.y + i].color != null && board[indexes.x - i][indexes.y + i].color.equalsIgnoreCase("w"))
                        {
                            break A;
                        }
                    }
                }
            }

            //checking right down
            if(indexes.x < board[0].length - 1 && indexes.y < board.length - 1)
            {
                A: for(int i = 1; i <= Math.min(board[0].length - 1 - indexes.x, board.length - 1 - indexes.y); i++)
                {
                    if(board[indexes.x + i][indexes.y + i].color != null && board[indexes.x + i][indexes.y + i].color.equalsIgnoreCase("b"))
                    {
                        break A;
                    }

                    if(board[indexes.x + i][indexes.y + i].piece == null || board[indexes.x + i][indexes.y + i].color.equalsIgnoreCase("w"))
                    {
                        moves.add(new Point(indexes.x + i, indexes.y + i));

                        if(board[indexes.x + i][indexes.y + i].color != null && board[indexes.x + i][indexes.y + i].color.equalsIgnoreCase("w"))
                        {
                            break A;
                        }
                    }
                }
            }
        }

        return moves;
    }
}