FROM openjdk:17
MAINTAINER Jason
ADD target/bookServer-0.0.1-SNAPSHOT.jar book.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","book.jar"]
