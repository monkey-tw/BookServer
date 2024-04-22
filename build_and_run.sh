#!/bin/bash

echo "Start building the bookServer"
./mvnw clean package
echo "Building the bookServer Done"

run_with_jar() {
    echo "Running the bookServer with jar"
    java  -jar ./target/bookServer-0.0.1-SNAPSHOT.jar 
}

check_image_exists() {
    local image_name="$1"
    if docker images | grep -wq "$image_name"; then
        return 0
    else
        return 1
    fi
}

run_with_docker() {
    echo "Running the bookServer with docker"
    if check_image_exists "my-book"; then
        echo "Image exists, Docker run image"
        docker run -p 8080:8080 my-book
    else
        echo "Image does not exist, building docker image"
        docker build -t my-book .
        echo "Docker run image"
        docker run -p 8080:8080 my-book
    fi
}

if ! command -v docker &> /dev/null; then
    run_with_jar
else
    run_with_docker
fi


