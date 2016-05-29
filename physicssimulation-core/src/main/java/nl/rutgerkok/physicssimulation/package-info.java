/**
 * The main package, contains some general classes: start reading the
 * documentation here.
 *
 * <p>
 * The simulation consists of objects in a world. Each object has forces applied
 * to it. Based on that, it determines its position at the next step.
 * Supervisors exists to stop physically impossible things from happening in a
 * world, like two objects being at the same place.
 * </p>
 *
 * <p>
 * Almost all the code in the program is written without units. Some exceptions
 * exist: the gravity code for example assumes meters and seconds.
 * </p>
 *
 * <ul>
 * <li><a href="world/PhysicsSimulation.html">Learn how to create a simulation</a></li>
 * <li><a href="world/PhysicalObject.html">More about objects</a></li>
 * <li><a href="world/Supervisor.html">More about supervisors</a></li>
 * <li><a href="world/Force.html">More about forces</a></li>
 * </ul>
 *
 * <p>
 * You are free to write code that loops trhough a world and prints information
 * of all objects. There is also a built-in visualizer using Java Swing, see
 * <a href="swing/package-summary.html">the Swing package</a>.
 * </p>
 */
@NonNullByDefault
package nl.rutgerkok.physicssimulation;

import org.eclipse.jdt.annotation.NonNullByDefault;
