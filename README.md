# Echo Server

An Echo Server written in Java

## Instructions

Open your terminal, create a directory, and clone this repo into your new directory.

Change into the root directory.

`cd http-server`

Start the server by running either of the following commands: 

Connect to port 8080 automatically:

`./gradlew runServer`

Connects to given port:

`./gradlew runServer --args <port-number>`

To run the client, you can use any of the following commands (in a separate terminal):

Connects to localhost and port 8080 automatically:

`./gradlew runClient`

Connects to given host and port:

`./gradlew runClient --args="<host-name> <port-number>"`

Using Netcat:

`nc <host-name> <port-number>`

To run tests:

`./gradlew test`



