FROM maven:3-amazoncorretto-17 AS build
WORKDIR /app/

COPY . ./
#Install maven deps
RUN mvn -f ./pom.xml clean package
#
# Package stage
#
FROM amazoncorretto:17-alpine
WORKDIR /app-serv/
COPY --from=build /app/target/GovoriIgraya-0.0.1-SNAPSHOT.jar .
RUN chmod +x GovoriIgraya-0.0.1-SNAPSHOT.jar

# Expose the port your application will run on
EXPOSE 80

CMD ["java", "-jar", "GovoriIgraya-0.0.1-SNAPSHOT.jar"]