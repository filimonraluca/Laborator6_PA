package drawingapp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;
    String comboColor = "random";
    BufferedImage image;
    Graphics2D graphics;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    /**
     * Creeaza imaginea de fundal pentru desenarea
     * poligoanelor regulate.
     */

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
    }

    private void init() {
        setPreferredSize(new Dimension(W, H));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawShape(e.getX(), e.getY());
                repaint();
            }
        });
    }

    /**
     * Aceasta metoda genereaza random valoarea pentru radius,
     * preia numarul de laturi dorite in configPanel si genereaza
     * culoarea pentru desenare, in functie de alegerea din ComboBox.
     */

    private void drawShape(int x, int y) {
        int radius = 50 + (int) (100 * Math.random());
        int sides = (int) frame.configPanel.sidesField.getValue();
        if (comboColor.equals("black")) {
            Color color = new Color(0, 0, 0);
            graphics.setColor(color);
        } else {
            Color color = new Color((float) Math.random(), (float) Math.random(), (float) Math.random(), (float) Math.random());
            graphics.setColor(color);
        }
        graphics.fill(new RegularPolygon(x, y, radius, sides));
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}