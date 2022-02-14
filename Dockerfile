FROM openjdk:8-jre-alpine

WORKDIR ${application.workdir}

ENV MAX_HEAP "512m"
ENV ZK_LIST ""
ENV ZK_CONFIG_PATH ""
ENV JAVA_OPTS ""
ENTRYPOINT [ "sh", "-c", "/usr/bin/java -D${project.name} -Xmx${MAX_HEAP} -Dcom.ctl.configsource.zookeeper.url=${ZK_LIST} -Dcom.ctl.configsource.zookeeper.applicationId=${ZK_CONFIG_PATH} ${JAVA_OPTS} -jar ${project.build.finalName}.jar" ]
EXPOSE 8080

COPY ${application.dependencies} ${application.dependencies}
COPY ${project.build.finalName}.jar ${project.build.finalName}.jar
