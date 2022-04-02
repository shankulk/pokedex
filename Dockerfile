FROM openjdk:11
VOLUME /tmp
COPY build/libs/pokedex-1.0.0.jar pokedex.jar
ENTRYPOINT ["java","-jar","/pokedex.jar"]