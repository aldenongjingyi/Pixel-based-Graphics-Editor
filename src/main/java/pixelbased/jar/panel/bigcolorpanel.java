package pixelbased.jar.panel;

import javax.swing.*;
import java.awt.*;

public class bigcolorpanel extends Frame {

    public bigcolorpanel(){

        this.setIconImage(ToolsBar.getImage("Icon", "Logo").getImage());
        Color curcolor = ColourPanel.getActiveCol();           
        Color colorboard = JColorChooser.showDialog(null, "Color Panel", curcolor);
        ColourPanel.setActiveCol(colorboard);
    }    
}
