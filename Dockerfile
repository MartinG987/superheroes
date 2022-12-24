FROM openjdk:11-jdk-alpine
COPY "./target/superheroes-1.0.jar" "app.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]