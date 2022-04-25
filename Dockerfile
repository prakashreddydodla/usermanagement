
FROM openjdk:8
COPY  target/*  target/
RUN  mv target/*.jar   target/usermanagement.jar

RUN apt install curl unzip -y 
RUN curl -O https://download.newrelic.com/newrelic/java-agent/newrelic-agent/current/newrelic-java.zip
RUN unzip newrelic-java.zip  
RUN mv newrelic  target/newrelic
COPY newrelic.yml  target/newrelic/newrelic.yml

ENV NEW_RELIC_APP_NAME="UserManagement"
ENV NEW_RELIC_LICENSE_KEY="4dacb6025e2eadaedd6562a3ea9f48d5FFFFNRAL"
ENV NEW_RELIC_LOG_FILE_NAME="STDOUT"

# clean up 
RUN apt remove unzip curl -y
RUN rm -rf newrelic-java.zip 

ENTRYPOINT ["java","-javaagent:target/newrelic/newrelic.jar", "-Dspring.profiles.active=cloud", "-jar", "target/usermanagement.jar"]
