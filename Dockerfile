FROM amazoncorretto:11-alpine-jdk
MAINTAINER devsahamerlin
VOLUME /tmp
COPY target/360agency-listing-0.0.1.jar 360agency-listing-0.0.1.jar
ENTRYPOINT ["java","-jar","/360agency-listing-0.0.1.jar"]
EXPOSE 8081