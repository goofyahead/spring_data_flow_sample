### Runtime image ###
FROM openjdk:8-jre-alpine

# Volume for temp Spring Boot application files
VOLUME /tmp

# Name of the service
ARG SERVICE

ENV FC_LANG en-US
ENV LC_CTYPE en_US.UTF-8

# Install dependencies
RUN apk add curl

# Set the app working directory
WORKDIR /app

# Copy the executable JAR to the image
COPY /build/libs/*.jar /app

# Run the app
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom ${JAVA_OPTS} -jar *.jar