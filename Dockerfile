FROM openjdk:21-jdk-oracle

ADD target/springbootjparest05-hafner-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]