# Multi Stage build

# Base Image and name stage as "builder"
FROM maven:3-openjdk-11 AS builder

# Create App Directory inside our container
WORKDIR /workspace/app/src/

# Copy files
COPY src ./
COPY pom.xml ../

# ./mvnw clean package -DskipTests  eventuell so -Dmaven.test.skip=true
RUN mvn -f /workspace/app/pom.xml clean package -Dmaven.test.skip=true

#### 2nd Stage ####

FROM openjdk:11

WORKDIR /workspace/lib/

# Copy the Jar from the first Stage (builder) to the 2nd stage working directory
COPY --from=builder /workspace/app/target/storage-microservice-0.0.1-SNAPSHOT.jar ./storageapp.jar

# Expose the port to the inner container communication network
EXPOSE 3031

# Run the Java Application
ENTRYPOINT [ "java","-jar","/workspace/lib/storageapp.jar"]