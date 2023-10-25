FROM openjdk:11-jdk
LABEL maintainer="dsa05037@naver.com"
COPY ./static/images/structures /app/static/images/structures/
ARG JAR_FILE=build/libs/pnutour-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} docker-springboot-pnutour.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/docker-springboot-pnutour.jar"]