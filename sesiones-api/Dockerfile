FROM openjdk:11-jre-slim

COPY build/libs/sesiones-api-1.0.jar sesiones-api-1.0.jar

ENV APP_NAME=sesiones-api

ENTRYPOINT ["java","-jar","/sesiones-api-1.0.jar"]