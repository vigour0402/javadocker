package com.ctl.javadocker;

import javax.servlet.ServletException;

import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;

import io.undertow.Undertow;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.server.handlers.resource.ResourceHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import static io.undertow.Handlers.resource;

public class Main {
	public static void main(String[] args) throws ServletException {

		UndertowJaxrsServer server = new UndertowJaxrsServer();

		ResteasyDeployment deployment = new ResteasyDeployment();
		deployment.setApplicationClass(RsApplication.class.getName());
		deployment.setInjectorFactoryClass("org.jboss.resteasy.cdi.CdiInjectorFactory");

		DeploymentInfo deploymentInfo = server.undertowDeployment(deployment, "/");
		deploymentInfo.setClassLoader(Thread.currentThread().getContextClassLoader());
		deploymentInfo.setDeploymentName("Undertow + Resteasy example");
		deploymentInfo.setContextPath("/api");

		deploymentInfo.addListener(Servlets.listener(org.jboss.weld.environment.servlet.Listener.class));

		server.deploy(deploymentInfo);

		Undertow.Builder builder = Undertow.builder().addHttpListener(8080, "localhost");

		server.start(builder);

	}
}
