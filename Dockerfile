FROM openjdk:8-jdk-alpine
EXPOSE 8081
ADD build/libs/JCLO-homework-1-0.0.1-SNAPSHOT-plain.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]