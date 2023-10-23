package pixelbased.jar.tools;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import pixelbased.jar.*;
import pixelbased.jar.panel.CanvasPanel;

/**
 * An interface for defining tools that can be used on a canvas panel.
 */
public interface Tool extends MouseMotionListener, MouseListener {

  /**
   * Default implementation of mouseDragged method that does nothing.
   *
   * @param e the MouseEvent object representing the user's mouse input
   */
  default void mouseDragged(MouseEvent e) {
    // Do nothing by default
  }

  /**
   * Default implementation of mouseMoved method that does nothing.
   *
   * @param e the MouseEvent object representing the user's mouse input
   */
  default void mouseMoved(MouseEvent e) {
    // Do nothing by default
  }

  /**
   * Default implementation of mouseClicked method that does nothing.
   *
   * @param e the MouseEvent object representing the user's mouse input
   */
  default void mouseClicked(MouseEvent e) {
    // Do nothing by default
  }

  /**
   * Default implementation of mouseEntered method that does nothing.
   *
   * @param e the MouseEvent object representing the user's mouse input
   */
  default void mouseEntered(MouseEvent e) {
    // Do nothing by default
  }

  /**
   * Default implementation of mouseExited method that does nothing.
   *
   * @param e the MouseEvent object representing the user's mouse input
   */
  default void mouseExited(MouseEvent e) {
    // Do nothing by default
  }

  /**
   * Default implementation of mouseReleased method that does nothing.
   *
   * @param e the MouseEvent object representing the user's mouse input
   */
  default void mouseReleased(MouseEvent e) {
    // Do nothing by default
  }
  /**
   * Default implementation of mousePressed method that does nothing.
   *
   * @param e the MouseEvent object representing the user's mouse input
   */
  default void mousePressed(MouseEvent e) {
    // Do nothing by default
  }

  default int XConverter(MouseEvent e){
    Canvas canvas = Canvas.getInstance();
    CanvasPanel canvasPanel = CanvasPanel.getInstance();
    return e.getX()*canvas.getPixelWidth()/canvasPanel.getWidth();
  }

  default int YConverter(MouseEvent e){
    Canvas canvas = Canvas.getInstance();
    CanvasPanel canvasPanel = CanvasPanel.getInstance();
    return e.getY()*canvas.getPixelHeight()/canvasPanel.getHeight();
  }
}
