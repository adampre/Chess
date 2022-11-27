import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PromptFrame implements ActionListener
{
    private final Color BACKGROUND_COLOR = Color.WHITE;

    private JFrame promptFrame;

    private JButton oneminute;
    private JButton threeminute;
    private JButton fiveminute;
    private JButton tenminute;
    private JButton thirtyminute;
    private JButton notime;

    public PromptFrame()
    {
        promptFrame = new JFrame("Chess");
        promptFrame.setLayout(new GridLayout(0, 3));
        promptFrame.setBounds(100, 100, 500, 500);
        promptFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        promptFrame.setBackground(BACKGROUND_COLOR);

        oneminute = new JButton("1 Minute");
        oneminute.addActionListener(this);

        threeminute = new JButton("3 Minutes");
        threeminute.addActionListener(this);

        fiveminute = new JButton("5 Minutes");
        fiveminute.addActionListener(this);

        tenminute = new JButton("10 Minutes");
        tenminute.addActionListener(this);

        thirtyminute = new JButton("30 Minutes");
        thirtyminute.addActionListener(this);

        notime = new JButton("No Time");
        notime.addActionListener(this);

        promptFrame.add(oneminute);
        promptFrame.add(threeminute);
        promptFrame.add(fiveminute);
        promptFrame.add(tenminute);
        promptFrame.add(thirtyminute);
        promptFrame.add(notime);
    }

    public void initialize()
    {
        promptFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        promptFrame.setVisible(false);

        GUI gui = new GUI(BACKGROUND_COLOR);

        if(e.getSource().equals(oneminute))
        {
            gui.boardPanel.displayPanel.timePanel.timeLimit = 1;
        }
        if(e.getSource().equals(threeminute))
        {
            gui.boardPanel.displayPanel.timePanel.timeLimit = 3;
        }
        if(e.getSource().equals(fiveminute))
        {
            gui.boardPanel.displayPanel.timePanel.timeLimit = 5;        
        }
        if(e.getSource().equals(tenminute))
        {
            gui.boardPanel.displayPanel.timePanel.timeLimit = 10;        
        }
        if(e.getSource().equals(thirtyminute))
        {
            gui.boardPanel.displayPanel.timePanel.timeLimit = 30;        
        }
        if(e.getSource().equals(notime))
        {
            gui.boardPanel.displayPanel.timePanel.timeLimit = 0;        
        }

        gui.startGame();
    }
}
