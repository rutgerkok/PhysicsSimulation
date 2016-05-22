package nl.rutgerkok.physicssimulation.swing.view;

import java.awt.BorderLayout;
import java.util.List;
import java.util.function.Supplier;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import nl.rutgerkok.physicssimulation.swing.SwingStartup;
import nl.rutgerkok.physicssimulation.world.PhysicsWorld;

public final class MainWindow extends JFrame {

    private static final long serialVersionUID = 7577238832639150286L;

    public MainWindow(PhysicsWorld world, List<Supplier<PhysicsWorld>> worldChoices) {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("PhysicsSimulation");

        JPanel mainPanel = new DrawPanel(world);
        JPanel settingsPanel = new SettingsPanel(worldChoices);

        add(mainPanel, BorderLayout.CENTER);
        add(settingsPanel, BorderLayout.PAGE_END);

        new Timer(1000 / SwingStartup.FPS, event -> {
            world.advance(1 / (double) SwingStartup.FPS);
            repaint();
        }).start();

        setVisible(true);
    }
}
