FROM madiva/openjdk17:latest

WORKDIR /app
COPY ./microservice-pokedex/target/microservice-pokedex-0.0.1-SNAPSHOT.jar .

EXPOSE 7090

ENTRYPOINT ["java", "-jar", "microservice-pokedex-0.0.1-SNAPSHOT.jar"]
