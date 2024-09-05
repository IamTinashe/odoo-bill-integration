FROM openjdk:17-jdk

WORKDIR /app

COPY target/odoo-bill-integration-0.0.1-SNAPSHOT.jar /app/odoo-bill-integration.jar

ENTRYPOINT ["java", "-jar", "/app/odoo-bill-integration.jar"]

EXPOSE 8080

