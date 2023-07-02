FROM adoptopenjdk/openjdk11

CMD ["./gradlew", "clean", "build"]

ARG USER_API_JAR_FILE_PATH=build/libs/*.jar

COPY ${USER_API_JAR_FILE_PATH} user-api.jar

ENTRYPOINT [ "java","-jar", "-Djasypt.encryptor.password=test","/user-api.jar" ]
