ARG DOCKER
FROM $DOCKER
ARG APP_VER
ENV VER=${APP_VER}
ENV JAVA_OPTS=""
WORKDIR /application
COPY rangiffler-userdata-$VER.jar ./

EXPOSE 8089
CMD java -jar -Dspring.profiles.active=docker $JAVA_OPTS rangiffler-userdata-$VER.jar
