package nl.rutgerkok.physicssimulation.swing;

import static nl.rutgerkok.physicssimulation.Vector2.vec2;
import static nl.rutgerkok.physicssimulation.shape.Circle.circle;
import static nl.rutgerkok.physicssimulation.shape.Rectangle.rectangle;
import static nl.rutgerkok.physicssimulation.world.PhysicalObject.obj;

import javax.swing.JFrame;

import nl.rutgerkok.physicssimulation.world.PhysicsWorld;

public final class SwingStartup {

    public static void main(String... args) {
        JFrame window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("PhysicsSimulation");

        PhysicsWorld world = new PhysicsWorld();
        world.addObject(obj(circle(vec2(100, 100), 30)));
        world.addObject(obj(circle(vec2(300, 100), 30)));
        world.addObject(obj(rectangle(vec2(0, 400), vec2(400, 450))));

        window.setContentPane(new DrawPanel(world));

        window.setVisible(true);
    }
}
