import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI implements ActionListener
{
    private JFrame frame;

    public BoardPanel boardPanel;

    public GUI(Color backgroundColor)
    {
        frame = new JFrame("Chess");
        frame.setLayout(new BorderLayout());
        frame.setBounds(0, 0, 1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(backgroundColor);

        boardPanel = new BoardPanel(frame.getWidth(), frame.getHeight(), backgroundColor);

        frame.add(boardPanel, BorderLayout.CENTER);

        frame.addMouseListener(boardPanel);
        frame.addMouseMotionListener(boardPanel);

        frame.setVisible(true);
    }

    public void startGame()
    {
        boardPanel.gameInit();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}