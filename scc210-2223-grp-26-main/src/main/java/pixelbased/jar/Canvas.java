package pixelbased.jar;
import java.util.*;
import java.util.stream.*;

import pixelbased.jar.panel.CanvasPanel;
import pixelbased.jar.panel.ColourPanel;

import java.awt.*;

/**
 * A 2D canvas made of pixels, represented as a 2D array of Color objects.
 * Supports various operations such as setting/getting individual pixels,
 * undo/redo, rotation, flipping, and resizing.
 */
public class Canvas{
    //array of columns each containing a row of data
    private ArrayList<ArrayList<Color>> data = new ArrayList<ArrayList<Color>>();
    private Stack<ArrayList<ArrayList<Color>>> history = new Stack<ArrayList<ArrayList<Color>>>();
    private Stack<ArrayList<ArrayList<Color>>> redo = new Stack<ArrayList<ArrayList<Color>>>();
    private static Canvas INSTANCE;

    /**
     * Constructs a new Canvas object with the specified dimensions.
     * @param x the width of the canvas in pixels
     * @param y the height of the canvas in pixels
     */
    public Canvas(int x, int y){
        for(int i=0;i<y;i++){
            data.add(new ArrayList<Color>());
            for(int j=0;j<x;j++){
                data.get(i).add(new Color(255,255,255));
            }
        }
        push();
    }
    public static Canvas getInstance(){
        if (INSTANCE == null){
            INSTANCE = new Canvas(100,100);
        }
        return INSTANCE;
    }

    /**
     * Sets the dimensions of the canvas to the specified values,
     * discarding any existing pixel data.
     * @param x the new width of the canvas in pixels
     * @param y the new height of the canvas in pixels
     */
    public void setDimensions(int x, int y){
        push();
        ArrayList<ArrayList<Color>> temp = new ArrayList<ArrayList<Color>>();
        for(int i=0;i<y;i++){
            temp.add(new ArrayList<Color>());
            for(int j=0;j<x;j++){
                temp.get(i).add(new Color(255,255,255));
            }
        }
        data = temp;
        CanvasPanel.getInstance().repaint();
    }

    /**
     * Undoes the most recent change to the canvas, if there is one.
     */
    public void pop(){
        if(history.isEmpty())return;
        redo.push(new ArrayList<>(data.stream().map(x -> new ArrayList<>(x)).collect(Collectors.toList())));
        data = new ArrayList<>(history.pop().stream().map(x -> new ArrayList<>(x)).collect(Collectors.toList()));
        CanvasPanel.getInstance().repaint();
    }

    /**
     * Saves the current state of the canvas to the history stack.
     */
    public void push(){
        redo = new Stack<ArrayList<ArrayList<Color>>>();
        history.push(new ArrayList<>(data.stream().map(x -> new ArrayList<>(x)).collect(Collectors.toList())));
    }
    /**
     * Reverts the canvas to its previous state, without removing it from
     * the history stack.
     */
    public void peek(){
        data = new ArrayList<>(history.peek().stream().map(x -> new ArrayList<>(x)).collect(Collectors.toList()));
        CanvasPanel.getInstance().repaint();
    }
    /**
     * Redoes the most recent undone change to the canvas, if there is one.
     */
    public void redo(){
        if(redo.isEmpty())return;
        history.push(new ArrayList<>(data.stream().map(x -> new ArrayList<>(x)).collect(Collectors.toList())));
        data = new ArrayList<>(redo.pop().stream().map(x -> new ArrayList<>(x)).collect(Collectors.toList()));
        CanvasPanel.getInstance().repaint();
    }

    /**
     * Sets the color of the specified pixel.
     * @param x the x-coordinate of the pixel to set
     * @param y the y-coordinate of the pixel to set
     * @param c the new color of the pixel
     */
    public void setPixel(int x, int y, Color c){
        if(x>getPixelWidth()-1){x=getPixelWidth()-1;}
        else if(x<0){x=0;}
        if(y>getPixelHeight()-1){y=getPixelHeight()-1;}
        else if(y<0){y=0;}
        data.get(y).set(x,c);
        CanvasPanel.getInstance().repaint();
    }

    /**
    * Setter used by tools to change the canvas
    * @param x The x coordinate of the pixel.
    * @param y The y coordinate of the pixel.
    */
    public void setPixel(int x, int y){
        setPixel(x,y,ColourPanel.getActiveCol());
    }

    /**
     * Get the color of the pixel at the specified position.
     *
     * @param x The x coordinate of the pixel.
     * @param y The y coordinate of the pixel.
     * @return The color of the pixel.
     */
    public Color getPixel(int x, int y){
        return data.get(y).get(x);
    }

    /**
     * Get the two-dimensional ArrayList of pixels.
     *
     * @return The two-dimensional ArrayList of pixels.
     */
    public ArrayList<ArrayList<Color>> getPixels(){
        return data;
    }

    /**
     * Set the two-dimensional ArrayList of pixels.
     *
     * @param newData The new two-dimensional ArrayList of pixels.
     */
    public void setPixels(ArrayList<ArrayList<Color>> newData){
        history.push(new ArrayList<>(data.stream().map(x -> new ArrayList<>(x)).collect(Collectors.toList())));
        data = new ArrayList<>(newData.stream().map(x -> new ArrayList<>(x)).collect(Collectors.toList()));
        CanvasPanel.getInstance().repaint();
    }

    /**
     * Rotate the canvas clockwise by 90 degrees.
     */
    public void rotateClockwise(){
        ArrayList<ArrayList<Color>> temp = new ArrayList<ArrayList<Color>>();
        for(int i=0;i<data.get(0).size();i++){
            temp.add(new ArrayList<Color>());
            for(int j=data.size()-1;j>=0;j--){
                temp.get(i).add(data.get(j).get(i));
            }
        }
      data = temp;
    }
    /**
     * Rotate the canvas counterclockwise by 90 degrees.
     */
    public void rotateAntiClockwise(){
        ArrayList<ArrayList<Color>> temp = new ArrayList<ArrayList<Color>>();
        for(int i=data.get(0).size()-1;i>=0;i--){
            temp.add(new ArrayList<Color>());
            for(int j=0;j<data.size();j++){
                temp.get(data.get(0).size()-i-1).add(data.get(j).get(i));
            }
        }
      data = temp;
    }

    /**
     * Get the height of the canvas in pixels.
     *
     * @return The height of the canvas.
     */
    public int getPixelHeight(){
        return data.size();
    }

    /**
     * Get the width of the canvas in pixels.
     *
     * @return The width of the canvas.
     */
    public int getPixelWidth(){
        return data.get(0).size();
    }
    /**
     * Flip the canvas horizontally.
     */
    public void flipHor() {
        ArrayList<ArrayList<Color>> temp = new ArrayList<ArrayList<Color>>();
        for(int i=0;i<data.size();i++){
            temp.add(new ArrayList<Color>());
            for(int j=data.get(0).size()-1;j>=0;j--){
                temp.get(i).add(data.get(i).get(j));
            } 
        }
        data = temp;
    }

    /**
     * Flip the canvas vertically.
     */
    public void flipVer() {
        Collections.reverse(data);
    }

}

