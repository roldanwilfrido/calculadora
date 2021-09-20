FROM openjdk:11-jre-slim

COPY build/libs/numeros-api-1.0.jar numeros-api-1.0.jar

ENV APP_NAME=numeros-api

ENTRYPOINT ["java","-jar","/numeros-api-1.0.jar"]