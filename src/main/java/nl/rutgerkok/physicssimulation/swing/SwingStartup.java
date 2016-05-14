package nl.rutgerkok.physicssimulation.swing;

import javax.swing.JFrame;

import nl.rutgerkok.physicssimulation.paint.TestPainter;

public final class SwingStartup {

    public static void main(String... args) {
        JFrame window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("PhysicsSimulation");
        window.setVisible(true);
        window.setContentPane(new DrawPanel(new TestPainter()));
    }
}
