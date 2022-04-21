
FROM openjdk:8
COPY  target/*  target/
RUN  mv target/*.jar   target/usermanagement.jar

RUN apt install curl unzip -y 
RUN curl -O https://download.newrelic.com/newrelic/java-agent/newrelic-agent/current/newrelic-java.zip
RUN unzip newrelic-java.zip  
RUN mv newrelic  target/newrelic
RUN add-apt-repository ppa:rmescandon/yq 
RUN apt-get install yq -y

ENV NEW_RELIC_APP_NAME="UserManagement"
ENV NEW_RELIC_LICENSE_KEY="c3d4c03b07732ef5b79ca544334c54bfFFFFNRAL"
ENV NEW_RELIC_LOG_FILE_NAME="STDOUT"

# Replace License key and application name 
RUN sed -i "s/'<%= license_key %>'/'c3d4c03b07732ef5b79ca544334c54bfFFFFNRAL'/g" target/newrelic/newrelic.yml
RUN sed -i "s/app_name\: My Application/app_name\: usermanagement/g" target/newrelic/newrelic.yml
RUN yq w target/newrelic/newrelic.yml "common.jfr.enabled" "true"

# clean up 
RUN apt remove unzip curl jq -y
RUN rm -rf newrelic-java.zip 

ENTRYPOINT ["java","-javaagent:target/newrelic/newrelic.jar", "-Dspring.profiles.active=cloud", "-jar", "target/usermanagement.jar"]
