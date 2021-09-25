FROM openjdk:18-slim-buster
COPY ./target/taco-cloud-1.0.jar  /home/taco/taco-cloud-1.0.jar
CMD ["java", "-jar", "/home/taco/taco-cloud-1.0.jar"]
