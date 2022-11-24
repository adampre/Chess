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

        removePins(board, indexes, moves);

        if(isInCheck)
        {
            validateCheckMoves(board, moves, indexes);
        }

        return moves;
    }

    private void validateCheckMoves(Piece[][] oldBoard, ArrayList<Point> moves, Point indexes)
    {
        Piece[][] board = new Piece[oldBoard.length][oldBoard[0].length];

        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                board[j][i] = new Piece(new Point(j * oldBoard[j][i].dimensions, i * oldBoard[j][i].dimensions), oldBoard[j][i].piece, oldBoard[j][i].dimensions, oldBoard[j][i].color, oldBoard[j][i].pieceType);
            }
        }

        for(int i = 0; i < moves.size(); i++)
        {
            board[moves.get(i).x][moves.get(i).y] = new Piece(new Point(moves.get(i).x * board[indexes.x][indexes.y].dimensions, moves.get(i).y * board[indexes.x][indexes.y].dimensions), board[indexes.x][indexes.y].piece, board[indexes.x][indexes.y].dimensions, board[indexes.x][indexes.y].color, board[indexes.x][indexes.y].pieceType);
            board[indexes.x][indexes.y] = new Piece(null, board[indexes.x][indexes.y].position, board[indexes.x][indexes.y].dimensions);

            ArrayList<Point> opponentMoves = new ArrayList<Point>();

            for(int j = 0; j < board.length; j++)
            {
                for(int k = 0; k < board[j].length; k++)
                {
                    if(board[k][j].piece != null && !board[k][j].color.equalsIgnoreCase(board[moves.get(i).x][moves.get(i).y].color))
                    {
                        if(board[k][j].pieceType.equalsIgnoreCase("b"))
                        {
                            opponentMoves.addAll(bishop.moves(board[k][j], board, new Point(k, j)));
                        }
                        else if(board[k][j].pieceType.equalsIgnoreCase("q"))
                        {
                            opponentMoves.addAll(queen.moves(board[k][j], board, new Point(k, j)));
                        }
                        else if(board[k][j].pieceType.equalsIgnoreCase("r"))
                        {
                            opponentMoves.addAll(rook.moves(board[k][j], board, new Point(k, j)));
                        }
                    }                  
                }
            }

            if(opponentMoves.contains(indexOfKing(board, board[moves.get(i).x][moves.get(i).y].color)))
            {
                moves.remove(i);

                i--;

                if(moves.size() == 0)
                {
                    return;
                }
            }
        }
    }

    private void removePins(Piece[][] board, Point indexes, ArrayList<Point> moves)
    {
        for(int i = 0; i < moves.size(); i++)
        {
            if(isPinned(board, indexes, moves.get(i)))
            {
                moves.remove(i);

                i--;
            }
        }
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