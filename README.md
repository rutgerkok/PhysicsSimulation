# PhysicsSimulation
A simple simulator of physical forces and collisions. Created to learn something
about the simulation of physics.

## Features
* World with moving objects.
* Elastic collision handling.
* Apply forces to objects.
* Both 2D and 3D are supported.
* Example application written using Java Swing is included.

## Documentation
The JavaDocs are the documentation. If you want to view the JavaDocs as a
collection of webpages, you can do the following:

1. [Download and install Maven](https://maven.apache.org/).
2. Download the source code.
3. In the root directory of the source code, run `mvn javadoc:javadoc`.
4. Go to the directory `target/site/apidocs`.
5. Open the `index.html` file in that directory to view the documentation.

## Compiling
1. [Download and install Maven](https://maven.apache.org/).
2. Download the source code.
3. In the root directory of the source code, run `mvn install`.

You will end up with a JAR file in the newly-created `target` directory called
something like `physicssimulation-VERSION.jar`. This is a runnable JAR
file.

Alternatively, you can import this project into any Java IDE as a Maven project.
You can then use the compile options of the IDE.
