package PieceCode;

import java.awt.*;
import java.util.ArrayList;

public class Queen 
{
    private Bishop bishop;
    private Rook rook;

    public Queen()
    {
        bishop = new Bishop();
        rook = new Rook();
    }

    public ArrayList<Point> moves(Piece piece, Piece[][] board, Point indexes)
    {
        ArrayList<Point> moves = bishop.moves(piece, board, indexes);

        moves.addAll(rook.moves(piece, board, indexes));

        return moves;
    }
}