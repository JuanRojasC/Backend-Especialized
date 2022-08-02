FROM openjdk:18-alpine

LABEL maintainer="Juan Rojas" description="Catalog microservices" version="1.0.0"

ARG APP_NAME=catalog-service
ARG APP_VERSION=1.0.0
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} catalog-service.jar
EXPOSE 9003

ENTRYPOINT ["java", "-jar", "catalog-service.jar"]