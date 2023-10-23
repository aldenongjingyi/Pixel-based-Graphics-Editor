package pixelbased.jar.tools;
import pixelbased.jar.*;
import java.awt.event.MouseEvent;

/**
 * This class represents a rectangle tool to be used on the canvas.
 */
public class RectangleTool implements Tool{
    private int startPointx = 0, startPointy = 0;
    private int endPointx = 0, endPointy = 0;
    private Canvas canvas = Canvas.getInstance();

    /**
     * Draws a rectangle on the canvas.
     *
     * @param startX the x-coordinate of the starting point of the rectangle
     * @param startY the y-coordinate of the starting point of the rectangle
     * @param endX the x-coordinate of the ending point of the rectangle
     * @param endY the y-coordinate of the ending point of the rectangle
     */
    public void setRectangle(int startX, int startY, int endX, int endY) {
        if(startX>endX){
            int temp = endX;
            endX = startX;
            startX = temp;
        }

        if(startY>endY){
            int temp = endY;
            endY = startY;
            startY = temp;
        }

        for(int x = startX-1; x!=endX;++x){
            for(int y = startY-1;y!=endY;++y){
                canvas.setPixel(x,y);
            }
        }
    }
    @Override
    public void mousePressed(MouseEvent e){
        canvas.push();
        startPointx = XConverter(e);
        startPointy = YConverter(e);
        canvas.setPixel(startPointx,startPointy);
    }
    
    @Override
    public void mouseDragged(MouseEvent e){
        canvas.peek();
        endPointx = XConverter(e);
        endPointy = YConverter(e);
        setRectangle(startPointx, startPointy, endPointx, endPointy);
    }
}
