package pixelbased.jar.tools;
import pixelbased.jar.*;
import pixelbased.jar.panel.ColourPanel;

import java.awt.event.MouseEvent;
/**
 * This class represents an eyedropper tool that allows the user to choose a colour from the canvas.
 */
public class EyeDropper implements Tool{
    private Canvas canvas = Canvas.getInstance();
    @Override
    public void mouseReleased(MouseEvent e){
        int x = XConverter(e);
        int y = YConverter(e);
        ColourPanel.setActiveCol(canvas.getPixel(x,y));
    }
}
