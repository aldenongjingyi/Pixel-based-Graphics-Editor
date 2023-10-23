package pixelbased.jar.panel;
import javax.swing.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * The ToolSlider class represents a slider used to select the size of a tool.
 */
public class ToolSlider extends JPanel implements ChangeListener  {
    private  int minSize = 0, maxSize = 20;
    private  JSlider slider = new JSlider(minSize,maxSize);
    private  JLabel currentvalue = new JLabel();
    private  static int value;

    /**
     * Constructs a new ToolSlider with a slider for selecting the size of a tool.
     */
    public ToolSlider(){

        GridLayout layout = new GridLayout(3,1);
        this.setLayout(layout);

        // paint the ticks and tracks
        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        // setup spaceing
        slider.setValue(minSize);
        slider.setMajorTickSpacing(50);
        slider.setMinorTickSpacing(5);

        //Label
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(minSize, new JLabel("Min"));
        labelTable.put(maxSize, new JLabel("Max"));
        slider.setLabelTable(labelTable);
        currentvalue.setText("Current Size = " + slider.getValue() + " pixel(s)");
        //Listener
        slider.addChangeListener(this);

        //slider.addChangeListener();
        this.add(new JLabel("Pick your size: "));
        this.add(slider);
        this.add(currentvalue);
    }

    /**
     * Called whenever the value of the slider changes.
     * Updates the current value label to reflect the new value.
     *
     * @param e the ChangeEvent that occurred
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        value = slider.getValue();
        currentvalue.setText("Current Size = " + String.format("%02d",value) + " pixel(s)");
    }

    /**
     * Returns the current value of the slider.
     *
     * @return the current value of the slider
     */
    public static int getSliderValue(){
        return value;
    }

}
