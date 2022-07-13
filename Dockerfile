FROM openjdk:8
ARG JAR_FILE=build/libs/be-0.0.1.jar
ENV RESOURCEPATH=/
COPY ${JAR_FILE} /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
