import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI implements ActionListener
{
    private JFrame frame;

    private BoardPanel boardPanel;

    public GUI()
    {
        frame = new JFrame("Chess");
        frame.setLayout(new BorderLayout());
        frame.setBounds(0, 0, 800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardPanel = new BoardPanel(frame.getWidth());

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