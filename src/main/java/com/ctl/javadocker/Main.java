package com.ctl.javadocker;

import javax.enterprise.inject.Vetoed;
import javax.servlet.ServletException;

import org.apache.deltaspike.core.api.config.ConfigResolver;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;

import io.undertow.Undertow;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;

@Vetoed
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
		
		int port = ConfigResolver.resolve("server.port").as(Integer.class).getValue();

		Undertow.Builder builder = Undertow.builder().addHttpListener(port, "0.0.0.0");

		server.start(builder);
		

	}
}
