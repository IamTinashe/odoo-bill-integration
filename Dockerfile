FROM openjdk:17-jdk

ARG artifact=target/odoo-bill-integration-0.0.1-SNAPSHOT.jar

WORKDIR /app

COPY ${artifact} odoo-bill-integration.jar

# This should not be changed
ENTRYPOINT ["java","-jar","odoo-bill-integration.jar"]

EXPOSE 8090

