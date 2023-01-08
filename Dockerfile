FROM openjdk:11
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} life-sports-discovery-management
ENTRYPOINT ["java", "-jar", "/life-sports-discovery-management"]