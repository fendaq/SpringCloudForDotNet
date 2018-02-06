FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD config-service.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
