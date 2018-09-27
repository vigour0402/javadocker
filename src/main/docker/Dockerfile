FROM openjdk:8-jre-alpine

WORKDIR ${application.workdir}
COPY ${application.dependencies} ${application.dependencies}
COPY ${project.build.finalName}.jar ${project.build.finalName}.jar

ENV JAVA_OPTS ""
ENTRYPOINT ["/usr/bin/java", "$JAVA_OPTS", "-jar", "${project.build.finalName}.jar"]
EXPOSE 8080