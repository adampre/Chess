import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import PieceCode.Piece;

public class BoardPanel extends JPanel implements MouseListener, MouseMotionListener
{
    public DisplayPanel displayPanel;

    private final String PIECETEMPLATE = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    private Color[][] colorBoard;
    private Color darkSquareColor;
    private Color lightSquareColor;

    private Piece[][] board;

    private int dimensions;
    private int squareSize;

    private Game game;

    private String currentPlayer;

    private int halfMoves;
    private int fullMoves;
    
    public BoardPanel(int width, int height, Color backgroundColor)
    {
        this.setLayout(new BorderLayout());

        board = new Piece[8][8];
        colorBoard = new Color[8][8];

        darkSquareColor = new Color(169, 122, 101);
        lightSquareColor = new Color(241, 217, 192);

        dimensions = height;
        squareSize = (dimensions / board.length) - 5;

        game = new Game();

        currentPlayer = PIECETEMPLATE.substring(PIECETEMPLATE.indexOf(" ") + 1, PIECETEMPLATE.indexOf(" ") + 2);    

        displayPanel = new DisplayPanel(width, height, backgroundColor);
        this.add(displayPanel, BorderLayout.EAST);

        displayPanel.checkDisplay.setText(" ");

        halfMoves = Integer.parseInt(PIECETEMPLATE.substring(PIECETEMPLATE.indexOf(" ", PIECETEMPLATE.indexOf(" ", PIECETEMPLATE.indexOf(" ") + 3) + 1) + 1, PIECETEMPLATE.indexOf(" ", PIECETEMPLATE.indexOf(" ", PIECETEMPLATE.indexOf(" ", PIECETEMPLATE.indexOf(" ") + 3) + 1) + 1)));
        fullMoves = Integer.parseInt(PIECETEMPLATE.substring(PIECETEMPLATE.indexOf(" ", PIECETEMPLATE.indexOf(" ", PIECETEMPLATE.indexOf(" ", PIECETEMPLATE.indexOf(" ") + 3) + 1) + 1) + 1, PIECETEMPLATE.length()));

        gameInit();
    }

    public void gameInit()
    {
        initBoard();

        int indexBeforeCastle = PIECETEMPLATE.indexOf(" ") + 3;
        int indexAfterCastle = PIECETEMPLATE.indexOf(" ", PIECETEMPLATE.indexOf(" ") + 3);

        initCastling(indexBeforeCastle, indexAfterCastle);

        initEnPassant(indexAfterCastle + 1);
    }

    private void initCastling(int indexBeforeCastle, int indexAfterCastle)
    {
        if(indexAfterCastle - indexBeforeCastle == 4) return;

        String nonCastling = "KQkq";

        for(int i = indexBeforeCastle; i < indexAfterCastle; i++)
        {
            switch(PIECETEMPLATE.charAt(i))
            {
                case 'K':
                nonCastling = nonCastling.replaceFirst("K", "");
                break;

                case 'Q': 
                nonCastling = nonCastling.replaceFirst("Q", "");
                break;

                case 'k': 
                nonCastling = nonCastling.replaceFirst("k", "");
                break;

                case 'q': 
                nonCastling = nonCastling.replaceFirst("q", "");
                break;
            }
        }

        for(int i = 0; i < nonCastling.length(); i++)
        {
            switch(nonCastling.charAt(i))
            {
                case 'K':
                board[board[0].length - 1][board.length - 1].amountMoved++;
                break;

                case 'Q': 
                board[0][board.length - 1].amountMoved++;
                break;

                case 'k': 
                board[board[0].length - 1][0].amountMoved++;
                break;

                case 'q': 
                board[0][0].amountMoved++;
                break;
            }
        }
    }

    private void initEnPassant(int indexAfterCastle)
    {
        if(PIECETEMPLATE.substring(indexAfterCastle, indexAfterCastle + 1).equalsIgnoreCase("-")) return;

        String files = "abcdefgh";

        switch(Integer.parseInt(PIECETEMPLATE.substring(indexAfterCastle + 1, indexAfterCastle + 2)))
        {
            case 3:
                board[files.indexOf(PIECETEMPLATE.substring(indexAfterCastle, indexAfterCastle + 1))][4].amountMoved = 1;
                return;

            case 6:
                board[files.indexOf(PIECETEMPLATE.substring(indexAfterCastle, indexAfterCastle + 1))][3].amountMoved = 1;
                return;
        }
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

        if(clickedPiece != null)
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
        for(int i = 0, index = 0; i < PIECETEMPLATE.indexOf(" "); i++, index++)
        {
            if(PIECETEMPLATE.substring(i, i + 1).equalsIgnoreCase("/")) 
            {
                i++;
            }

            try
            {
                index += Integer.parseInt(PIECETEMPLATE.substring(i, i + 1)) - 1;
            }
            catch(NumberFormatException e)
            {
                board[index % board[0].length][index / board.length] = new Piece(new File(getFileName(PIECETEMPLATE.substring(i, i + 1))), new Point((index % board[0].length) * squareSize, (index / board.length) * squareSize), squareSize);
            }
        }

        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(board[j][i] == null)
                {
                    board[j][i] = new Piece(null, new Point(j * squareSize, i * squareSize), squareSize);
                }
            }
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
                    updateHalfMove(new Point(j, i), indexes);

                    checkSpecialMove(indexes, board[j][i]);

                    udpatePawns();
                   
                    switchPiece(indexes, new Point(j, i));

                    checkPawnPromotion(indexes, board[indexes.x][indexes.y]);

                    switchCurrentPlayer();

                    displayCheck();

                    checkEndOfGame();

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

    private void updateHalfMove(Point oldIndex, Point newIndex)
    {
        if(board[newIndex.x][newIndex.y].piece != null || board[oldIndex.x][oldIndex.y].pieceType.equalsIgnoreCase("p"))
        {
            halfMoves = 0;

            return;
        }

        halfMoves++;
    }

    private void checkEndOfGame()
    {
        if(halfMoves >= 50) 
        {
            repaint();

            JOptionPane.showMessageDialog(null, "Draw by halfmove.");

            System.out.println("Number of moves: " + fullMoves);

            System.exit(0);
        }

        ArrayList<Point> moves = new ArrayList<Point>();

        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(board[j][i].piece != null && board[j][i].color.equalsIgnoreCase(currentPlayer))
                {
                    moves.addAll(game.listOfMoves(board[j][i], board));
                }
            }
        }

        if(moves.size() == 0)
        {
            if(game.isInCheck)
            {
                repaint();

                displayPanel.moveDisplay.setText(displayPanel.moveDisplay.getText() + "#");

                switchCurrentPlayer();

                JOptionPane.showMessageDialog(null, currentPlayer.toUpperCase() + " has won by checkmate!");
            }
            else
            {
                repaint();

                displayPanel.moveDisplay.setText(displayPanel.moveDisplay.getText() + "$");

                JOptionPane.showMessageDialog(null, "Stalemate");
            }

            System.out.println("Number of moves: " + fullMoves);

            System.exit(0);
        }    
    }

    private void udpatePawns()
    {
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(board[j][i].piece != null && board[j][i].pieceType.equalsIgnoreCase("p") && board[j][i].amountMoved == 1)
                {
                    board[j][i].amountMoved++;
                }
            }
        }
    }

    private void switchPiece(Point indexOfClick, Point oldIndex)
    {
        int newMove = board[oldIndex.x][oldIndex.y].amountMoved + 1;

        board[oldIndex.x][oldIndex.y].isClicked = false;

        board[indexOfClick.x][indexOfClick.y] = new Piece(new Point(indexOfClick.x * board[oldIndex.x][oldIndex.y].dimensions, indexOfClick.y * board[oldIndex.x][oldIndex.y].dimensions), board[oldIndex.x][oldIndex.y].piece, board[oldIndex.x][oldIndex.y].dimensions, board[oldIndex.x][oldIndex.y].color, board[oldIndex.x][oldIndex.y].pieceType);
        board[indexOfClick.x][indexOfClick.y].amountMoved = newMove;

        board[oldIndex.x][oldIndex.y] = new Piece(null, board[oldIndex.x][oldIndex.y].position, board[oldIndex.x][oldIndex.y].dimensions);

        displayPanel.moves.add(new Move(board[indexOfClick.x][indexOfClick.y], oldIndex, indexOfClick));

        displayPanel.updateMoveDisplay();
    }

    private void displayCheck()
    {
        if(game.discoveredCheck(board, currentPlayer))
        {
            game.isInCheck = true;

            switchCurrentPlayer();
            displayPanel.checkDisplay.setText(currentPlayer.toUpperCase() + " is currently in check.");
            switchCurrentPlayer();
        }
        else if(game.check(board, currentPlayer))
        {
            game.isInCheck = true;

            displayPanel.checkDisplay.setText(currentPlayer.toUpperCase() + " is currently in check.");
        }
        else
        {
            game.isInCheck = false;

            displayPanel.checkDisplay.setText(" ");
        }
    }

    private void checkPawnPromotion(Point indexes, Piece piece)
    {     
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

            board[indexes.x][indexes.y] = new Piece(new File("Pieces/w" + answer.toLowerCase() + ".png"), new Point(indexes.x * squareSize, indexes.y * squareSize), squareSize);
            board[indexes.x][indexes.y].amountMoved = newMove;

            return;
        }
        
        if(piece.pieceType.equalsIgnoreCase("p") && indexes.y == board.length - 1)
        {
            int newMove = piece.amountMoved + 1;
            
            String answer = "";

            do
            {
                answer = JOptionPane.showInputDialog(this, "What piece do you want to promote? (q = Queen, b = Bishop, n = Knight, r = Rook)");
            }
            while(!answer.equalsIgnoreCase("q") && !answer.equalsIgnoreCase("n") && !answer.equalsIgnoreCase("b") && !answer.equalsIgnoreCase("r"));

            board[indexes.x][indexes.y] = new Piece(new File("Pieces/b" + answer.toLowerCase() + ".png"), new Point(indexes.x * squareSize, indexes.y * squareSize), squareSize);
            board[indexes.x][indexes.y].amountMoved = newMove;

            return;
        }
    }

    private void checkSpecialMove(Point indexes, Piece piece)
    {      
        //en passant
        if(piece.pieceType.equalsIgnoreCase("p") && indexes.y == 2 && board[indexes.x][indexes.y].piece == null && board[indexes.x][indexes.y + 1].piece != null && board[indexes.x][indexes.y + 1].color.equalsIgnoreCase("b") && board[indexes.x][indexes.y + 1].pieceType.equalsIgnoreCase("p") && board[indexes.x][indexes.y + 1].amountMoved < 2)
        {
            board[indexes.x][indexes.y + 1] = new Piece(null, board[indexes.x][indexes.y + 1].position, board[indexes.x][indexes.y + 1].dimensions);

            return;
        }

        if(piece.pieceType.equalsIgnoreCase("p") && indexes.y == 5 && board[indexes.x][indexes.y].piece == null && board[indexes.x][indexes.y - 1].piece != null && board[indexes.x][indexes.y - 1].color.equalsIgnoreCase("w") && board[indexes.x][indexes.y - 1].pieceType.equalsIgnoreCase("p") && board[indexes.x][indexes.y - 1].amountMoved < 2)
        {
            board[indexes.x][indexes.y - 1] = new Piece(null, board[indexes.x][indexes.y - 1].position, board[indexes.x][indexes.y - 1].dimensions);

            return;
        }

        //short castle
        if(piece.pieceType.equalsIgnoreCase("k") && indexes.x == board[0].length - 2 && indexes.y == board.length - 1)
        {
            board[indexes.x - 1][indexes.y] = new Piece(new Point((indexes.x - 1) * board[board[0].length - 1][board.length - 1].dimensions, indexes.y * board[board[0].length - 1][board.length - 1].dimensions), board[board[0].length - 1][board.length - 1].piece, board[board[0].length - 1][board.length - 1].dimensions, board[board[0].length - 1][board.length - 1].color, board[board[0].length - 1][board.length - 1].pieceType);
            board[board[0].length - 1][board.length - 1] = new Piece(null, board[board[0].length - 1][board.length - 1].position, board[board[0].length - 1][board.length - 1].dimensions);
            
            return;
        }

        if(piece.pieceType.equalsIgnoreCase("k") && indexes.x == board[0].length - 2 && indexes.y == 0)
        {
            board[indexes.x - 1][indexes.y] = new Piece(new Point((indexes.x - 1) * board[board[0].length - 1][0].dimensions, indexes.y * board[board[0].length - 1][0].dimensions), board[board[0].length - 1][0].piece, board[board[0].length - 1][0].dimensions, board[board[0].length - 1][0].color, board[board[0].length - 1][0].pieceType);
            board[board[0].length - 1][0] = new Piece(null, board[board[0].length - 1][0].position, board[board[0].length - 1][0].dimensions);

            return;
        }

        //long castle
        if(piece.pieceType.equalsIgnoreCase("k") && indexes.x == 2 && indexes.y == board.length - 1)
        {
            board[indexes.x + 1][indexes.y] = new Piece(new Point((indexes.x + 1) * board[0][board.length - 1].dimensions, indexes.y * board[0][board.length - 1].dimensions), board[0][board.length - 1].piece, board[0][board.length - 1].dimensions, board[0][board.length - 1].color, board[0][board.length - 1].pieceType);
            board[0][board.length - 1] = new Piece(null, board[0][board.length - 1].position, board[0][board.length - 1].dimensions);

            return;
        }

        if(piece.pieceType.equalsIgnoreCase("k") && indexes.x == 2 && indexes.y == 0)
        {
            board[indexes.x + 1][indexes.y] = new Piece(new Point((indexes.x + 1) * board[0][0].dimensions, indexes.y * board[0][0].dimensions), board[0][0].piece, board[0][0].dimensions, board[0][0].color, board[0][0].pieceType);
            board[0][0] = new Piece(null, board[0][0].position, board[0][0].dimensions);

            return;
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

        fullMoves++;
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
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
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