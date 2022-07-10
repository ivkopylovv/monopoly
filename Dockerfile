
FROM openjdk:latest

VOLUME /project_data

WORKDIR /monopoly

ADD /build/libs/*.jar app.jar

EXPOSE 8090

ENTRYPOINT ["java","-jar","app.jar"]