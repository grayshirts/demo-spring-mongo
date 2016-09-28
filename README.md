Spring Demo API
===============

REST project made with Spring Boot and MongoDB, with simple CRUD examples
and a Left Join example with MongoDB 3.2 new `$lookup` operator supported
by Morphia 1.2.


System Requirements
-------------------

For development purpose, you need installed:

 * JDK 8+
 * Maven 3.3+
 * MongoDB 3.2+


Build & Run
-----------

To run the application in a development or production environment, first ensure that
the MongoDB instance configured in the ``src/main/resources/application.yml`` file is running.

Then build:

    mvn package

After build, run with the default environment ("dev" profile) and in the default
port (5000) with:

    java -jar target/demo-0.0.1-SNAPSHOT.jar

To run it in another port and with another environment profile:

    java -Dspring.profiles.active=production -jar target/demo-0.0.1-SNAPSHOT.jar --server.port=9000

In development environment, to avoid package the application each time you make changes,
it's recommended to use an IDE, or compile and launch with the *Spring Maven plugin*:

    mvn spring-boot:run

### Hot swapping

There are several options for hot reloading. In development environments the best approach
is use an IDE (especially with debugging on). If you run from console with the Maven plugin,
to reload the classes after a source change, execute from another console `mvn compile`.

A workaround to do this automatically is execute also from another console just one
time:

    $ find -name *.java | entr mvn compile


Access to the application
-------------------------

You can get for example all the orders from the ``/orders`` *REST* service within the
console with:

    curl http://localhost:5000/orders/


--
(2016) Grayshirts
