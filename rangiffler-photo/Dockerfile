ARG DOCKER
FROM $DOCKER
ARG APP_VER
ENV VER=${APP_VER}
ENV JAVA_OPTS=""
WORKDIR /application
COPY rangiffler-photo-$VER.jar ./

EXPOSE 8086
CMD java -jar -Dspring.profiles.active=docker $JAVA_OPTS rangiffler-photo-$VER.jar
