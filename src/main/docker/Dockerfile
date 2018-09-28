FROM openjdk:8-jre-alpine

WORKDIR ${application.workdir}

ENV MAX_HEAP "512m"
ENV JAVA_OPTS ""
ENTRYPOINT [ "sh", "-c", "/usr/bin/java -D${project.name} -Xmx${MAX_HEAP} ${JAVA_OPTS} -jar ${project.build.finalName}.jar" ]
EXPOSE 8080

COPY ${application.dependencies} ${application.dependencies}
COPY ${project.build.finalName}.jar ${project.build.finalName}.jar
