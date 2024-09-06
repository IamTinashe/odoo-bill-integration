FROM openjdk:17-jdk

ARG artifact=target/odoo-bill-integration-0.0.1-SNAPSHOT.jar

WORKDIR /app

COPY ${artifact} odoo-bill-integration.jar


ENTRYPOINT ["java","-jar","odoo-bill-integration.jar"]

CMD ["tail", "-f", "/dev/null"]
HEALTHCHECK --interval=30s --timeout=10s --retries=3 CMD curl --fail http://localhost:8090/health || exit 1

EXPOSE 8090

