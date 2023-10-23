package pixelbased.jar.tools;
import java.awt.event.MouseEvent;
/**
 * This class represents a Brush tool that can be used in a drawing application on a canvas.
 */
public class Brush extends Line{
    private int prevX;
    private int prevY;

    /**
     * This method is called when the mouse is dragged while using the Brush tool.
     * It updates the position of the brush and draws a line between the previous and current positions.
     *
     * @param e the MouseEvent object containing information about the mouse drag event
     */
    @Override
    public void mouseDragged(MouseEvent e){
        int x = XConverter(e);
        int y = YConverter(e);
        setLineInBounds(prevX,prevY,x,y);
        prevX = x;
        prevY = y;
    }

    /**
     * This method is called when the mouse is pressed while using the Brush tool.
     * It sets the position of the brush and draws a circle at the current position.
     *
     * @param e the MouseEvent object containing information about the mouse press event
     */
    @Override
    public void mousePressed(MouseEvent e){
        canvas.push();
        int x = XConverter(e);
        int y = YConverter(e);
        prevX = x;
        prevY = y;
        drawCircle(x,y);
    }
}
