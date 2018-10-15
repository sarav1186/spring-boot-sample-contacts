# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="sarav1186@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8484

# The application's jar file
ARG JAR_FILE=build/libs/contacts-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} contacts.jar
COPY build/libs/newrelic.jar .
COPY newrelic.yml .


# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-javaagent:newrelic.jar","-jar","/contacts.jar"]