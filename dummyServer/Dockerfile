FROM amd64/amazoncorretto:21
LABEL authors="sohyundoh"
WORKDIR /app
EXPOSE 8081
COPY ./build/libs/dummyServer-0.0.0-BOILERPLATE.jar /app/dummyServer.jar
ENTRYPOINT ["java", "-Xms512m", "-Xmx1024m", "-Duser.timezone=Asia/Seoul", "-jar", "/app/dummyServer.jar"]