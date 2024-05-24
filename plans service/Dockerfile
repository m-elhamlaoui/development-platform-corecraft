FROM openjdk:8-jdk-alpine

VOLUME /tmp

ADD target/plans-service.jar plans-service.jar

ENTRYPOINT ["java","-jar","/plans-service.jar"]



