FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD agile-tool-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongodb/agile-tool","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]