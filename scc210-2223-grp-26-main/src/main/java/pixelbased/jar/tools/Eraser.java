package pixelbased.jar.tools;

import pixelbased.jar.panel.*;

import java.awt.event.MouseEvent;
import java.awt.Color;
/**
 * This class represents an eraser tool that can be used on the canvas.
 */
public class Eraser extends Line{
    private int prevX;
    private int prevY;

    /**
     * Responds to a mouse drag event.
     * @param e the MouseEvent
     */
    @Override
    public void mouseDragged(MouseEvent e){
        int x = XConverter(e);
        int y = YConverter(e);
        setLineInBounds(prevX,prevY,x,y,new Color(255,255,255));
        prevX = x;
        prevY = y;
    }

    /**
     * Responds to a mouse press event.
     * @param e the MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e){
        canvas.push();
        int x = XConverter(e);
        int y = YConverter(e);
        prevX = x;
        prevY = y;
        drawCircle(x,y,ToolSlider.getSliderValue(),new Color(255,255,255));
    }
}
