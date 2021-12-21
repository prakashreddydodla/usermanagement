
FROM openjdk:8
#ARG JAR_FILE=connection-pool/target/*.jar 
COPY  target/*  target/
RUN  mv target/*.jar   target/usermanagement.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=cloud", "-jar", "target/usermanagement.jar"]
