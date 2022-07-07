FROM openjdk:8
ARG JAR_FILE=build/libs/be-0.0.1.jar
ENV RESOURCEPATH=/
COPY ${JAR_FILE} /app.jar
COPY lucene/indexes /lucene/indexes
COPY images /images
COPY src/main/resources/myCertificate.crt /usr/local/share/ca-certificates/ 
COPY src/main/resources/myCertificate.crt $JAVA_HOME/jre/lib/security/
RUN cat  /usr/local/share/ca-certificates/myCertificate.crt >> /etc/ssl/certs/ca-certificates.crt
RUN keytool -keystore $JAVA_HOME/jre/lib/security/cacerts -storepass changeit -noprompt -trustcacerts -importcert -alias spring -file /usr/local/share/ca-certificates/myCertificate.crt
ENTRYPOINT ["java", "-jar", "/app.jar"]
