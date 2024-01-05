FROM mysql/8.0.34

WORKDIR /app

COPY ./config-server/target/config-server-0.0.1-SNAPSHOT.jar .

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "config-server-0.0.1-SNAPSHOT.jar"]