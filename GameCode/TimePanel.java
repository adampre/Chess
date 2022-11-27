import javax.swing.*;
import java.awt.event.*;

public class TimePanel extends JPanel implements ActionListener
{
    private Timer timer;

    public String currentPlayer;

    public int timeLimit;

    public TimePanel()
    {
        timer = new Timer(100, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        // TODO Auto-generated method stub
        
    }
}
