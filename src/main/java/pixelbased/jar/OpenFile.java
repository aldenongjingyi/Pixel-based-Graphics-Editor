package pixelbased.jar;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.*;

/**
 * The OpenFile class extends the JFileChooser class and provides functionality for opening CSV files and reading pixel data from them.
 */
public class OpenFile extends JFileChooser {

    /**
     * Constructor for creating a new OpenFile object.
     * Adds a CSV file filter, sets the approve button text and displays the open file dialog.
     * If a file is selected and approved, reads the pixel data from the CSV file and updates the canvas panel.
     * If the dialog is canceled, resets the text fields.
     *
     * @param canvas The canvas to update with the read pixel data.
     */
    public OpenFile(Canvas canvas) {
        this.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("CSV", "csv");
        this.addChoosableFileFilter(csvFilter);
        this.setApproveButtonText("Open");
        int Value = showOpenDialog(this);
        if (Value == JFileChooser.APPROVE_OPTION) {
            File savelocation = this.getSelectedFile();
            readFromCSV(canvas, savelocation.getAbsolutePath());
        }
    }
    /**
     * Reads the pixel data from the specified CSV file and updates the canvas panel.
     *
     * @param canvas The canvas to update with the read pixel data.
     * @param filePath The file path of the CSV file to read from.
     */
    public void readFromCSV(Canvas canvas, String filePath) {
        ArrayList<ArrayList<Color>> dataread = new ArrayList<ArrayList<Color>>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                ArrayList<Color> row = new ArrayList<Color>();
                String[] values = line.split(",");
                for (int i = 0; i < values.length; i += 3) {
                    int red = Integer.parseInt(values[i]);
                    int green = Integer.parseInt(values[i + 1]);
                    int blue = Integer.parseInt(values[i + 2]);
                    row.add(new Color(red, green, blue));
                }
                dataread.add(row);
            }
            canvas.setPixels(dataread);
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file.");
        }
    }
}
