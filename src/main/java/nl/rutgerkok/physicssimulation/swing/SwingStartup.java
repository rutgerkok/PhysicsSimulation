package nl.rutgerkok.physicssimulation.swing;

import static nl.rutgerkok.physicssimulation.shape.Sphere.sphere;
import static nl.rutgerkok.physicssimulation.vector.Vector.vec3;
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
        world.addObject(obj(sphere(vec3(0, 1, -10), 2), vec3(0, 0, 6), Material.METAL));
        world.addObject(obj(sphere(vec3(0, 0, 10), 2), vec3(0, 2, -6), Material.METAL));
        world.addObject(obj(sphere(vec3(0, 20, 0), 3), vec3(0, -5, 0), Material.METAL));
        window.setContentPane(new DrawPanel(world));

        new Timer(1000 / FPS, event -> {
            world.advance(1 / (double) FPS);
            window.repaint();
        }).start();

        window.setVisible(true);
    }
}
