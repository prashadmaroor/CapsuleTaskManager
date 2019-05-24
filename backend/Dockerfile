FROM openjdk:8
ADD target/backend-0.0.1-SNAPSHOT.war capsulebackend.war
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "capsulebackend.war"] 