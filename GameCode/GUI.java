import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI implements ActionListener
{
    private JFrame frame;

    private BoardPanel boardPanel;

    private final Color BACKGROUND_COLOR = Color.WHITE;

    public GUI()
    {
        frame = new JFrame("Chess");
        frame.setLayout(new BorderLayout());
        frame.setBounds(0, 0, 1200, 820);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(BACKGROUND_COLOR);

        boardPanel = new BoardPanel(frame.getWidth(), frame.getHeight() - 20, BACKGROUND_COLOR);

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