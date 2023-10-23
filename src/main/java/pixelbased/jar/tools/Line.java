package pixelbased.jar.tools;

import pixelbased.jar.panel.*;

import java.awt.event.MouseEvent;
import java.awt.Color;
/**
 * This class represents a line tool to draw straight lines on the canvas.
 */
public class Line extends Circle{
    private int startX, startY;
    private int endX, endY;


    public void setLineInBounds(int x1,int y1, int x2, int y2){
        setLineInBounds(x1,y1,x2,y2,ToolSlider.getSliderValue(),ColourPanel.getActiveCol());
    }
    public void setLineInBounds(int x1,int y1, int x2, int y2,int width){
        setLineInBounds(x1,y1,x2,y2,width,ColourPanel.getActiveCol());
    }
    public void setLineInBounds(int x1,int y1, int x2, int y2,Color c){
        setLineInBounds(x1,y1,x2,y2,ToolSlider.getSliderValue(),c);
    }

    /**
     * Sets the line in bounds on the canvas with given start and end points
     *
     * @param x1    the x-coordinate of the starting point
     * @param y1    the y-coordinate of the starting point
     * @param x2    the x-coordinate of the ending point
     * @param y2    the y-coordinate of the ending point
     * @param width the width of the line
     * @param c the colour of the line to draw 
     */
    public void setLineInBounds(int x1,int y1, int x2, int y2, int width, Color c){
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int XIncrement  = x1 < x2 ? 1 : -1;
        int YIncrement  = y1 < y2 ? 1 : -1;
        int excpetionerror = dx - dy;
        int maxX = canvas.getPixelWidth();
        int maxY = canvas.getPixelHeight();

        while (true){
            if (x1 + XIncrement  > maxX || x1 - XIncrement  <= 0 || y1 + YIncrement  > maxY || y1 - YIncrement  <= 0){
                XIncrement = 0;
                YIncrement = 0;
                return;
            }
            else {
                drawCircle(x1,y1,width,c);
                if (x1==x2 && y1 == y2){
                    break;
                }
                int e2 = 2 *excpetionerror;
                if (e2 > -dy){
                    excpetionerror -= dy;
                    x1 += XIncrement ;
                }
                if (e2 < dx){
                    excpetionerror += dx;
                    y1 +=YIncrement ;
                }
            }
        }
    }


    /**
     * Handles the mouse dragged event to draw line on the canvas
     *
     * @param e the MouseEvent object representing the mouse dragged event
     */
    @Override
    public void mouseDragged(MouseEvent e){
        canvas.peek();
        endX = XConverter(e);
        endY = YConverter(e);
        setLineInBounds(startX, startY, endX, endY);
    }

    /**
     * Handles the mouse pressed event to set the starting point and activate the pixel color on the canvas
     *
     * @param e the MouseEvent object representing the mouse pressed event
     */
    @Override
    public void mousePressed(MouseEvent e){
        canvas.push();
        startX = XConverter(e);
        startY = YConverter(e);
        canvas.setPixel(startX,startY);
    }
    /**
     * Handles the mouse released event to draw the final line on the canvas
     *
     * @param e the MouseEvent object representing the mouse released event
     */
    public void mouseReleased(MouseEvent e){
        endX = XConverter(e);
        endY = YConverter(e);
        setLineInBounds(startX, startY, endX, endY);
    }
}
