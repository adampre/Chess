import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class DisplayPanel extends JPanel
{
    public JTextArea moveDisplay;
    public JLabel checkDisplay;

    public DisplayPanel(int width, int height, Color backgroundColor)
    {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new DimensionUIResource(width - height, height));
        this.setBackground(backgroundColor);

        moveDisplay = new JTextArea();
        moveDisplay.setEditable(false);
        moveDisplay.setFont(new FontUIResource(moveDisplay.getFont().getName(), moveDisplay.getFont().getStyle(), 20));
        moveDisplay.setBackground(backgroundColor);
        moveDisplay.setPreferredSize(new DimensionUIResource(width - height, height / 2));
        this.add(moveDisplay, BorderLayout.NORTH);

        checkDisplay = new JLabel();
        checkDisplay.setFont(new FontUIResource(checkDisplay.getFont().getName(), checkDisplay.getFont().getStyle(), 34));
        checkDisplay.setBackground(backgroundColor);
        checkDisplay.setOpaque(true);
        this.add(checkDisplay, BorderLayout.SOUTH);  
    }
}
