import java.awt.Point;

import PieceCode.Piece; 

public class Move
{
    private final String COLUMN = "abcdefgh";
    private final String ROW = "87654321";

    public Piece piece;

    public Point oldPosition;
    public Point newPosition;

    public Move(Piece piece, Point oldPosition, Point newPosition)
    {
        this.piece = piece;

        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
    }

    public String getMoveNotation()
    {
        if(piece.pieceType.equalsIgnoreCase("p"))
        {
            return COLUMN.substring(newPosition.x, newPosition.x + 1) + ROW.substring(newPosition.y, newPosition.y + 1);
        }

        if(piece.pieceType.equalsIgnoreCase("k"))
        {
            if(piece.color.equalsIgnoreCase("w"))
            {
                //long castle
                if(newPosition.x == oldPosition.x - 2)
                {
                    return "0-0-0";
                }

                //short castle
                if(newPosition.x == oldPosition.x + 2)
                {
                    return "0-0";
                }
            }

            if(piece.color.equalsIgnoreCase("b"))
            {
                //long castle
                if(newPosition.x == oldPosition.x + 2)
                {
                    return "O-O-O";
                }

                //short castle
                if(newPosition.x == oldPosition.x - 2)
                {
                    return "O-O";
                }
            }
        }

        return piece.pieceType.toUpperCase() + COLUMN.substring(newPosition.x, newPosition.x + 1) + ROW.substring(newPosition.y, newPosition.y + 1);
    }
}