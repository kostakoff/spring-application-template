FROM docker.io/kostakoff/rocky-base-images:8-openjdk17

LABEL application=spring-application-template

COPY target/application.jar app.jar
RUN touch /etc/secrets/jsec-opts.txt

CMD java $JAVA_OPTS $JDEBUG_OPTS @/etc/secrets/jsec-opts.txt -jar app.jar
