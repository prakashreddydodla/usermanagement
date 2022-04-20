
FROM openjdk:8
COPY  target/*  target/
RUN  mv target/*.jar   target/usermanagement.jar
RUN apt install curl unzip -y 
RUN curl -O https://download.newrelic.com/newrelic/java-agent/newrelic-agent/current/newrelic-java.zip
RUN unzip newrelic-java.zip  target/

ENV NEW_RELIC_APP_NAME="UserManagement"
ENV NEW_RELIC_LICENSE_KEY="c3d4c03b07732ef5b79ca544334c54bfFFFFNRAL"
ENV NEW_RELIC_LOG_FILE_NAME="STDOUT"

ENTRYPOINT ["java","-javaagent:target/newrelic.jar", "-Dspring.profiles.active=cloud", "-jar", "target/usermanagement.jar"]
