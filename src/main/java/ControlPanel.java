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
     * Aceasta metoda salveaza continutul din canvas.image
     * intr-un fisier extern de tipul png
     *
     * @param e
     */

    private void save(ActionEvent e) {
        try {
            ImageIO.write(frame.canvas.image, "PNG", new File("./test.png"));
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Aceasta metoda incarca continutul fisierului extern
     * intr-un BufferedImage care va prelua rolul de imagine
     * de fundal pentru aplicatie.
     *
     * @param e
     */

    private void load(ActionEvent e) {
        try {
            BufferedImage image = ImageIO.read(new File("./test.png"));
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
     *
     * @param e
     */
    private void reset(ActionEvent e) {
        int W = frame.canvas.getWidth();
        int H = frame.canvas.getHeight();
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
