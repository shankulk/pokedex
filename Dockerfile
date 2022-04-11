FROM gradle:7.4.2-jdk11-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:11
VOLUME /tmp
COPY --from=build /home/gradle/src/build/libs/pokedex-1.0.0.jar pokedex.jar
ENTRYPOINT ["java","-jar","/pokedex.jar"]