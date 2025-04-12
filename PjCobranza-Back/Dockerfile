#FROM tomcat:9.0-jdk11    															PARA WAR
FROM eclipse-temurin:17-jdk-alpine
RUN apk add --no-cache bash
WORKDIR /app

#RUN rm -rf /usr/local/tomcat/webapps/ROOT* && \                                    PARA WAR
#    mkdir -p /usr/local/tomcat/webapps/


#COPY target/ROOT.war /usr/local/tomcat/webapps/ROOT.war							PARA WAR
COPY target/ROOT.jar app.jar


COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh


ENV SPRING_DATASOURCE_URL=jdbc:mysql://sql10.freesqldatabase.com:3306/sql10772773?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
ENV SPRING_DATASOURCE_USERNAME=sql10772773
ENV SPRING_DATASOURCE_PASSWORD=8TaLhVTtiK



HEALTHCHECK --interval=30s --timeout=10s --start-period=30s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

#CMD ["/wait-for-it.sh","sql10.freesqldatabase.com:3306", "--timeout=120", "--", "catalina.sh", "run"]    PARA WAR
CMD ["/wait-for-it.sh", "sql10.freesqldatabase.com:3306", "--timeout=120", "--", "java", "-jar", "app.jar"]
