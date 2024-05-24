FROM openjdk:8-jdk-alpine

VOLUME /tmp

ADD target/stats-service.jar stats-service.jar

ENTRYPOINT ["java","-jar","/stats-service.jar"]
