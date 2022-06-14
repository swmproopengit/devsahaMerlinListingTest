FROM amazoncorretto:11-alpine-jdk
MAINTAINER devsahamerlin
VOLUME /tmp
COPY target/ListingRestAPI-0.0.1.jar ListingRestAPI-0.0.1.jar
ENTRYPOINT ["java","-jar","/ListingRestAPI-0.0.1.jar"]
EXPOSE 8081