FROM openjdk

Workdir /app

copy target/agenda-0.0.1-SNAPSHOT.jar /app/agenda.jar

ENTRYPOINT ["java", "-jar", "agenda.jar"]