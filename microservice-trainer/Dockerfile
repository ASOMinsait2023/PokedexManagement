FROM madiva/openjdk17:latest

# Copia todos los archivos necesarios
WORKDIR /app
COPY ./microservice-trainer/target/microservice-trainer-0.0.1-SNAPSHOT.jar .
#Empaquetar la app

EXPOSE 7080

ENTRYPOINT ["java", "-jar", "microservice-trainer-0.0.1-SNAPSHOT.jar"]