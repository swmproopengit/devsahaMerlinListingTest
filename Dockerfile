FROM amazoncorretto:11-alpine-jdk
MAINTAINER devsahamerlin
VOLUME /tmp
ADD target/ListingRestAPI.jar ListingRestAPI.jar
ENTRYPOINT ["java","-jar","/ListingRestAPI.jar"]
EXPOSE 8081