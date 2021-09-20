FROM openjdk:11-jre-slim

COPY build/libs/gateway-api-1.0.jar gateway-api-1.0.jar

ENV APP_NAME=gateway-api

ENTRYPOINT ["java","-jar","/gateway-api-1.0.jar"]