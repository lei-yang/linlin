FROM openjdk:8-jdk-alpine

MAINTAINER Qihang

VOLUME /tmp

COPY target/linlin.jar app.jar

ENV JAVA_OPTS="-Duser.timezone=Asia/Shanghai"

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
