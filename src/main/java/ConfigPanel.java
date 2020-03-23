import javax.swing.*;
import java.awt.event.ActionEvent;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel sidesLabel;
    JSpinner sidesField;
    JComboBox colorCombo;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    /**
     * Aceasta metoda creeaza labelul pentru spinner,
     * spinnerul pentru numarul de laturi ale poligonului regulat
     * si comboBoxul pentru alegerea culorii.
     * Toate aceste componente sunt adaugate in container.
     */

    private void init() {
        sidesLabel = new JLabel("Number of sides:");
        sidesField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        sidesField.setValue(6);
        String[] colors = {"black", "random"};
        colorCombo = new JComboBox<String>(colors);
        colorCombo.setSelectedIndex(1);
        colorCombo.addActionListener(this::setColor);
        add(sidesLabel);
        add(sidesField);
        add(colorCombo);
    }

    /**
     * Memoreaza in variabila comboColor din
     * clasa DrawingPanel culoarea aleasa
     * de catre utilizator.
     */

    private void setColor(ActionEvent e) {
        frame.canvas.comboColor = (String) colorCombo.getSelectedItem();
    }
}
