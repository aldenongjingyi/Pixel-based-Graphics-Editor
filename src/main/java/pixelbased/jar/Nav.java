package pixelbased.jar;
import javax.swing.*;

import pixelbased.jar.panel.CanvasPanel;
import pixelbased.jar.panel.Hotkeyboard;
import pixelbased.jar.panel.walkthrough;

import java.awt.event.*;
import java.util.*;
import java.io.*;
import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import static java.awt.event.InputEvent.SHIFT_DOWN_MASK;
public class Nav extends JMenuBar implements ActionListener{
    private ArrayList<JMenuItem> FileOperations = new ArrayList<>();
    private ArrayList<JMenuItem> EditOperations = new ArrayList<>();

    private JMenuItem NewFile ,Open, Saved;
    private JMenuItem Undo, Redo, FlipH, FlipV, RotateL, RotateR;
    private JMenuItem shortcut, contact;
    public Nav(){
        JMenu File = new JMenu("File... ");
        // File.setBackground(Color.decode("#121212"));
        //Menu items of Files
        NewFile = new JMenuItem("New File");
        NewFile.setAccelerator(KeyStroke.getKeyStroke('N', CTRL_DOWN_MASK));
        FileOperations.add(NewFile) ;

        Open = new JMenuItem("Open File");
        Open.setAccelerator(KeyStroke.getKeyStroke('O', CTRL_DOWN_MASK));
        FileOperations.add(Open);

        Saved = new JMenuItem("Save File");
        Saved.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK + SHIFT_DOWN_MASK));
        FileOperations.add(Saved);

        for (JMenuItem op:FileOperations){
            op.addActionListener(this);
            File.add(op);
        }
        
        JMenu Edit = new JMenu("Edit... ");
        //Function Menu
        Undo = new JMenuItem("Undo");
        Undo.setAccelerator(KeyStroke.getKeyStroke('Z', CTRL_DOWN_MASK));
        EditOperations.add(Undo);
        Redo = new JMenuItem("Redo");
        Redo.setAccelerator(KeyStroke.getKeyStroke('Z', CTRL_DOWN_MASK + SHIFT_DOWN_MASK));
        EditOperations.add(Redo);
        FlipH = new JMenuItem("Flip Horizontally");
        FlipH.setAccelerator(KeyStroke.getKeyStroke('H', CTRL_DOWN_MASK ));
        EditOperations.add(FlipH);
        FlipV = new JMenuItem("Flip Vertically");
        FlipV.setAccelerator(KeyStroke.getKeyStroke('T', CTRL_DOWN_MASK ));
        EditOperations.add(FlipV);
        RotateL = new JMenuItem("Rotate Left");
        RotateL.setAccelerator(KeyStroke.getKeyStroke('R', CTRL_DOWN_MASK ));
        EditOperations.add(RotateL);
        RotateR = new JMenuItem("Rotate Right");
        RotateR.setAccelerator(KeyStroke.getKeyStroke('R', CTRL_DOWN_MASK + SHIFT_DOWN_MASK));
        EditOperations.add(RotateR);

        for (JMenuItem op:EditOperations){
            op.addActionListener(this);
            Edit.add(op);
        }
        

        JMenu ThemeMenu = new JMenu("Themes... ");
        
        UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo look : looks) {
            JMenuItem theme = new JMenuItem(look.getName());
            ThemeMenu.add(theme);
            theme.addActionListener(this);
        }
        
        JMenu Help = new JMenu("Help... ");
        shortcut = new JMenuItem("Shortcut");
        shortcut.addActionListener(this);
        contact = new JMenuItem("Help...");
        contact.addActionListener(this);

        Help.add(shortcut);
        Help.add(contact);
        Help.add(ThemeMenu);
        //Add item to Help Menu
        //add to Menu Bar
        this.add(File);
//        navbar.add(File);
        this.add(Edit);
        this.add(Help);
    }

    public  void setTheme(String theme){
        try{
            UIManager.setLookAndFeel(theme);
            SwingUtilities.updateComponentTreeUI(this.getParent());
        }catch(Exception ex){
            System.out.println("error setting theme");
            return;
        }
        File f = new File(".pixelbased");
        try{
            BufferedWriter writer;
            if(!f.isFile()){
                writer = new BufferedWriter(new FileWriter(f,false));
                writer.write("THEME:"+theme+ "\n");
            }else{
                BufferedReader reader = new BufferedReader(new FileReader(".pixelbased"));
                String line = reader.readLine();
                writer = new BufferedWriter(new FileWriter(f,false));
                Boolean exists = false;
                while(line!=null){
                    if(line.startsWith("THEME:")){
                        line = "THEME:"+theme ;
                        exists = true;
                    }
                    writer.write(line+"\n");
                    line = reader.readLine();
                }
                if(exists == false){
                    writer.write("THEME:"+theme+ "\n");
                }
                reader.close();
            }
            writer.close();
        }catch(Exception ex){
            System.out.println("something went wrong");
        }
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(FileOperations.contains(e.getSource()) || EditOperations.contains(e.getSource())){
            Canvas canvas = Canvas.getInstance();
            if(e.getSource() == NewFile){
                new new_file_tab();
            }else if(e.getSource() == Open){
                new OpenFile(canvas);
            }else if(e.getSource() == Saved){
                new SaveFile(canvas);
            }else if(e.getSource() == Undo){
                canvas.pop();
            }else if(e.getSource() == Redo){
                canvas.redo();
            }else if(e.getSource() == FlipH){
                canvas.flipHor();
            }else if(e.getSource() == FlipV){
                canvas.flipVer();
            }else if(e.getSource() == RotateL){
              canvas.rotateAntiClockwise();
            }else if(e.getSource() == RotateR){
              canvas.rotateClockwise();
            }
            CanvasPanel.getInstance().repaint();
        }else if (e.getSource() == shortcut){
            new Hotkeyboard();
        }else if(e.getSource() == contact){
            new walkthrough();
        }else if (e.getSource() instanceof JMenuItem){
            String name = ((JMenuItem)e.getSource()).getText();
            for (UIManager.LookAndFeelInfo look : UIManager.getInstalledLookAndFeels()) {
                if (look.getName()==name){
                    setTheme(look.getClassName());
                }
            }
        }
    }
}
