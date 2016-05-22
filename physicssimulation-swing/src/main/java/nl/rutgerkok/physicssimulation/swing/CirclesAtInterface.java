package nl.rutgerkok.physicssimulation.swing;

import static nl.rutgerkok.physicssimulation.shape.Circle.circle;
import static nl.rutgerkok.physicssimulation.shape.Rectangle.rectangle;
import static nl.rutgerkok.physicssimulation.vector.Vector.vec2;
import static nl.rutgerkok.physicssimulation.world.PhysicalObject.obj;

import java.util.function.Supplier;

import nl.rutgerkok.physicssimulation.collision.CollisionSupervisor;
import nl.rutgerkok.physicssimulation.force.Forces;
import nl.rutgerkok.physicssimulation.shape.Material;
import nl.rutgerkok.physicssimulation.world.PhysicsWorld;
import nl.rutgerkok.physicssimulation.world.WorldBuilder;

final class CirclesAtInterface implements Supplier<PhysicsWorld> {

    private static final int VERT_SIZE = 23;
    private static final int HOR_SIZE = 30;

    private final WorldBuilder atInterface = WorldBuilder.newWorld()

            // Circles
            .withObject(obj(circle(vec2(0, 0), 2), vec2(0, 0), Material.ROCK))
            .withObject(obj(circle(vec2(5, 0), 2), vec2(0, 0), Material.ROCK))
            .withObject(obj(circle(vec2(5.1, 6), 2), vec2(0, 0), Material.ROCK))
            .withObject(obj(circle(vec2(-5, 10), 2), vec2(0, 0), Material.ROCK))

            // Walls
            .withObject(obj(rectangle(vec2(-HOR_SIZE, -VERT_SIZE), vec2(HOR_SIZE, -VERT_SIZE + 1)),
                    vec2(0, 0), Material.STATIC))
            .withObject(obj(rectangle(vec2(-HOR_SIZE, -VERT_SIZE + 1), vec2(-HOR_SIZE + 1, VERT_SIZE - 1)),
                    vec2(0, 0), Material.STATIC))
            .withObject(obj(rectangle(vec2(-HOR_SIZE, VERT_SIZE - 1), vec2(HOR_SIZE, VERT_SIZE)),
                    vec2(0, 0), Material.STATIC))
            .withObject(obj(rectangle(vec2(HOR_SIZE - 1, -VERT_SIZE + 1), vec2(HOR_SIZE, VERT_SIZE - 1)),
                    vec2(0, 0), Material.STATIC))

            .withSupervisor(new CollisionSupervisor())
            .withForce(Forces.lennardJones(1, 6))
            .withForce(Forces.GRAVITY);;

    @Override
    public PhysicsWorld get() {
        return atInterface.create();
    }

    @Override
    public String toString() {
        return "2D interface with circles";
    }

}
