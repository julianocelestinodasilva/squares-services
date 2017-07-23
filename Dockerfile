FROM java:8

# Install maven
RUN apt-get update
RUN apt-get install maven -y

WORKDIR ./squares-service

ENV PORT 4567

# Prepare by downloading dependencies
ADD pom.xml /squares-service/pom.xml
RUN ["mvn", "dependency:resolve"]
RUN ["mvn", "verify"]

# Adding source, compile and package into a fat jar
ADD src /squares-service/src
RUN ["mvn", "clean"]
RUN ["mvn", "package"]

EXPOSE 4567
CMD ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-jar", "target/squares-service.jar"]

