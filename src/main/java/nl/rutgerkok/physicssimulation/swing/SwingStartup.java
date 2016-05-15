package nl.rutgerkok.physicssimulation.swing;

import static nl.rutgerkok.physicssimulation.shape.Circle.circle;
import static nl.rutgerkok.physicssimulation.shape.Rectangle.rectangle;
import static nl.rutgerkok.physicssimulation.vector.Vector.vec2;
import static nl.rutgerkok.physicssimulation.world.PhysicalObject.obj;

import javax.swing.JFrame;
import javax.swing.Timer;

import nl.rutgerkok.physicssimulation.shape.Material;
import nl.rutgerkok.physicssimulation.world.PhysicsWorld;

public final class SwingStartup {

    public static final int FPS = 60;

    public static void main(String... args) {
        JFrame window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("PhysicsSimulation");

        PhysicsWorld world = new PhysicsWorld();
        world.addObject(obj(circle(vec2(100, 100), 30), vec2(40, 0), Material.METAL));
        world.addObject(obj(circle(vec2(300, 100), 30), vec2(1, 5), Material.SUPERBALL));
        world.addObject(obj(rectangle(vec2(0, 400), vec2(700, 405)), vec2(0, 0), Material.WOOD));
        world.addObject(obj(rectangle(vec2(50, 500), vec2(200, 520)), vec2(5, -40), Material.ROCK));
        window.setContentPane(new DrawPanel(world));

        new Timer(1000 / FPS, event -> {
            world.advance(1 / (double) FPS);
            window.repaint();
        }).start();

        window.setVisible(true);
    }
}
