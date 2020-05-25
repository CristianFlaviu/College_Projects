FROM openjdk:10

# Refer to Maven build -> finalName
ARG JAR_FILE=target/sd-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} firstApp.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","firstApp.jar"]