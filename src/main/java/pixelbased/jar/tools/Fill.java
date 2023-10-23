package pixelbased.jar.tools;
import pixelbased.jar.*;
import pixelbased.jar.panel.ColourPanel;

import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Point;
import java.util.*;
/**
 * This class represents a fill tool that fills a region of pixels with a specified color
 * using breadth-first search.
 */
public class Fill implements Tool {
    private Canvas canvas = Canvas.getInstance();
    /**
     * @param x The x-coordinate of the starting pixel
     * @param y The y-coordinate of the starting pixel
     * @param col The color to fill the region with
     */
    private void fill(int x, int y, Color col){
        int height = canvas.getPixelHeight();
        int width = canvas.getPixelWidth();
        Color targetColor = canvas.getPixel(x,y);
        Color fillColor = ColourPanel.getActiveCol();

        if (targetColor.equals(fillColor)) {
            return;
        }

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x,y));

        while (!queue.isEmpty()) {
            Point p = queue.remove();
            int px = p.x;
            int py = p.y;

            if (px >= 0 && px < width && py >= 0 && py < height && canvas.getPixel(px,py).equals(targetColor)) {
                canvas.setPixel(px, py);
                queue.add(new Point(px + 1, py));
                queue.add(new Point(px - 1, py));
                queue.add(new Point(px, py + 1));
                queue.add(new Point(px, py - 1));
            }
        }
    }

    /**
     * Handles the mouse release event by filling the region around the clicked pixel.
     *
     * @param e The mouse event object
     */
    @Override
    public void mouseReleased(MouseEvent e){
        canvas.push();
        int x = XConverter(e);
        int y = YConverter(e);
        fill(x, y, canvas.getPixel(x,y));
    }
}
