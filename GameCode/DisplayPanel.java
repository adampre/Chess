import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

public class DisplayPanel extends JPanel
{
    public JTextArea moveDisplay;
    public JTextField checkDisplay;

    public DisplayPanel(int width, int height, Color backgroundColor)
    {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new DimensionUIResource(width - height, height));
        this.setBackground(Color.GREEN);

        moveDisplay = new JTextArea();
        moveDisplay.setEditable(false);
        moveDisplay.setBackground(Color.BLUE);
        moveDisplay.setPreferredSize(new DimensionUIResource(width - height, height / 2));
        this.add(moveDisplay, BorderLayout.CENTER);

        checkDisplay = new JTextField();
        checkDisplay.setEditable(false);
        checkDisplay.setBackground(backgroundColor);
        this.add(checkDisplay, BorderLayout.SOUTH);  
    }
}
