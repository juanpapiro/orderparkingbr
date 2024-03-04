FROM openjdk:17-alpine

ADD target/orderparkingbr-0.0.1.jar orderparkingbr.jar

ENTRYPOINT [ "java", "-jar",  "/orderparkingbr.jar"]

EXPOSE 8002