# Version 1.0.0
FROM frolvlad/alpine-oraclejdk8:slim

MAINTAINER gongtao

ADD zipkin-service-1.0.0.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]