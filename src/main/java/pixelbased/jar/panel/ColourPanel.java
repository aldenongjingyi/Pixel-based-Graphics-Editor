package pixelbased.jar.panel;


import pixelbased.jar.ColourTile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * A panel for selecting and displaying a color. Contains a grid of pre-set colors,
 * a slider for selecting a shade of the active color, and a display for the active color.
 */
public class ColourPanel extends JPanel implements ActionListener{
    private static Color activeColour = new Color(0,0,0);
    private static JPanel activeDisplay = new JPanel();
    private static Queue<ColourTile> history = new LinkedList<>();
    private static JPanel historyDisplay = new JPanel();

    private JButton CustomColor;

    /**
     * Constructs a new color selection panel.
     */
    public ColourPanel(){
        ToolSlider slider = new ToolSlider();

        historyDisplay.setLayout(new GridLayout(1,4));
        for(int i=0;i<4;i++){
            ColourTile ct = new ColourTile(new Color(0,0,0));
            history.add(ct);
            historyDisplay.add(ct);
        }
    //
        //color board
        this.setLayout(new GridBagLayout());
        GridBagConstraints clboard = new GridBagConstraints();
        clboard.gridx = 1;
        clboard.gridy = 0;
        clboard.insets.right = 10;
        clboard.insets.top = (getHeight() - slider.getPreferredSize().height) / 2;
        clboard.weightx = 0.1;
        clboard.weighty = 0.1;
        clboard.anchor = GridBagConstraints.LINE_END;

        //slider
        GridBagConstraints sl = new GridBagConstraints();
        sl.gridx = 1;
        sl.gridy = 2; //piority
        sl.insets.right = 10;
        sl.insets.top = (getHeight() - slider.getPreferredSize().height) / 2;
        sl.weightx = 1;
        sl.weighty = 0.2;
        sl.anchor = GridBagConstraints.LINE_END;


        //ForButton
        GridBagConstraints but = new GridBagConstraints();
        but.gridx = 1;
        but.gridy = 1;
        but.insets.right = 10;
        but.insets.top = (getHeight() - slider.getPreferredSize().height) / 2;
        but.weightx = 0.1;
        but.weighty = 0.1;
        but.anchor = GridBagConstraints.LINE_END;
        this.add(slider,sl);
        

        JPanel maincontainer= new JPanel(new GridLayout(2,1));
        maincontainer.setPreferredSize(new Dimension(240,520));

        JPanel activePanel = new JPanel(new GridLayout(2,2, 5,5));
        activePanel.add(new JLabel("Active: "));
        activePanel.add(activeDisplay);
        activePanel.add(new JLabel("Last Colour: "));
        activePanel.add(historyDisplay);
        maincontainer.add(activePanel);

        JPanel gridContainer = new JPanel(new GridLayout(8,8));
        for (int i = 0 ; i < 8; i++){
            for (int j = 0; j < 8; j++){
                gridContainer.add(new ColourTile(new Color(i*32, j*32,0 )));
            }
        }
        maincontainer.add(gridContainer);

        this.add(maincontainer,clboard);
        //
        //button
        CustomColor = new JButton("More colours");
        CustomColor.addActionListener(this);
        CustomColor.setMnemonic('K');
        CustomColor.setToolTipText("<html>Advance colour board inside<br> Shortcut : Alt + K<br></html>");
        CustomColor.setPreferredSize(new Dimension(240,30));
        this.add(CustomColor, but);
        setActiveCol(new Color(0,0,0));
        this.setVisible(true);
    }

    /**
     * actionPerformed method for the ActionListener interface. Sets the active color to the
     * color of the clicked ColourTile.
     *
     * @param e The ActionEvent that triggered the method call.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == CustomColor){
            new bigcolorpanel();
        }
    }
    
    /**
     * Gets the currently active color.
     *
     * @return The currently active color.
     */
    public static Color getActiveCol(){
        return activeColour;
    }


    /**
     * Sets the currently active color.
     *
     * @param col The new active color.
     */
    public static void setActiveCol(Color col){
        activeColour = col;
        activeDisplay.setBackground(col);
        activeDisplay.repaint();
        historyDisplay.remove(history.remove());
        ColourTile ct = new ColourTile(col);
        historyDisplay.add(ct);
        history.add(ct);
        historyDisplay.revalidate();
    }
}
