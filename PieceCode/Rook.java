package PieceCode;

import java.awt.*;
import java.util.ArrayList;

public class Rook 
{
    public ArrayList<Point> moves(Piece piece, Piece[][] board, Point indexes)
    {
        ArrayList<Point> moves = new ArrayList<Point>();

        if(piece.color.equalsIgnoreCase("w"))
        {
            //checking left
            if(indexes.x > 0)
            {
                A: for(int i = indexes.x - 1; i >= 0; i--)
                {
                    if(board[i][indexes.y].color != null && board[i][indexes.y].color.equalsIgnoreCase("w"))
                    {
                        break A;
                    }

                    if(board[i][indexes.y].piece == null || board[i][indexes.y].color.equalsIgnoreCase("b"))
                    {
                        moves.add(new Point(i, indexes.y));

                        if(board[i][indexes.y].color != null && board[i][indexes.y].color.equalsIgnoreCase("b"))
                        {
                            break A;
                        }
                    }
                }
            }

            //checking right
            if(indexes.x < board[0].length - 1)
            {
                A: for(int i = indexes.x + 1; i < board[0].length; i++)
                {
                    if(board[i][indexes.y].color != null && board[i][indexes.y].color.equalsIgnoreCase("w"))
                    {
                        break A;
                    }

                    if(board[i][indexes.y].piece == null || board[i][indexes.y].color.equalsIgnoreCase("b"))
                    {
                        moves.add(new Point(i, indexes.y));

                        if(board[i][indexes.y].color != null && board[i][indexes.y].color.equalsIgnoreCase("b"))
                        {
                            break A;
                        }
                    }
                }
            }
            
            //checking down
            if(indexes.y < board.length - 1)
            {
                A: for(int i = indexes.y + 1; i < board.length; i++)
                {
                    if(board[indexes.x][i].color != null && board[indexes.x][i].color.equalsIgnoreCase("w"))
                    {
                        break A;
                    }

                    if(board[indexes.x][i].piece == null || board[indexes.x][i].color.equalsIgnoreCase("b"))
                    {
                        moves.add(new Point(indexes.x, i));

                        if(board[indexes.x][i].color != null && board[indexes.x][i].color.equalsIgnoreCase("b"))
                        {
                            break A;
                        }
                    }
                }
            }

            //checking up
            if(indexes.y > 0)
            {
                A: for(int i = indexes.y - 1; i >= 0; i--)
                {
                    if(board[indexes.x][i].color != null && board[indexes.x][i].color.equalsIgnoreCase("w"))
                    {
                        break A;
                    }

                    if(board[indexes.x][i].piece == null || board[indexes.x][i].color.equalsIgnoreCase("b"))
                    {
                        moves.add(new Point(indexes.x, i));

                        if(board[indexes.x][i].color != null && board[indexes.x][i].color.equalsIgnoreCase("b"))
                        {
                            break A;
                        }
                    }
                }
            }
        }
        else
        {
            if(indexes.x > 0)
            {
                A: for(int i = indexes.x - 1; i >= 0; i--)
                {
                    if(board[i][indexes.y].color != null && board[i][indexes.y].color.equalsIgnoreCase("b"))
                    {
                        break A;
                    }

                    if(board[i][indexes.y].piece == null || board[i][indexes.y].color.equalsIgnoreCase("w"))
                    {
                        moves.add(new Point(i, indexes.y));

                        if(board[i][indexes.y].color != null && board[i][indexes.y].color.equalsIgnoreCase("w"))
                        {
                            break A;
                        }
                    }
                }
            }

            //checking right
            if(indexes.x < board[0].length - 1)
            {
                A: for(int i = indexes.x + 1; i < board[0].length; i++)
                {
                    if(board[i][indexes.y].color != null && board[i][indexes.y].color.equalsIgnoreCase("b"))
                    {
                        break A;
                    }

                    if(board[i][indexes.y].piece == null || board[i][indexes.y].color.equalsIgnoreCase("w"))
                    {
                        moves.add(new Point(i, indexes.y));

                        if(board[i][indexes.y].color != null && board[i][indexes.y].color.equalsIgnoreCase("w"))
                        {
                            break A;
                        }
                    }
                }
            }
            
            //checking down
            if(indexes.y < board.length - 1)
            {
                A: for(int i = indexes.y + 1; i < board.length; i++)
                {
                    if(board[indexes.x][i].color != null && board[indexes.x][i].color.equalsIgnoreCase("b"))
                    {
                        break A;
                    }

                    if(board[indexes.x][i].piece == null || board[indexes.x][i].color.equalsIgnoreCase("w"))
                    {
                        moves.add(new Point(indexes.x, i));

                        if(board[indexes.x][i].color != null && board[indexes.x][i].color.equalsIgnoreCase("w"))
                        {
                            break A;
                        }
                    }
                }
            }

            //checking up
            if(indexes.y > 0)
            {
                A: for(int i = indexes.y - 1; i >= 0; i--)
                {
                    if(board[indexes.x][i].color != null && board[indexes.x][i].color.equalsIgnoreCase("b"))
                    {
                        break A;
                    }

                    if(board[indexes.x][i].piece == null || board[indexes.x][i].color.equalsIgnoreCase("w"))
                    {
                        moves.add(new Point(indexes.x, i));

                        if(board[indexes.x][i].color != null && board[indexes.x][i].color.equalsIgnoreCase("w"))
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