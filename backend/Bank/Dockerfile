FROM openjdk:21-jdk
COPY . .
RUN ./mvnw clean package -DskipTests
CMD ["java","-jar","target/Bank-0.0.1-SNAPSHOT.jar"]