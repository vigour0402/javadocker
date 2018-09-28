FROM openjdk:8-jre-alpine

WORKDIR ${application.workdir}

ENV MAX_HEAP "512m"
ENV JAVA_OPTS "-D${project.name}"
ENTRYPOINT [ "sh", "-c", "/usr/bin/java ${JAVA_OPTS} -Xmx${MAX_HEAP} -jar ${project.build.finalName}.jar" ]
EXPOSE 8080

COPY ${application.dependencies} ${application.dependencies}
COPY ${project.build.finalName}.jar ${project.build.finalName}.jar
