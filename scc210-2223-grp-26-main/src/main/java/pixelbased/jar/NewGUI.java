package pixelbased.jar;
import javax.swing.*;

import pixelbased.jar.panel.*;

import java.awt.*;
import java.io.*;
/**
 * The NewGUI class is the main class that sets up the GUI for the Pixel Base drawing program.
 * It extends the JFrame class 
 */
public class NewGUI extends JFrame{

    /**
     * Constructor for Interface_GUI that creates the frame and sets up the GUI components.
     */
    public NewGUI(){
    //This is the colour designed to be used for pen tools, shape tools etc...
        Boolean doWalkthrough = true;
        if(new File(".pixelbased").isFile()){
            try{
                BufferedReader reader = new BufferedReader(new FileReader(".pixelbased"));
                String line;
                while ((line = reader.readLine()) != null) {
                    if(line.startsWith("THEME:")){
                        setTheme(line.split(":")[1]);
                    }else  if(line.startsWith("WALKTHROUGH:")){
                        if(line.split(":")[1].equals("FALSE")){
                            doWalkthrough = false;
                        } 
                    }
                }
                reader.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if(doWalkthrough == true){
            new walkthrough();
        }
        //Adding and setup main frame
        this.setTitle("Pixel Base");
        //this.setLayout(new BorderLayout());
        // Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        // int screenwidth = objDimension.width/2 ;
        // int screenheight = objDimension.height/2 ;
        // this.setLayout(new BorderLayout(screenwidth - 650,screenheight - 400));
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
        this.setBackground(Color.decode("#BEBEBE"));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setMinimumSize(new Dimension(700,500));
        this.setIconImage(ToolsBar.getImage("Icon", "Logo").getImage());

        this.setJMenuBar(new Nav());
        this.add(new ToolsBar(),BorderLayout.LINE_START);
        CanvasPanel cp = CanvasPanel.getInstance();
        JScrollPane scroll = new JScrollPane(cp);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scroll,BorderLayout.CENTER);
        this.add(new ColourPanel(), BorderLayout.LINE_END);

        this.add(new JLabel(""), BorderLayout.PAGE_START);
        this.add(new CanvasSlider(), BorderLayout.PAGE_END);

        this.setVisible(true);

    }
    public  void setTheme(String theme){
        try{
            UIManager.setLookAndFeel(theme);
            SwingUtilities.updateComponentTreeUI(this);
        }catch(Exception ex){
            ex.printStackTrace();
      }
    }
}

