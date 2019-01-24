FROM openjdk:8-jdk-alpine
# VOLUME /tmp
ADD target/service-eureka-server-1.0.0-SNAPSHOT.jar app.jar
EXPOSE 8761
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
