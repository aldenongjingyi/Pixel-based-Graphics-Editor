package pixelbased.jar.panel;
import pixelbased.jar.Canvas;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
public final class CanvasPanel extends JPanel implements Scrollable{
    private static CanvasPanel INSTANCE;
    private static int width ;
    private static int height;
    private static double zoom=1;
    public CanvasPanel(){
        INSTANCE = this;
        setBorder(BorderFactory.createLineBorder(Color.black));
        width = Canvas.getInstance().getPixelWidth();
        height = Canvas.getInstance().getPixelHeight();
    }

    public static void setZoom(double newZoom){
        zoom = newZoom;
    }

    public static CanvasPanel getInstance(){
        if (INSTANCE == null){
            INSTANCE = new CanvasPanel();
        }
        return INSTANCE;
    }
    public BufferedImage getCanvasImage(){
        Canvas canvas = Canvas.getInstance();
        int canvasWidth = canvas.getPixelWidth();
        int canvasHeight = canvas.getPixelHeight();
        BufferedImage image = new BufferedImage(canvasWidth,canvasHeight,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        for(int i =0;i<canvasWidth;i++){
            for(int j=0;j<canvasHeight;j++){
                g2d.setColor(canvas.getPixel(i,j));
                g2d.fillRect(i,j,1,1);
            }
        }
        g2d.dispose();
        return image;
    }
    /**
    * Function called on every repaint() call
    * Fills the pane with squares representing each pixel on the canvas
    **/
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Canvas canvas = Canvas.getInstance();
        Graphics2D g2d = (Graphics2D)g;

        int canvasWidth = canvas.getPixelWidth();
        int canvasHeight = canvas.getPixelHeight();

        int edgeLength = Math.min(getParent().getWidth() / canvasWidth,getParent().getHeight() / canvasHeight);
        width = (int)(edgeLength*canvasWidth*zoom);
        height = (int)(edgeLength*canvasHeight*zoom);
        if(width<canvasWidth){
            width=canvasWidth;
        }
        if(height<canvasHeight){
            height = canvasHeight;
        }
        BufferedImage image = getCanvasImage();
        g2d.drawImage(image.getScaledInstance(width,height,Image.SCALE_DEFAULT),0,0,this);
        g2d.dispose();
        setSize(width,height);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width,height);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(width,height);
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return new Dimension(width,height);
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 128;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 128;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return getPreferredSize().height
        <= getParent().getSize().height;
    }
    @Override
    public boolean getScrollableTracksViewportWidth() {
        return getPreferredSize().width
        <= getParent().getSize().width;
    }
}
