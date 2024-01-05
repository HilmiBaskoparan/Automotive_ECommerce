# Dockerfile

# https://www.nginx.com/
# https://hub.docker.com/_/nginx

FROM openjdk:17
LABEL maintainer="My Automotive E-Commerce Web Site Project"
VOLUME /tmp
ARG JAR_FILE_PATH=Automotive_ECommerce-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE_PATH} auto.jar
ENTRYPOINT ["java", "-jar", "/auto.jar"]
EXPOSE 4040 9999
CMD ["cmd 1"]