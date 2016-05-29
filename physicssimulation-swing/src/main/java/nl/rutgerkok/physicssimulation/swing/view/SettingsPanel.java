package nl.rutgerkok.physicssimulation.swing.view;

import java.awt.FlowLayout;
import java.util.List;
import java.util.Vector;
import java.util.function.Supplier;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import nl.rutgerkok.physicssimulation.world.PhysicsSimulation;

final class SettingsPanel extends JPanel {

    private static final long serialVersionUID = 332092739791747134L;

    private static JComboBox<Supplier<PhysicsSimulation>> getSelector(List<Supplier<PhysicsSimulation>> worldChoices) {
        Vector<Supplier<PhysicsSimulation>> worldChoicesVector = new Vector<>();
        worldChoicesVector.addAll(worldChoices);
        return new JComboBox<Supplier<PhysicsSimulation>>(worldChoicesVector);
    }

    SettingsPanel(List<Supplier<PhysicsSimulation>> worldChoices) {
        setLayout(new FlowLayout(FlowLayout.LEADING));

        add(getSelector(worldChoices));
    }
}
