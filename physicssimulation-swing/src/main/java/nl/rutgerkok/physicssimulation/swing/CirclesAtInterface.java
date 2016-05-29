package nl.rutgerkok.physicssimulation.swing;

import static nl.rutgerkok.physicssimulation.shape.Circle.circle;
import static nl.rutgerkok.physicssimulation.shape.Rectangle.rectangle;
import static nl.rutgerkok.physicssimulation.vector.Vector.vec2;
import static nl.rutgerkok.physicssimulation.world.PhysicalObject.obj;

import java.util.Random;
import java.util.function.Supplier;

import nl.rutgerkok.physicssimulation.collision.CollisionSupervisor;
import nl.rutgerkok.physicssimulation.force.Forces;
import nl.rutgerkok.physicssimulation.shape.Material;
import nl.rutgerkok.physicssimulation.world.PhysicsSimulation;
import nl.rutgerkok.physicssimulation.world.WorldBuilder;

final class CirclesAtInterface implements Supplier<PhysicsSimulation> {

    private static final int VERT_SIZE = 23;
    private static final int HOR_SIZE = 30;
    private static final double INCREMENT = 5.1;

    private final WorldBuilder atInterface;

    CirclesAtInterface() {
        atInterface = WorldBuilder.newWorld();

        // Circles
        Random random = new Random(0xABC);
        for (double x = -HOR_SIZE + INCREMENT; x <= HOR_SIZE - INCREMENT; x += INCREMENT) {
            for (double y = -VERT_SIZE + INCREMENT; y <= VERT_SIZE - INCREMENT; y += INCREMENT) {
                atInterface.withObject(obj(circle(vec2(x, y), 2), vec2(random.nextInt(10) - 5, random.nextInt(10) - 5),
                        Material.ROCK));
            }
        }

        // Walls
        atInterface
                .withObject(obj(rectangle(vec2(-HOR_SIZE, -VERT_SIZE), vec2(HOR_SIZE, -VERT_SIZE + 1)),
                        vec2(0, 0), Material.STATIC))
                .withObject(obj(rectangle(vec2(-HOR_SIZE, -VERT_SIZE + 1), vec2(-HOR_SIZE + 1, VERT_SIZE - 1)),
                        vec2(0, 0), Material.STATIC))
                .withObject(obj(rectangle(vec2(-HOR_SIZE, VERT_SIZE - 1), vec2(HOR_SIZE, VERT_SIZE)),
                        vec2(0, 0), Material.STATIC))
                .withObject(obj(rectangle(vec2(HOR_SIZE - 1, -VERT_SIZE + 1), vec2(HOR_SIZE, VERT_SIZE - 1)),
                        vec2(0, 0), Material.STATIC))

                .withSupervisor(new CollisionSupervisor())
                .withForce(Forces.lennardJones(1.5, 6).restrictTo(Material.ROCK));
    }

    @Override
    public PhysicsSimulation get() {
        return atInterface.create();
    }

    @Override
    public String toString() {
        return "2D interface with circles";
    }

}
