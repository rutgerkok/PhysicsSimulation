package nl.rutgerkok.physicssimulation.swing;

import static nl.rutgerkok.physicssimulation.Circle.circle;
import static nl.rutgerkok.physicssimulation.AxisAlignedBoundingBox.aabb;
import static nl.rutgerkok.physicssimulation.Vector2.vec2;

import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

import nl.rutgerkok.physicssimulation.paint.Drawable;
import nl.rutgerkok.physicssimulation.paint.MultiDrawable;

public final class SwingStartup {

    public static void main(String... args) {
        JFrame window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("PhysicsSimulation");

        List<Drawable> drawables = Arrays.asList(
                vec2(10, 10),
                vec2(50, 20),
                circle(vec2(10, 10), 10),
                aabb(vec2(20, 30), vec2(40, 80)));

        window.setContentPane(new DrawPanel(new MultiDrawable(drawables)));

        window.setVisible(true);
    }
}
