package drawingapp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;
    List<Shape> shapes = new ArrayList<>();
    List<Color> colors = new ArrayList<>();
    String comboColor = "random";
    String comboShape = "polygon";
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

    public void createOffscreenImage() {
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
     * preia numarul de laturi dorite in configPanel, genereaza
     * culoarea pentru desenare si deseneaza forma aleasa in
     * ComboBox.
     */

    private void drawShape(int x, int y) {
        int radius = 50 + (int) (100 * Math.random());
        int sides = (int) frame.configPanel.sidesField.getValue();
        if (comboColor.equals("black")) {
            colors.add(new Color(0, 0, 0));
           // graphics.setColor(color);
        } else {
            colors.add(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
            //graphics.setColor(color);
        }

        if (comboShape.equals("polygon")){
            shapes.add(new RegularPolygon(x, y, radius, sides));
        }
        else if (comboShape.equals("circle")){
            shapes.add(new NodeShape(x,y,radius));
        }
        else {
            shapes.add(new Rectangle(x, y, radius));
        }
        graphics.setColor(colors.get(colors.size()-1));
        graphics.fill(shapes.get(shapes.size()-1));
    }

    @Override
    public void update(Graphics g) { }


    /**
     * Aceasta metoda trece prin lista formelor si le deseneaza
     * Apeleaza metoda drawImage pentru a desena o imagine cu pozitiile
     * stange, sus 0 si 0.
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : shapes) {
            graphics.setColor(colors.get(shapes.indexOf(shape)));
            graphics.fill(shape);
        }
        g.drawImage(image, 0, 0, this);
    }
}