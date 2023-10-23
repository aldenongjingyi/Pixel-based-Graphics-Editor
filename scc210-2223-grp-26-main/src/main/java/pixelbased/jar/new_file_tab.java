package pixelbased.jar;
import javax.swing.*;
import javax.swing.text.*;

import pixelbased.jar.panel.NotiFrame;
import pixelbased.jar.panel.ToolsBar;

import java.awt.*;
import java.awt.event.*;

/**
 * This class creates a new tab to generate
 * a new canvas with a custom or predefined size.
 */
public class new_file_tab extends JFrame implements ActionListener{
    //for custom size
    private JButton Accept;
    private JTextField WidthBox, HeightBox;
    //for prefered size
    private JButton A4_V, A4_H, Square, Squarex100, Squarex500 ;
    private Canvas canvas = Canvas.getInstance();
    
    // Create a custom DocumentFilter to limit input to the specified number of characters
    class LimitFilter extends DocumentFilter {
        private int limit;
    
        LimitFilter(int limit) {
            this.limit = limit;
        }
    
        public void insertString(FilterBypass fb, int offset, String str, AttributeSet attr) throws BadLocationException {
            if (fb.getDocument().getLength() + str.length() <= limit) {
                super.insertString(fb, offset, str, attr);
                revalidate();
            }
        }
    
        public void replace(FilterBypass fb, int offset, int length, String str, AttributeSet attrs) throws BadLocationException {
            if (fb.getDocument().getLength() - length + str.length() <= limit) {
                super.replace(fb, offset, length, str, attrs);
                revalidate();
            }
        }
    }


    /**
     * Creates a new file tab with custom size and preferred size containers.
     */
   public new_file_tab(){
        JLabel title1 = new JLabel("Custom size :              ");
    
        //Custom box
        SpringLayout inputlayout = new SpringLayout();
        JPanel inputbox = new JPanel();
        inputbox.setLayout(inputlayout);
        WidthBox = new JTextField("500", 4);
        WidthBox.setDocument(new PlainDocument());
        ((PlainDocument) WidthBox.getDocument()).setDocumentFilter(new LimitFilter(3)); // Add the custom DocumentFilter
        HeightBox = new JTextField("500", 4);
        HeightBox.setDocument(new PlainDocument());
        ((PlainDocument) HeightBox.getDocument()).setDocumentFilter(new LimitFilter(3)); // Add the custom DocumentFilter
        JLabel wLabel  = new JLabel("Width  (px) :");
        JLabel hLabel  = new JLabel("Height (px) :");
        inputbox.add(wLabel);
        inputbox.add(WidthBox);
        inputbox.add(hLabel);
        inputbox.add(HeightBox);

        inputlayout.putConstraint(SpringLayout.WEST, wLabel, 3, SpringLayout.WEST, inputbox);
        inputlayout.putConstraint(SpringLayout.NORTH, wLabel, 3, SpringLayout.NORTH, inputbox);
        inputlayout.putConstraint(SpringLayout.WEST, WidthBox, 3, SpringLayout.EAST, wLabel);
        inputlayout.putConstraint(SpringLayout.NORTH, WidthBox, 3, SpringLayout.NORTH, inputbox);
        inputlayout.putConstraint(SpringLayout.WEST, hLabel, 3, SpringLayout.WEST, inputbox);
        inputlayout.putConstraint(SpringLayout.NORTH, hLabel, 5, SpringLayout.SOUTH, wLabel);
        inputlayout.putConstraint(SpringLayout.WEST, HeightBox, 3, SpringLayout.EAST, hLabel);
        inputlayout.putConstraint(SpringLayout.NORTH, HeightBox, 3, SpringLayout.SOUTH, WidthBox);

        //Accept button
        Accept = new JButton("ACCEPT");
        Accept.addActionListener(this);

        //setup panel
        JPanel Custom = new JPanel(new BorderLayout());
        Custom.setMinimumSize(new Dimension(200,300));
        Custom.add(title1, BorderLayout.NORTH);
        Custom.add(inputbox, BorderLayout.CENTER);
        Custom.add(Accept, BorderLayout.PAGE_END);

        this.add(Custom);

        GridLayout prefered = new GridLayout(3,4);
        prefered.setHgap(20);
        prefered.setVgap(20);

        JPanel Prefered = new JPanel();
        Prefered.setLayout(prefered);

        A4_V = new JButton(ToolsBar.getImage("A4_H-01", "NewFile"));
        A4_H = new JButton(ToolsBar.getImage("A4_W-01", "NewFile"));
        Square = new JButton(ToolsBar.getImage("Square-01", "NewFile"));
        Squarex100 = new JButton(ToolsBar.getImage("squarex100-01", "NewFile"));
        Squarex500 = new JButton(ToolsBar.getImage("squarex500-01", "NewFile"));

        A4_H.addActionListener(this);
        A4_V.addActionListener(this);
        Square.addActionListener(this);
        Squarex100.addActionListener(this);
        Squarex500.addActionListener(this);


        Prefered.add(A4_V);
        Prefered.add(A4_H);
        Prefered.add(Square);
        Prefered.add(Squarex100);
        Prefered.add(Squarex500);

        JPanel mainprefered = new JPanel(new BorderLayout());
        mainprefered.add(new JLabel("Prefered Size: "), BorderLayout.NORTH);
        mainprefered.add(Prefered, BorderLayout.CENTER);

        BorderLayout mainlay = new BorderLayout(15,15);
        this.setLayout(mainlay);

        this.add(Custom, BorderLayout.LINE_END);
        this.add(mainprefered, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(700,400));
        this.setVisible(true);
    }
    /**
     * Responds to button clicks by setting the dimensions of the canvas and closing the tab.
     *
     * @param e the event that triggered this method
     */
   @Override
   public void actionPerformed(ActionEvent e) {
       if (e.getSource() == Accept){
            String widthInput = WidthBox.getText();
            String heightInput = HeightBox.getText();
            if (widthInput == null || heightInput == null){
                JOptionPane.showMessageDialog(new NotiFrame(),"Please insert your size ! ");
            }
            try {
                int width =  Integer.parseInt(widthInput) ;
                int height =  Integer.parseInt(heightInput);
                canvas.setDimensions(width, height);
                this.dispose();
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(new NotiFrame(),"Your size must be an integer ! ");
            }
        }else{
            if (e.getSource() == A4_H){
               canvas.setDimensions(297, 210);
            }else if (e.getSource() == A4_V){
                canvas.setDimensions(210, 297);
            }else if (e.getSource() == Square){
                canvas.setDimensions(250, 250);
            }else if (e.getSource() == Squarex100){
                canvas.setDimensions(100, 100);
            }else if (e.getSource() == Squarex500){
                canvas.setDimensions(500, 500);
            }
            this.dispose();
        }
   }
}





