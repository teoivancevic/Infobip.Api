# Build: docker build -t spring-boot-docker .
# Run: docker run -p 8080:8080 spring-boot-docker
#FROM openjdk:19-jdk-alpine
FROM openjdk:19
#copy the jar file to the container
COPY target/*.jar app.jar
#run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]
#expose the port
EXPOSE 8080

