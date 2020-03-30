package drawingapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ShapePanel extends JPanel {
    final MainFrame frame;
    JComboBox shapeCombo;
    JButton undoBtn = new JButton("Undo");

    public ShapePanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    /**
     * Aceasta metoda adauga in cointainer comboBoxul
     * pentru alegerea formei dorite si butonul de
     * undo. ComboBoxul are un ActionListener
     * care activeaza metoda locala setShape, iar
     * butonul undo are un ActionListener
     *  care activeaza metoda locala undo.
     */

    private void init() {
        String[] shapes = {"polygon", "circle","rectangle"};
        shapeCombo = new JComboBox<String>(shapes);
        shapeCombo.setSelectedIndex(0);
        shapeCombo.addActionListener(this::setShape);
        add(shapeCombo, BorderLayout.NORTH);
        add(undoBtn, BorderLayout.NORTH);
        undoBtn.addActionListener(this::undo);
    }

    /**
     * Aceasta metoda seteaza atributul comboShape
     * la forma selectata din ComboBox. Initial acesta are
     * valoare polygon.
     * @param e
     */

    private void setShape(ActionEvent e) {
        frame.canvas.comboShape = (String) shapeCombo.getSelectedItem();
    }

    /**
     * Aceasta metoda seteaza culoarea alb si reumple ultima forma, sterge din
     * lista formelor ultima forma
     * inserata si de asemenea sterge din lista culorile culoarea
     * formei dupa care apeleaza metoda repaint.
     * @param e
     */

    private void undo(ActionEvent e) {
        if (frame.canvas.shapes.size() > 0) {
            frame.canvas.graphics.setColor(Color.white);
            frame.canvas.graphics.fill(frame.canvas.shapes.get(frame.canvas.shapes.size() - 1));
            frame.canvas.shapes.remove(frame.canvas.shapes.size() - 1);
            frame.canvas.colors.remove(frame.canvas.colors.size() - 1);
            frame.canvas.repaint();
        }
    }
}
