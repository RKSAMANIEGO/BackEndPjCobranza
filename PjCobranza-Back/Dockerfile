FROM tomcat:9.0-jdk11

RUN rm -rf /usr/local/tomcat/webapps/ROOT* && \
    mkdir -p /usr/local/tomcat/webapps/


COPY target/ROOT.war /usr/local/tomcat/webapps/ROOT.war



COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh


ENV SPRING_DATASOURCE_URL=jdbc:mysql://sql.freedb.tech:3306/freedb_pjCobranza?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
ENV SPRING_DATASOURCE_USERNAME=freedb_rkSam
ENV SPRING_DATASOURCE_PASSWORD=VR@%hZvzQ5%sqBg


HEALTHCHECK --interval=30s --timeout=10s --start-period=30s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

CMD ["/wait-for-it.sh","sql.freedb.tech:3306", "--timeout=120", "--", "catalina.sh", "run"]
