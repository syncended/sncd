FROM openjdk:21-ea-jdk-slim

# S3 settings
ARG HOST
ARG DATABASE_URL
ARG DATABASE_USER
ARG DATABASE_PASSWORD

ENV HOST $HOST
ENV PRODUCTION 'true'
ENV DATABASE_DRIVER 'org.postgresql.Driver'
ENV DATABASE_URL $DATABASE_URL
ENV DATABASE_USER $DATABASE_USER
ENV DATABASE_PASSWORD $DATABASE_PASSWORD

WORKDIR /service

COPY deploy/YaCA.crt YaCA.crt
COPY build/libs/service.jar service.jar
RUN keytool -import -noprompt -trustcacerts -alias YaRoot -file /service/YaCA.crt -keystore $JAVA_HOME/lib/security/cacerts -storepass changeit

CMD ["java", "-jar", "service.jar"]
