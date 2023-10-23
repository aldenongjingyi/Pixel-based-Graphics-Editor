package pixelbased.jar.panel;
import javax.swing.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * The CanvasSlider class represents a slider used to zoom into the canvas
 */
public class CanvasSlider extends JPanel implements ChangeListener  {
    private  JSlider slider = new JSlider(1,100);

    /**
     * Constructs a new CanvasSlider with a slider for zooming into the canvas
     */
    public CanvasSlider(){
        slider.setValue(1);

        slider.addChangeListener(this);

        //slider.addChangeListener();
        this.add(new JLabel("Zoom"));
        this.add(slider);
    }

    /**
     * Called whenever the value of the slider changes.
     * Updates the current value label to reflect the new value.
     *
     * @param e the ChangeEvent that occurred
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        double value = (double)slider.getValue()-1;
        CanvasPanel.setZoom((value/50)+1);
        CanvasPanel.getInstance().repaint();
    }
}
