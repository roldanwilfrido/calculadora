FROM openjdk:11-jre-slim

COPY build/libs/registrador-api-1.0.jar registrador-api-1.0.jar

ENV APP_NAME=registrador-api

ENTRYPOINT ["java","-jar","/registrador-api-1.0.jar"]