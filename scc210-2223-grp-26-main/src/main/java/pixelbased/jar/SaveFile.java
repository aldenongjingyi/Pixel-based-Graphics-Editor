package pixelbased.jar;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import pixelbased.jar.panel.*;

import java.io.*;
import javax.imageio.*;
import java.io.IOException;

/**
 * A class that allows the user to save a canvas as a JPG, PNG, or CSV file.
 */
public class SaveFile extends JFileChooser{

    /**
     * Constructs a SaveFile object with a CanvasPanel to save.
     * @param canvas the Canvas to save
     */
    public SaveFile(Canvas canvas){
        FileNameExtensionFilter jpgFilter = new FileNameExtensionFilter("JPG", "jpg");
        FileNameExtensionFilter pngFilter = new FileNameExtensionFilter("PNG", "png");
        FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("CSV", "csv");
        this.setAcceptAllFileFilterUsed(false);
        this.addChoosableFileFilter(pngFilter);
        this.addChoosableFileFilter(jpgFilter);
        this.addChoosableFileFilter(csvFilter);
        int Value = showSaveDialog(null);

        if(Value == JFileChooser.APPROVE_OPTION){
            File savelocation = this.getSelectedFile();
            if (this.getSelectedFile().getName().isEmpty()){
                JOptionPane.showMessageDialog(new NotiFrame(),"Please pick a name for the file");
            }
            else {
                String filePath = savelocation.getAbsolutePath();
                String fileType = this.getFileFilter().getDescription().toLowerCase();
                if(fileType.equals("jpg") || fileType.equals("png")){
                    savefunction(filePath,fileType);
                    JOptionPane.showMessageDialog(new NotiFrame(), "Saved at: " + savelocation + "." + fileType);
                }
                else if (fileType.equals("csv")){
                    writeToCSV(canvas, filePath + ".csv");
                    JOptionPane.showMessageDialog(new NotiFrame(), "Saved at: " + savelocation + "." + fileType);
                }


            }

        }

    }
    /**
     * Saves the canvas as an image file.
     * @param path the file path to save to
     * @param type the file type (JPG or PNG)
     */
    private void savefunction (String path, String type){
        CanvasPanel canvasPanel = CanvasPanel.getInstance();
        BufferedImage image = new BufferedImage(canvasPanel.getWidth(), 
                                                canvasPanel.getHeight(), 
                                                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        canvasPanel.paint(g);
        g.dispose();
        try {
            ImageIO.write(image, type.toUpperCase() , new File(path + "." + type));
        } catch (IOException e) {
            System.err.println("Failed to save canvas as PNG.");
            e.printStackTrace();
        }
    }
    /**
     * Writes the canvas data as a CSV file.
     * @param canvas the Canvas to save
     * @param path the file path to save to
     */
    public void writeToCSV(Canvas canvas, String path) {
        try {
            FileWriter writer = new FileWriter(path);
            int width = canvas.getPixelWidth();
            int height = canvas.getPixelHeight();
            for(int i=0;i<height;i++){
                for(int j=0;j<width;j++){
                    Color pixel = canvas.getPixel(j,i);
                    writer.append(pixel.getRed() + "," + pixel.getGreen() + "," + pixel.getBlue());
                    if (j < width - 1) {
                        writer.append(",");
                    }
                }
                writer.append("\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
