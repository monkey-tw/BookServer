#!/bin/bash

echo "Start building the bookServer"
 ./mvnw clean package
 echo "Building the bookServer Done"

echo "Running the bookServer"
 java  -jar ./target/bookServer-0.0.1-SNAPSHOT.jar 