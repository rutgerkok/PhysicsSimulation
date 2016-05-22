package nl.rutgerkok.physicssimulation.swing.view;

import java.awt.FlowLayout;
import java.util.List;
import java.util.Vector;
import java.util.function.Supplier;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import nl.rutgerkok.physicssimulation.world.PhysicsWorld;

final class SettingsPanel extends JPanel {

    private static final long serialVersionUID = 332092739791747134L;

    private static JComboBox<Supplier<PhysicsWorld>> getSelector(List<Supplier<PhysicsWorld>> worldChoices) {
        Vector<Supplier<PhysicsWorld>> worldChoicesVector = new Vector<>();
        worldChoicesVector.addAll(worldChoices);
        return new JComboBox<Supplier<PhysicsWorld>>(worldChoicesVector);
    }

    SettingsPanel(List<Supplier<PhysicsWorld>> worldChoices) {
        setLayout(new FlowLayout(FlowLayout.LEADING));

        add(getSelector(worldChoices));
    }
}
