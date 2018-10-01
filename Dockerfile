FROM java:8
VOLUME /tmp
ADD target/AppointmentProject-1.0.0.jar app.jar
EXPOSE 8080
ENV MONGO_DB_NAME appointments
ENV MONGO_DB_HOST mongo
ENV MONGO_DB_PORT 27017
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]