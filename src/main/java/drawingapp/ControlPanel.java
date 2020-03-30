package drawingapp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    /**
     * Aceasta metoda adauga in container butoanele save, load,
     * reset, exit si le atribuie un ActionListener
     */

    private void init() {
        setLayout(new GridLayout(1, 4));
        add(saveBtn);
        add(loadBtn);
        add(resetBtn);
        add(exitBtn);
        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::load);
        resetBtn.addActionListener(this::reset);
        exitBtn.addActionListener(this::exit);
    }

    /**
     * Aceasta metoda am creat un JFileChooser pentru care
     * am setat directorul, titlul si faptul ca poate
     * selecta doar directoare si am apelat metoda
     * showSaveDialog() care returneaza directorul ales
     * sau null daca nu s-a selectat niciun director.
     * In directorul ales am creat un fisier test.png
     * cu imaginea curenta.
     * @param e
     */

    private void save(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("."));
        fc.setDialogTitle("Choose location");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.showSaveDialog(null);
        try {
            frame.canvas.repaint();
            ImageIO.write(frame.canvas.image, "PNG", new File(fc.getSelectedFile().getAbsolutePath() + "/test.png"));
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Aceasta metoda am creat un JFileChooser pentru care
     * am setat directorul, titlul si faptul ca poate
     * selecta doar fisiere si am apelat metoda
     * showSaveDialog() care returneaza directorul ales
     * sau null daca nu s-a selectat niciun director.
     * Am incercat fisierul ales si l-am setat ca imagine
     * de fundal in canvas dupa care am apelat metoda repaint().
     * De asemenea am golit listele cu forme si culorile lor.
     * @param e
     */
    private void load(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("."));
        fc.setDialogTitle("Choose location");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.showSaveDialog(null);
        System.out.println(fc.getSelectedFile().getAbsolutePath());
        try {
            BufferedImage image = ImageIO.read(new File(fc.getSelectedFile().getAbsolutePath()));
            frame.canvas.shapes.clear();
            frame.canvas.colors.clear();
            frame.canvas.image = image;
            frame.canvas.graphics = frame.canvas.image.createGraphics();
            frame.canvas.repaint();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Aceasta metoda atribuie obiectelor
     * canvas.image si canvas.graphics valorile initiale
     * goleste listele cu formele si culorile aferente
     * si redeseneaza.
     *
     * @param e
     */
    private void reset(ActionEvent e) {
        int W = frame.canvas.getWidth();
        int H = frame.canvas.getHeight();
        frame.canvas.shapes.clear();
        frame.canvas.colors.clear();
        frame.canvas.createOffscreenImage();
        frame.canvas.image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        frame.canvas.graphics = frame.canvas.image.createGraphics();
        frame.canvas.graphics.setColor(Color.WHITE);
        frame.canvas.graphics.fillRect(0, 0, W, H);
        frame.canvas.repaint();
    }

    /**
     * Aceasta metoda incheie rularea aplicatiei.
     *
     * @param e
     */
    private void exit(ActionEvent e) {
        System.exit(0);
    }
}
