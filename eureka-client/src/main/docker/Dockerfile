# Version 1.0.0
FROM frolvlad/alpine-oraclejdk8:slim

MAINTAINER gongtao

# 环境变量
#ENV WORK_PATH /home/project/dockerdemo
#ENV APP_NAME @project.build.finalName@.@project.packaging@
#ENV APP_VERSION @project.version@

# 开放端口
EXPOSE 8080

#VOLUME
VOLUME ["/home/project", "/tmp/data"]

#COPY
#COPY $APP_NAME $WORK_PATH/

# WORKDIR
#WORKDIR $WORK_PATH

# ENTRYPOINT
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom"]

# CMD
#CMD ["-jar", "@project.build.finalName@.@project.packaging@"]

ADD eureka-client-1.0.0.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

