FROM openjdk:8
ARG JAR_FILE=build/libs/sorbet-0.0.1.jar
ENV RESOURCEPATH=/
COPY ${JAR_FILE} /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
