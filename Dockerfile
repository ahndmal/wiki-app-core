FROM gradle:7.5 AS GR_BUILD
COPY . .
RUN gradle bootJar

FROM amazoncorretto:17-alpine
COPY --from=GR_BUILD /home/gradle/build/libs/*.jar ./app.jar
CMD ["/bin/sh", "-c", "java -jar app*.jar"]