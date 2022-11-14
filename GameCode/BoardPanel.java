import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

import PieceCode.Piece;

public class BoardPanel extends JPanel implements MouseListener, MouseMotionListener
{
    private final String PIECETEMPLATE = "rnbqkbnr/pppppppp/********/********/********/********/PPPPPPPP/RNBQKBNR";

    private Color[][] colorBoard;
    private Color darkSquareColor;
    private Color lightSquareColor;

    private Piece[][] board;

    private int dimensions;
    private int squareSize;

    private Game game;

    private String currentPlayer;

    private boolean gameHasStarted;
    
    public BoardPanel(int dimension)
    {
        board = new Piece[8][8];

        colorBoard = new Color[8][8];

        darkSquareColor = new Color(169, 122, 101);
        lightSquareColor = new Color(241, 217, 192);

        dimensions = dimension;
        squareSize = (dimensions / board.length) - 5;

        game = new Game();

        currentPlayer = "w";

        gameHasStarted = false;

        initBoard();

        gameHasStarted = true;
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponents(g);

        drawColorBoard(g);

        Piece clickedPiece = null;

        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(board[j][i].piece != null)
                {
                    g.drawImage(board[j][i].piece, board[j][i].position.x, board[j][i].position.y, board[j][i].dimensions, board[j][i].dimensions, this);
                }

                if(board[j][i].piece != null && board[j][i].isClicked)
                {
                    clickedPiece = board[j][i];
                }
            }
        }

        if(gameHasStarted && clickedPiece != null)
        {
            drawMoves(g, clickedPiece);
        }
    }

    private void drawColorBoard(Graphics g)
    {
        int count = 0; 

        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(count % 2 == 0)
                {
                    g.setColor(lightSquareColor);

                    colorBoard[j][i] = lightSquareColor;
                }
                else
                {
                    g.setColor(darkSquareColor);

                    colorBoard[j][i] = darkSquareColor;
                }

                g.fillRect(j * squareSize, i * squareSize, squareSize, squareSize);

                count++;
            }

            count++;
        }
    }

    private void drawMoves(Graphics g, Piece piece)
    {
        ArrayList<Point> moves = game.listOfMoves(piece, board);

        if(!piece.color.equalsIgnoreCase(currentPlayer)) return;

        for(int i = 0; i < moves.size(); i++)
        {
            g.setColor(Color.GRAY);

            g.fillOval((moves.get(i).x * squareSize) + (squareSize / 3), (moves.get(i).y * squareSize) + (squareSize / 3), squareSize / 3, squareSize / 3);
        }
    }

    private void initBoard()
    {
        int count = 0;

        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(getFileName(PIECETEMPLATE.substring(count, count + 1)) != null)
                {
                    board[j][i] = new Piece(new File(getFileName(PIECETEMPLATE.substring(count, count + 1))), new Point(j * squareSize, i * squareSize), squareSize);
                }
                else 
                {
                    board[j][i] = new Piece(null, new Point(j * squareSize, i * squareSize), squareSize);
                } 

                count++;
            }

            count++;
        }

        repaint();
    }

    private String getFileName(String pieceName)
    {
        String fileName = "Pieces/";

        if(pieceName.toLowerCase().equals(pieceName) && !pieceName.equals("*"))
        {
            fileName += "b";
        }
        else if(pieceName.toUpperCase().equals(pieceName) && !pieceName.equals("*"))
        {
            fileName += "w";
        }
        else
        {
            return null;
        }

        fileName += pieceName.toLowerCase();
        fileName += ".png";

        return fileName;
    }

    private Point indexOfClick(Point point)
    {
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if((board[j][i].position.x < point.x + 8 && point.x < board[j][i].position.x + board[j][i].dimensions + 8) && 
                   (board[j][i].position.y + 30 < point.y && point.y < board[j][i].position.y + board[j][i].dimensions + 30))
                {
                    return new Point(j, i);
                }
            }
        }

        return null;
    }

    private void switchIsClicked(Point indexes)
    {
        if(board[indexes.x][indexes.y].isClicked)
        {
            board[indexes.x][indexes.y].isClicked = false;

            return;
        }

        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(board[j][i].piece != null && board[j][i].color.equalsIgnoreCase(currentPlayer) && board[j][i].isClicked && game.isLegalMove(board[j][i], indexes, board))
                {
                    checkSpecialMove(indexes, board[j][i]);

                    int newMove = board[j][i].amountMoved + 1;

                    board[j][i].isClicked = false;

                    board[indexes.x][indexes.y] = new Piece(new Point(indexes.x * board[j][i].dimensions, indexes.y * board[j][i].dimensions), board[j][i].piece, board[j][i].dimensions, board[j][i].color, board[j][i].pieceType);
                    board[indexes.x][indexes.y].amountMoved = newMove;

                    board[j][i] = new Piece(null, board[j][i].position, board[j][i].dimensions);

                    switchCurrentPlayer();

                    return;
                }

                if(board[j][i].isClicked)
                {
                    board[j][i].isClicked = false;
                }
            }
        }

        board[indexes.x][indexes.y].isClicked = true;
    }

    private void checkSpecialMove(Point indexes, Piece piece)
    {
        //en passant
        if(piece.pieceType.equalsIgnoreCase("p") && indexes.y == 2 && board[indexes.x][indexes.y].piece == null && board[indexes.x][indexes.y + 1].piece != null && board[indexes.x][indexes.y + 1].color.equalsIgnoreCase("b") && board[indexes.x][indexes.y + 1].pieceType.equalsIgnoreCase("p") && board[indexes.x][indexes.y + 1].amountMoved < 2)
        {
            board[indexes.x][indexes.y + 1] = new Piece(null, board[indexes.x][indexes.y + 1].position, board[indexes.x][indexes.y + 1].dimensions);
        }

        if(piece.pieceType.equalsIgnoreCase("p") && indexes.y == 5 && board[indexes.x][indexes.y].piece == null && board[indexes.x][indexes.y - 1].piece != null && board[indexes.x][indexes.y - 1].color.equalsIgnoreCase("w") && board[indexes.x][indexes.y - 1].pieceType.equalsIgnoreCase("p") && board[indexes.x][indexes.y - 1].amountMoved < 2)
        {
            board[indexes.x][indexes.y - 1] = new Piece(null, board[indexes.x][indexes.y - 1].position, board[indexes.x][indexes.y - 1].dimensions);
        }

        //short castle
        if(piece.pieceType.equalsIgnoreCase("k") && indexes.x == board[0].length - 2 && indexes.y == board.length - 1)
        {
            board[indexes.x - 1][indexes.y] = new Piece(new Point((indexes.x - 1) * board[board[0].length - 1][board.length - 1].dimensions, indexes.y * board[board[0].length - 1][board.length - 1].dimensions), board[board[0].length - 1][board.length - 1].piece, board[board[0].length - 1][board.length - 1].dimensions, board[board[0].length - 1][board.length - 1].color, board[board[0].length - 1][board.length - 1].pieceType);
            board[board[0].length - 1][board.length - 1] = new Piece(null, board[board[0].length - 1][board.length - 1].position, board[board[0].length - 1][board.length - 1].dimensions);
        }

        if(piece.pieceType.equalsIgnoreCase("k") && indexes.x == board[0].length - 2 && indexes.y == 0)
        {
            board[indexes.x - 1][indexes.y] = new Piece(new Point((indexes.x - 1) * board[board[0].length - 1][0].dimensions, indexes.y * board[board[0].length - 1][0].dimensions), board[board[0].length - 1][0].piece, board[board[0].length - 1][0].dimensions, board[board[0].length - 1][0].color, board[board[0].length - 1][0].pieceType);
            board[board[0].length - 1][0] = new Piece(null, board[board[0].length - 1][0].position, board[board[0].length - 1][0].dimensions);
        }

        //long castle
        if(piece.pieceType.equalsIgnoreCase("k") && indexes.x == 2 && indexes.y == board.length - 1)
        {
            board[indexes.x + 1][indexes.y] = new Piece(new Point((indexes.x + 1) * board[0][board.length - 1].dimensions, indexes.y * board[0][board.length - 1].dimensions), board[0][board.length - 1].piece, board[0][board.length - 1].dimensions, board[0][board.length - 1].color, board[0][board.length - 1].pieceType);
            board[0][board.length - 1] = new Piece(null, board[0][board.length - 1].position, board[0][board.length - 1].dimensions);
        }

        if(piece.pieceType.equalsIgnoreCase("k") && indexes.x == 2 && indexes.y == 0)
        {
            board[indexes.x + 1][indexes.y] = new Piece(new Point((indexes.x + 1) * board[0][0].dimensions, indexes.y * board[0][0].dimensions), board[0][0].piece, board[0][0].dimensions, board[0][0].color, board[0][0].pieceType);
            board[0][0] = new Piece(null, board[0][0].position, board[0][0].dimensions);
        }

        //fix pawn promotion        
        //pawn promotion
        if(piece.pieceType.equalsIgnoreCase("p") && indexes.y == 0)
        {
            int newMove = piece.amountMoved + 1;
            
            String answer = "";

            do
            {
                answer = JOptionPane.showInputDialog(this, "What piece do you want to promote? (q = Queen, b = Bishop, n = Knight, r = Rook)");
            }
            while(!answer.equalsIgnoreCase("q") && !answer.equalsIgnoreCase("n") && !answer.equalsIgnoreCase("b") && !answer.equalsIgnoreCase("r"));

            //problem: pawn isnt actually becoming the piece
            board[indexes.x][indexes.y] = new Piece(new File("Pieces/w" + answer.toLowerCase() + ".png"), board[indexes.x][indexes.y].position, board[indexes.x][indexes.y].dimensions);
            board[indexes.x][indexes.y].amountMoved = newMove;
        }

        if(piece.pieceType.equalsIgnoreCase("p") && indexes.y == board.length - 1)
        {
            int newMove = piece.amountMoved + 1;
            
            String answer = "";

            answer = JOptionPane.showInputDialog(null, "What piece do you want to promote? (q = Queen, b = Bishop, n = Knight, r = Rook)");

            //
            board[indexes.x][indexes.y] = new Piece(new File("Pieces/b" + answer.toLowerCase() + ".png"), new Point(indexes.x * piece.dimensions, indexes.y * piece.dimensions), piece.dimensions);
            board[indexes.x][indexes.y].amountMoved = newMove;
        }
    }

    private void switchCurrentPlayer()
    {
        if(currentPlayer.equalsIgnoreCase("w"))
        {
            currentPlayer = "b";

            return;
        }

        currentPlayer = "w";
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        Point indexes = indexOfClick(e.getPoint());

        if(indexes != null)
        {
            switchIsClicked(indexes);

            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
}