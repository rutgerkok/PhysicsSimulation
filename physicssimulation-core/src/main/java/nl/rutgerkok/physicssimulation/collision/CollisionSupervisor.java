package nl.rutgerkok.physicssimulation.collision;

import nl.rutgerkok.physicssimulation.vector.Vector;
import nl.rutgerkok.physicssimulation.world.PhysicalObject;
import nl.rutgerkok.physicssimulation.world.PhysicsWorld;
import nl.rutgerkok.physicssimulation.world.Supervisor;

public final class CollisionSupervisor implements Supervisor {

    private CollisionChecker collisionChecker = new CollisionChecker();

    @Override
    public void check(PhysicsWorld world) {
        collisionChecker.getCollisions(world).forEach(this::resolveCollision);
    }

    private void resolveCollision(Collision collision) {
        PhysicalObject a = collision.getOneObject();
        PhysicalObject b = collision.getOtherObject();
        System.out.println("Resolving for " + (1 / a.invertedMass) + " vs " + (1 / b.invertedMass));

        // Calculate relative velocity
        Vector velocityDifference = b.getVelocity().minus(a.getVelocity());

        // Calculate relative velocity in terms of the normal direction
        double velAlongNormal = velocityDifference.dotProduct(collision.getNormal());

        // Do not resolve if velocities are separating
        if (velAlongNormal > 0) {
            return;
        }

        // Calculate restitution
        double bouncyness = Math.min(a.getMaterial().restitution, b.getMaterial().restitution);

        // Calculate impulse scalar
        double impulseLength = -(1 + bouncyness) * velAlongNormal / (a.invertedMass + b.invertedMass);

        // Apply impulse
        Vector impulse = collision.getNormal().multiply(impulseLength);

        // Update speeds
        this.changeVelocity(a, a.getVelocity().minus(impulse.multiply(a.invertedMass)));
        this.changeVelocity(b, b.getVelocity().plus(impulse.multiply(b.invertedMass)));
    }

}
