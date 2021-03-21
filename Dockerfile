FROM openjdk:11
EXPOSE 8080
ARG JAR_FILE=build/libs/ms-notificacao-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} ms-notificacao.jar
ENTRYPOINT ["java", "-jar", "/ms-notificacao.jar"]