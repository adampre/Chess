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

    public Game()
    {
        bishop = new Bishop();
        king = new King();
        knight = new Knight();
        pawn = new Pawn();
        queen = new Queen();
        rook = new Rook();
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

        if(indexes == null)
        {
            return moves;
        }

        if(piece.pieceType.equalsIgnoreCase("b"))
        {
            moves = bishop.moves(piece, board, indexes);
        }

        if(piece.pieceType.equalsIgnoreCase("k"))
        {
            moves = king.moves(piece, board, indexes);
        }

        if(piece.pieceType.equalsIgnoreCase("n"))
        {
            moves = knight.moves(piece, board, indexes);
        }

        if(piece.pieceType.equalsIgnoreCase("p"))
        {
            moves = pawn.moves(piece, board, indexes);
        }

        if(piece.pieceType.equalsIgnoreCase("q"))
        {
            moves = queen.moves(piece, board, indexes);
        }

        if(piece.pieceType.equalsIgnoreCase("r"))
        {
            moves = rook.moves(piece, board, indexes);
        }

        return moves;
    }

    private Point indexOf(Piece piece, Piece[][] board)
    {
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
}