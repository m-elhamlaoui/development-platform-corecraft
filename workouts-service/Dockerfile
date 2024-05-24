FROM openjdk:8-jdk-alpine

VOLUME /tmp

ADD target/workouts-service.jar workouts-service.jar

ENTRYPOINT ["java","-jar","/workouts-service.jar"]
