package pixelbased.jar.tools;
import pixelbased.jar.*;
import pixelbased.jar.panel.ColourPanel;
import pixelbased.jar.panel.ToolSlider;

import java.awt.event.MouseEvent;
import java.awt.Color;

/**
 * This class represents a Circle tool that can be used in a drawing application on a canvas.
 */
public class Circle implements Tool{
    private int startX, startY;
    protected Canvas canvas = Canvas.getInstance();

    public  void drawCircle(int x, int y){
        drawCircle(x,y,ToolSlider.getSliderValue());
    }

    public  void drawCircle(int x, int y, int radius){
       drawCircle(x,y,radius,ColourPanel.getActiveCol());
    }
    
    /**
     * Draws a circle of a given radius centered at (x,y) on the canvas.
     * @param x The x-coordinate of the center of the circle.
     * @param y The y-coordinate of the center of the circle.
     * @param radius The radius of the circle.
     * @param color the colour to set thr pixeld to 
     */
    public  void drawCircle(int x, int y, int radius, Color color){
        for (int x0 = x - radius; x0 <= x + radius; x0++){
            for (int y0 = y - radius; y0 <= y + radius; y0++){
                if ((x0 - x) * (x0 - x) + (y0 - y) * (y0 - y) <= radius * radius) {
                    canvas.setPixel(x0, y0,color);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e){
        canvas.push();
        startX = XConverter(e);
        startY = YConverter(e);
        drawCircle(startX,startY,0);
    }
    public void mouseDragged(MouseEvent e){
        canvas.peek();
        int dx = XConverter(e);
        int dy = YConverter(e);
        int radius = (int) Math.sqrt(Math.pow(startX - dx,2) + Math.pow(startY -dy,2));
        drawCircle(startX,startY,radius);
    }

}
