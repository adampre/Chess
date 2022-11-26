import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

public class DisplayPanel extends JPanel
{
    public DisplayPanel(int width, int height)
    {
        this.setPreferredSize(new DimensionUIResource(width - height, height));
        this.setBackground(Color.GREEN);
    }
}
