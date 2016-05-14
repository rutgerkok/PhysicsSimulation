package nl.rutgerkok.physicssimulation.swing;

import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

import nl.rutgerkok.physicssimulation.AxisAlignedBoundingBox;
import nl.rutgerkok.physicssimulation.Vector2;
import nl.rutgerkok.physicssimulation.paint.Drawable;
import nl.rutgerkok.physicssimulation.paint.MultiDrawable;

public final class SwingStartup {

    public static void main(String... args) {
        JFrame window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("PhysicsSimulation");

        List<Drawable> drawables = Arrays.asList(
                Vector2.of(10, 10),
                Vector2.of(50, 20),
                AxisAlignedBoundingBox.of(Vector2.of(20, 30), Vector2.of(40, 80)));

        window.setContentPane(new DrawPanel(new MultiDrawable(drawables)));

        window.setVisible(true);
    }
}
