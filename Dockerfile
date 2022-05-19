FROM tomcat:7.0.100-jdk8-openjdk
COPY target/pentadata-1.0.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
