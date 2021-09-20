FROM openjdk:11-jre-slim

COPY build/libs/calculos-api-1.0.jar calculos-api-1.0.jar

ENV APP_NAME=calculos-api

ENTRYPOINT ["java","-jar","/calculos-api-1.0.jar"]