package pixelbased.jar.panel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class walkthrough extends JFrame implements ActionListener{
    private JButton next, close;
    private JCheckBox check;
    private JPanel buttonpanel;
    private JPanel belowpanel;

    private JLabel guidelabel;
    private JPanel guidelines;
    private int counter = 0;


    public void guidelinepanel(){
        guidelines = new JPanel();
        //((JLabel) guidelines.getComponent(0)).setIcon(ToolsBar.getImage("Guide1", "Walkthrough"));
        guidelabel = new JLabel();
        //default first guidlines
        guidelabel.setIcon(ToolsBar.getImage("Guide0", "Walkthrough"));
        guidelines.add(guidelabel);
    }

    public void buttonpanel(){
        belowpanel = new JPanel(new GridLayout(1,2));
        check = new JCheckBox("Do not show again.", false);
        next = new JButton("Next");
        close = new JButton("Close");
        buttonpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        next.addActionListener(this);
        close.addActionListener(this);

        //next.put(keyStroke, keyStroke.toString());
        
        buttonpanel.add(close );
        buttonpanel.add(next);

        belowpanel.add(check);
        belowpanel.add(buttonpanel);

    }

    public walkthrough(){
        guidelinepanel();
        buttonpanel();
        this.setLayout(new BorderLayout());
        this.add(guidelines, BorderLayout.CENTER);
        this.add(belowpanel, BorderLayout.SOUTH);


        this.setTitle("Walkthrough with us !");
        this.setAlwaysOnTop (true);
        this.setSize(1280, 720);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setIconImage(ToolsBar.getImage("Icon", "Logo").getImage());
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next  ){
            counter++;
            guidelabel.setIcon(ToolsBar.getImage("Guide"+counter, "Walkthrough"));
            if (counter == 10){
                dispose();
            }
        }
        if (e.getSource() == close){
            dispose();
        }
        if (check.isSelected()){
            File f = new File(".pixelbased");
            try{
                BufferedWriter writer;
                if(!f.isFile()){
                    writer = new BufferedWriter(new FileWriter(f,false));
                    writer.write("WALKTHROUGH:FALSE\n");
                }else{
                    BufferedReader reader = new BufferedReader(new FileReader(".pixelbased"));
                    String line = reader.readLine();
                    writer = new BufferedWriter(new FileWriter(f,false));
                    Boolean exists = false;
                    while(line!=null){
                        if(line.startsWith("WALKTHROUGH:")){
                            line = "WALKTHROUGH:FALSE";
                            exists = true;
                        }
                        writer.write(line + "\n");
                        line = reader.readLine();
                    }
                    if(exists == false){
                        writer.write("WALKTHROUGH:FALSE\n");
                    }
                    reader.close();
                }
                writer.close();
            }catch(Exception ex){
                System.out.println("something went wrong");
            }
        }
    }
    
}
