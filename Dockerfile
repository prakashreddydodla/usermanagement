FROM tomcat:8.0
USER root
COPY target/* /usr/local/tomcat/webapps/
