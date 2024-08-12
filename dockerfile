FROM eclipse-temurin:19.0.2_7-jre-focal
COPY target/*.jar opt/app/app.jar
#EXPOSE 8080
WORKDIR opt/app/
ENTRYPOINT ["java", "-jar", "app.jar"]
