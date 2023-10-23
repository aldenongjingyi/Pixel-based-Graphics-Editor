package pixelbased.jar.panel;
import pixelbased.jar.tools.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * A custom JPanel class representing a toolbar with various painting tools
 */
public class ToolsBar extends JPanel implements ActionListener{
    private ArrayList<JButton> buttons = new ArrayList<>();
    private ArrayList<Tool> tools = new ArrayList<>();
    private Tool activeTool;

    /**
     * toolInit function to create a tool button with tooltip 
     * @param tool the button use to set title
     * @param toolname the name use to set for title 
     * @param hotkey the hotkey going with name 
     */
    private void toolInit(JButton tool, String toolname, char hotkey){
        String set = "<html>" + toolname + "<br> Shortcut : Alt + " + hotkey + "<br></html>";
        tool.setMnemonic(hotkey);
        tool.setToolTipText(set);
        buttons.add(tool);
    }


    /**
     * Constructs a new ToolsBar object and initializes the buttons for the different painting tools.
     */
    public ToolsBar(){
        //Start for Tool bar 
        toolInit(new JButton(getImage("PenTools", "Toolbar")) , "Pen Tool", 'P');
        tools.add(new Pen());

        toolInit(new JButton(getImage("EraserTools", "Toolbar")),"Eraser Tool",'E');
        tools.add(new Eraser());

        toolInit(new JButton(getImage("BrushTools", "Toolbar")),"Brush Tool", 'B');
        tools.add(new Brush());

        toolInit(new JButton(getImage("BucketTools", "Toolbar")),"Bucket Tool", 'F');
        tools.add(new Fill());

        toolInit(new JButton(getImage("LineTools", "Toolbar")),"Line Tool", 'L');
        tools.add(new Line());

        toolInit(new JButton(getImage("SquareTools", "Toolbar")),"Rectangle Tool", 'R');
        tools.add(new RectangleTool());

        toolInit(new JButton(getImage("CircleTools", "Toolbar")),"Circle Tool", 'C');
        tools.add(new Circle());

        toolInit(new JButton(getImage("EyeDropper", "Toolbar")),"Eyedropper Tool", 'D');
        tools.add(new EyeDropper());

        JPanel Tools = new JPanel(new GridLayout(buttons.size(),1));
        Tools.setPreferredSize(new Dimension(75,buttons.size() * 75));

        for(JButton button : buttons){
            Tools.add(button);
            button.addActionListener(this);
        } 
        setTool(new Brush());
        buttons.get(2).setBackground(Color.WHITE);

        this.setLayout(new GridBagLayout());

        GridBagConstraints consTools = new GridBagConstraints();
        consTools.anchor = GridBagConstraints.WEST; // Align the panel to the left side
        consTools.weightx = 1.0; // Fill horizontal space
        consTools.gridx = 0; // Left column
        consTools.gridy = 0; // Top row
        consTools.fill = GridBagConstraints.VERTICAL; // Fill vertical space
        consTools.ipadx = 20; // Add some padding
        consTools.ipady = 10; 
        this.add(Tools,consTools);
        this.setBounds(0, 80 , 75, 75 * buttons.size());
        this.setVisible(true);
    }

    /**
     * getImage is used to get icon from resource location
     * @param name : Name of icon 
     * @param location : Name of menu location
     */
    public static ImageIcon getImage(String name, String location){
        String realPath = System.getProperty("user.dir");
        String subpath; 
        if (realPath.contains("target")){
            subpath = "/classes/Icon/";
        }
        else {
            subpath = "/src/main/resources/Icon/";
        }
        try {
            if (location.equals("Logo")){
                return new ImageIcon(new ImageIcon(realPath + subpath + "Logo/" + name +".png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
            }
            if (location.equals("Toolbar")){
                return new ImageIcon(realPath + subpath + "Toolbar/" + name +".png");
            }
            if (location.equals("NewFile")){
                return new ImageIcon(realPath + subpath + "Menu-File/" + name +".png");
            }
            if (location.equals("Walkthrough")){
                return new ImageIcon(new ImageIcon(realPath + subpath + "Walkthrough/" + name +".png").getImage().getScaledInstance( 1280, 720, Image.SCALE_DEFAULT));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(new NotiFrame(), "Missing icon :" + name);
        }
        return null;
    }

    /**
     * setTool used to contact with user interface letting them know the tool they are using 
     * @param newTool Tool is selected
     */
    private void setTool(Tool newTool){
        CanvasPanel canvas = CanvasPanel.getInstance();
        canvas.removeMouseListener(activeTool);
        canvas.removeMouseMotionListener(activeTool);
        activeTool = newTool;
        canvas.addMouseMotionListener(newTool);
        canvas.addMouseListener(newTool);
        for(JButton button : buttons){
            button.setBackground(Color.WHITE);
        } 
    }
    /**
     * Method to set an actionlistener for the buttons on the toolsbar, referencing the main GUI component
     * @param e Object reference of the main NewGUI
     */
    @Override
    public void actionPerformed(ActionEvent e){
        if(buttons.contains(e.getSource())){
            setTool(tools.get(buttons.indexOf(e.getSource())));
            ((JButton)e.getSource()).setBackground(Color.DARK_GRAY);
        }
    }
}
