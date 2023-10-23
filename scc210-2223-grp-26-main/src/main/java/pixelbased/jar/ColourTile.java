package pixelbased.jar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import pixelbased.jar.panel.ColourPanel;

public class ColourTile extends JButton implements ActionListener{
    private Color rawCol;
    /**
     * Constructor for an individual colourtile
     * @param colIn the desired colour of the tile
     */
    public ColourTile(Color colIn)
    {
        rawCol = colIn;
        setBackground(rawCol);
        setPreferredSize(new Dimension(90,90));
        addActionListener(this);
    }

    /**
     * Method to get the colour of the tile
     * @return the colour of the tile
     */
    public Color getColour()
    {
        return rawCol;
    }

    public void actionPerformed(ActionEvent e) {
        ColourTile referencedTile = (ColourTile)e.getSource();
        ColourPanel.setActiveCol(referencedTile.getColour());
    }
}
