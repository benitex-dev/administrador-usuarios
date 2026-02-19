FROM eclipse-temurin:17-jdk-jammy
ARG JAR_FILE=target/administrador-usuarios-0.0.1.jar
COPY ${JAR_FILE} app_administrador_usuarios.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","app_administrador_usuarios.jar"]