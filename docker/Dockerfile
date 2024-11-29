FROM bellsoft/liberica-openjdk-alpine
WORKDIR opt

COPY product-management-2_4_0-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/opt/app.jar"]