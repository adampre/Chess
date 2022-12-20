import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.FontUIResource;

public class DisplayPanel extends JPanel implements ActionListener
{
    public JScrollPane scrollPane;
    public JTextArea moveDisplay;

    public JLabel checkDisplay;

    public ArrayList<Move> moves;

    public DisplayPanel(int width, int height, Color backgroundColor)
    {
        moves = new ArrayList<Move>();

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new DimensionUIResource(width - height, height));
        this.setBackground(backgroundColor);

        moveDisplay = new JTextArea();
        moveDisplay.setEditable(false);
        moveDisplay.setFont(new FontUIResource(moveDisplay.getFont().getName(), moveDisplay.getFont().getStyle(), 20));
        moveDisplay.setBackground(backgroundColor);
        moveDisplay.setLineWrap(true);
        moveDisplay.setWrapStyleWord(true);

        scrollPane = new JScrollPane(moveDisplay);
        scrollPane.setPreferredSize(new DimensionUIResource(width - height, 500));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane, BorderLayout.NORTH);

        checkDisplay = new JLabel();
        checkDisplay.setFont(new FontUIResource(checkDisplay.getFont().getName(), checkDisplay.getFont().getStyle(), 34));
        checkDisplay.setBackground(backgroundColor);
        checkDisplay.setOpaque(true);
        this.add(checkDisplay, BorderLayout.SOUTH);  
    }

    public void updateMoveDisplay()
    {   
        String displayedMoves = "";

        A: for(int i = 0; i < moves.size(); i++)
        {
            displayedMoves += ((i / 2) + 1) + ". " + moves.get(i).getMoveNotation() + " ";

            i++;

            if(i >= moves.size()) break A;

            displayedMoves += moves.get(i).getMoveNotation() + " ";
        }

        moveDisplay.setText(displayedMoves);  
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}
