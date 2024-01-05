FROM madiva/openjdk17:latest

WORKDIR /app
COPY ./gateway-service/target/gateway-service-0.0.1-SNAPSHOT.jar .


EXPOSE 8080

ENTRYPOINT ["java", "-jar", "gateway-service-0.0.1-SNAPSHOT.jar"]