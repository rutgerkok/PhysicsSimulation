package nl.rutgerkok.physicssimulation.swing;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import nl.rutgerkok.physicssimulation.swing.view.MainWindow;
import nl.rutgerkok.physicssimulation.world.PhysicsSimulation;

public final class SwingStartup {

    public static final int FPS = 60;

    public static void main(String... args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            // Ignore, use default look and feel
        }

        List<Supplier<PhysicsSimulation>> worldChoices = Arrays.asList(new CirclesAtInterface(), new ThreeFallingSpheres());
        PhysicsSimulation world = worldChoices.get(0).get();

        new MainWindow(world, worldChoices);
    }
}
