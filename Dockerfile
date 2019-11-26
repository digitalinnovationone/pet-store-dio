FROM openjdk:8-alpine

RUN apk update && apk add bash

RUN mkdir -p /opt/app
ENV PROJECT_HOME /opt/app

COPY build/libs/restSpring-0.0.1-SNAPSHOT.jar $PROJECT_HOME/springboot-mongo.jar

WORKDIR $PROJECT_HOME


CMD ["java", "-Dspring.data.mongodb.uri=mongodb://mongo:27017/pets","-Djava.security.egd=file:/dev/./urandom","-jar","./springboot-mongo.jar"]
