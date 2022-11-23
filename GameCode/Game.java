import java.awt.*;
import java.util.ArrayList;

import PieceCode.Piece;

import PieceCode.Pawn;
import PieceCode.Knight;
import PieceCode.Bishop;
import PieceCode.Rook;
import PieceCode.King;
import PieceCode.Queen;

public class Game
{
    private Bishop bishop;
    private King king;
    private Knight knight;
    private Pawn pawn;
    private Queen queen;
    private Rook rook;

    public boolean isInCheck;

    public Game()
    {
        bishop = new Bishop();
        king = new King();
        knight = new Knight();
        pawn = new Pawn();
        queen = new Queen();
        rook = new Rook();

        isInCheck = false;
    }

    public boolean isLegalMove(Piece piece, Point move, Piece[][] board)
    {
        ArrayList<Point> moves = listOfMoves(piece, board);

        for(int i = 0; i < moves.size(); i++)
        {
            if(moves.get(i).equals(move))
            {
                return true;
            }
        }

        return false;
    }

    public ArrayList<Point> listOfMoves(Piece piece, Piece[][] board)
    {
        ArrayList<Point> moves = new ArrayList<Point>();

        Point indexes = indexOf(piece, board);

        if(indexes == null) return moves;

        if(piece.pieceType.equalsIgnoreCase("b"))
        {
            moves = bishop.moves(piece, board, indexes);
        }
        else if(piece.pieceType.equalsIgnoreCase("k"))
        {
            moves = king.moves(piece, board, indexes);
        }
        else if(piece.pieceType.equalsIgnoreCase("n"))
        {
            moves = knight.moves(piece, board, indexes);
        }
        else if(piece.pieceType.equalsIgnoreCase("p"))
        {
            moves = pawn.moves(piece, board, indexes);
        }
        else if(piece.pieceType.equalsIgnoreCase("q"))
        {
            moves = queen.moves(piece, board, indexes);
        }
        else if(piece.pieceType.equalsIgnoreCase("r"))
        {
            moves = rook.moves(piece, board, indexes);
        }

        // if(isInCheck)
        // {
        //     validateCheckMoves(moves, piece, board);
        // }

        return moves;
    }

    private ArrayList<Point> validateCheckMoves(ArrayList<Point> moves, Piece piece, Piece[][] board)
    {
        

        return moves;
    }

    public boolean isPinned(Piece[][] oldBoard, Point indexes, Point move)
    {
        if(oldBoard[indexes.x][indexes.y].piece == null)
        {
            return false;
        }

        Piece[][] board = new Piece[oldBoard.length][oldBoard[0].length];

        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                board[j][i] = new Piece(new Point(j * oldBoard[j][i].dimensions, i * oldBoard[j][i].dimensions), oldBoard[j][i].piece, oldBoard[j][i].dimensions, oldBoard[j][i].color, oldBoard[j][i].pieceType);
            }
        }

        ArrayList<Point> moves = new ArrayList<Point>();

        board[move.x][move.y] = new Piece(new Point(move.x * oldBoard[indexes.x][indexes.y].dimensions, move.y * oldBoard[indexes.x][indexes.y].dimensions), oldBoard[indexes.x][indexes.y].piece, oldBoard[indexes.x][indexes.y].dimensions, oldBoard[indexes.x][indexes.y].color, oldBoard[indexes.x][indexes.y].pieceType);
        board[indexes.x][indexes.y] = new Piece(null, oldBoard[indexes.x][indexes.y].position, oldBoard[indexes.x][indexes.y].dimensions);

        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(board[j][i].piece != null && !board[j][i].color.equalsIgnoreCase(board[move.x][move.y].color))
                {
                    if(board[j][i].pieceType.equalsIgnoreCase("b"))
                    {
                        moves.addAll(bishop.moves(board[j][i], board, new Point(j, i)));
                    }
                    else if(board[j][i].pieceType.equalsIgnoreCase("q"))
                    {
                        moves.addAll(queen.moves(board[j][i], board, new Point(j, i)));
                    }
                    else if(board[j][i].pieceType.equalsIgnoreCase("r"))
                    {
                        moves.addAll(rook.moves(board[j][i], board, new Point(j, i)));
                    }
                }
            }
        }
        
        return moves.contains(indexOfKing(board, board[move.x][move.y].color));
    }

    private Point indexOf(Piece piece, Piece[][] board)
    {
        if(piece == null) return null;

        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(board[j][i].equals(piece))
                {
                    return new Point(j, i);
                }
            }
        }

        return null;
    }

    private Point indexOfKing(Piece[][] board, String currentColor)
    {
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(board[j][i].piece != null && board[j][i].pieceType.equalsIgnoreCase("k") && board[j][i].color.equalsIgnoreCase(currentColor))
                {
                    return new Point(j, i);
                }
            }
        }

        return null;
    }

    private Point indexOfOpponentKing(Piece[][] board, String currentColor)
    {
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(board[j][i].piece != null && board[j][i].pieceType.equalsIgnoreCase("k") && !board[j][i].color.equalsIgnoreCase(currentColor))
                {
                    return new Point(j, i);
                }
            }
        }

        return null;
    }

    public boolean check(Piece[][] board, String currentColor)
    {
        ArrayList<Point> moves = new ArrayList<Point>();

        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(board[j][i].piece != null && !board[j][i].color.equalsIgnoreCase(currentColor))
                {
                    moves.addAll(listOfMoves(board[j][i], board));
                }
            }
        }

        return moves.contains(indexOfKing(board, currentColor));
    }

    public boolean discoveredCheck(Piece[][] board, String currentColor)
    {
        ArrayList<Point> moves = new ArrayList<Point>();

        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(board[j][i].piece != null && board[j][i].color.equalsIgnoreCase(currentColor))
                {
                    moves.addAll(listOfMoves(board[j][i], board));
                }
            }
        }

        return moves.contains(indexOfOpponentKing(board, currentColor));
    }
}