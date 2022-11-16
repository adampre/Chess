package PieceCode;

import java.io.File;
import java.io.IOException;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.Point;

public class Piece
{
    public Image piece;

    public String color;
    public String pieceType;

    public Point position;
    public int dimensions;

    public boolean isClicked;
    public int amountMoved;

    public Piece(File image, Point point, int dimensions)
    {
        if(image != null)
        {
            try
            {
                piece = ImageIO.read(image);
                
                color = image.getName().substring(0, 1);
                pieceType = image.getName().substring(1, 2);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        
        this.position = point;
        this.dimensions = dimensions;

        isClicked = false;
        amountMoved = 0;
    }

    public Piece(Point point, Image image, int dimensions, String color, String pieceType)
    {
        if(image != null)
        {
            piece = image;

            this.color = color;
            this.pieceType = pieceType;
        }
        
        this.position = point;
        this.dimensions = dimensions;

        isClicked = false;

        amountMoved = 0;
    }
}