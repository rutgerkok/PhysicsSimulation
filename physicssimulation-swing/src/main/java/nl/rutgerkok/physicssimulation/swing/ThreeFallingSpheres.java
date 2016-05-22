package nl.rutgerkok.physicssimulation.swing;

import static nl.rutgerkok.physicssimulation.shape.Sphere.sphere;
import static nl.rutgerkok.physicssimulation.vector.Vector.vec3;
import static nl.rutgerkok.physicssimulation.world.PhysicalObject.obj;

import java.util.function.Supplier;

import nl.rutgerkok.physicssimulation.collision.CollisionSupervisor;
import nl.rutgerkok.physicssimulation.force.Forces;
import nl.rutgerkok.physicssimulation.shape.Material;
import nl.rutgerkok.physicssimulation.world.PhysicsWorld;
import nl.rutgerkok.physicssimulation.world.WorldBuilder;

public class ThreeFallingSpheres implements Supplier<PhysicsWorld> {

    private final WorldBuilder threeFallingSpheres = WorldBuilder.newWorld()
            .withObject(obj(sphere(vec3(0, 1, -10), 2), vec3(0, 0, 6), Material.METAL))
            .withObject(obj(sphere(vec3(0, 0, 10), 2), vec3(0, 2, -6), Material.METAL))
            .withObject(obj(sphere(vec3(0, 20, 0), 3), vec3(0, -5, 0), Material.METAL))
            .withSupervisor(new CollisionSupervisor())
            .withForce(Forces.GRAVITY);

    @Override
    public PhysicsWorld get() {
        return threeFallingSpheres.create();
    }

    @Override
    public String toString() {
        return "Three falling spheres";
    }
}
