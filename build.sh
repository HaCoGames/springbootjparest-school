#!/bin/bash

# Package the application using Maven
mvn clean install -DskipTests=true
mvn package -DskipTests=true

# Build the Docker image
docker build -t business-day-backend .