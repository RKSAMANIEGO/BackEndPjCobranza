FROM tomcat:9.0-jdk11

# Limpieza y configuración
RUN rm -rf /usr/local/tomcat/webapps/ROOT* && \
    mkdir -p /usr/local/tomcat/webapps/ROOT

# Copia la aplicación
COPY target/PjCobranza-Back-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Script de espera mejorado
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# Establecer las variables de entorno para la base de datos remota
ENV SPRING_DATASOURCE_URL=jdbc:mysql://sql105.infinityfree.com:3306:3306/if0_38689568_pjcobranza?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
ENV SPRING_DATASOURCE_USERNAME=if0_38689568
ENV SPRING_DATASOURCE_PASSWORD=blXQhYNQkPVf

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=30s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

CMD ["/wait-for-it.sh","sql105.infinityfree.com:3306", "--timeout=120", "--", "catalina.sh", "run"]


