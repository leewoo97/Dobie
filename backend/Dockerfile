FROM azul/zulu-openjdk:17

ARG JAR_FILE=build/libs/*.jar

WORKDIR /usr/app/

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
