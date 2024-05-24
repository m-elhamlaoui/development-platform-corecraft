FROM openjdk:8-jdk-alpine

VOLUME /tmp

ADD target/user-service.jar user-service.jar

ENTRYPOINT ["java","-jar","/user-service.jar"]
