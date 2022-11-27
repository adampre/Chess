import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

public class DisplayPanel extends JPanel
{
    public TimePanel timePanel;

    public JTextArea moveDisplay;
    public JTextField promptPanel;

    public DisplayPanel(int width, int height, Color backgroundColor)
    {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new DimensionUIResource(width - height, height));
        this.setBackground(Color.GREEN);

        timePanel = new TimePanel();

        moveDisplay = new JTextArea();
        moveDisplay.setEditable(false);
        moveDisplay.setBackground(Color.BLUE);
        moveDisplay.setPreferredSize(new DimensionUIResource(width - height, height / 2));
        this.add(moveDisplay, BorderLayout.CENTER);

        promptPanel = new JTextField();
        promptPanel.setEditable(false);
        promptPanel.setBackground(backgroundColor);
        this.add(promptPanel, BorderLayout.SOUTH);  
    }
}
