package pixelbased.jar.tools;
import pixelbased.jar.*;
import java.awt.event.MouseEvent;
import java.awt.Color;
/**
 This class represents a pen tool that can be used to draw freely on the canvas.
 */

public class Pen extends Line{
    private int prevX;
    private int prevY;
    private  Canvas canvas = Canvas.getInstance();

    @Override
    public void mouseDragged(MouseEvent e){
        int x = XConverter(e);
        int y = YConverter(e);
        setLineInBounds(prevX,prevY,x,y,0);
        prevX = x;
        prevY = y;
    }

    /**
     * Invoked when a mouse button has been pressed on the CanvasPanel.
     * It saves the current state of the panel, sets the coordinates of the initial point
     * of the pen and draws a single pixel at that point.
     * @param e the MouseEvent object that contains information about
     *          the mouse event.
     */
    @Override
    public void mousePressed(MouseEvent e){
        canvas.push();
        int x = XConverter(e);
        int y = YConverter(e);
        prevX = x;
        prevY = y;
        canvas.setPixel(x,y,new Color(0,0,0));
    }
}
