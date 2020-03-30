package drawingapp;
import sun.reflect.generics.tree.BottomSignature;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;
    ShapePanel shapePanel;

    public MainFrame() {
        super("My Drawing Application");
        init();
    }

    /**
     * Aceasta metoda creeaza componentele frame-ului
     * si le pozitioneaza in locatiile specificate,
     * dupa care apeleaza layout manager
     */

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        canvas = new DrawingPanel(this);
        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);
        shapePanel = new ShapePanel(this);
        add(canvas, BorderLayout.CENTER);
        add(configPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.SOUTH);
        add(shapePanel, BorderLayout.WEST);
        pack();
    }
}