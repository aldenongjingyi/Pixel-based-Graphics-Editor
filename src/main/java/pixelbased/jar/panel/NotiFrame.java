package pixelbased.jar.panel;

import javax.swing.*;

/**
 * The NotiFrame class extends the JFrame class and creates a new frame for displaying notifications.
 */
public class NotiFrame extends JFrame {

    /**
     * Constructor for creating a new NotiFrame object.
     * Sets the title, size, default close operation and resizable properties of the frame.
     */
    public NotiFrame() {
        this.setTitle("Notification !");
        this.setSize(200, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(ToolsBar.getImage("Icon", "Logo").getImage());
        this.setResizable(false);
    }
}
