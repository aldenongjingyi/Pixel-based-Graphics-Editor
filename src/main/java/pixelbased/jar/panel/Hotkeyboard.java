package pixelbased.jar.panel;

import java.awt.*;
import javax.swing.*;


/**
 * A JFrame that displays a table of hotkeys for a pixel-based graphics editor.
 */
public class Hotkeyboard extends JFrame{

    private JTable hotkeytable;
    private JScrollPane scrollPane;

    /**
     * Initializes the components of the JFrame.
     * @param items the container of the JFrame
     */
    public void component(final Container items ){
        String[][] data = {
                //tools
                {"Pen Tool", "Alt + P"},
                {"Brush Tool", "Alt + B"},
                {"Eraser Tool", "Alt + E"},
                {"Bucket/Fill Tool", "Alt + F"},
                {"Line Tool", "Alt + L"},
                {"Rectangle Tool", "Alt + R"},
                {"Circle Tool", "Alt + C"},
                {"Eyedropper Tool", "Alt + E"},
                {"Color Board", "Alt + K"},
                //Function of File Menu
                {"Open File", "Ctrl + O"},
                {"New File", "Ctrl + N"},
                {"Save File", "Ctrl + Shift + S"},
                //Function of Edit Menu
                {"Undo", "Ctrl + Z"},
                {"Redo", "Ctrl + Shift + Z"},
                {"Flip Horizontal", "Ctrl + H"},
                {"Flip Vertical", "Ctrl + T"},
                {"Rotate Left", "Ctrl + R"},
                {"Rotate Right", "Ctrl + Shift + R"}
        };
        String[] titleofcol = {"Function" , "HotKeys"};

        hotkeytable = new JTable(data, titleofcol);
        hotkeytable.setRowMargin(5);
        scrollPane = new JScrollPane(hotkeytable);
        scrollPane.setBounds(400,500, 0, 0);
    }


    /**
     * Initializes a new instance of Hotkeyboard.
     */
    public Hotkeyboard(){
        component(this);
        this.setMinimumSize(new Dimension(400,500));
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        this.setIconImage(ToolsBar.getImage("Icon", "Logo").getImage());
        this.setVisible(true);
    }

}
