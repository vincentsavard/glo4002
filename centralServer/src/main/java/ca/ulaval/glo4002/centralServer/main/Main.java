package ca.ulaval.glo4002.centralServer.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.sun.jersey.spi.container.servlet.ServletContainer;

public class Main {

	private static int PORT = 8080;

	public static void main(String[] args) throws Exception {

		Server server = new Server(PORT);
		ServletContextHandler servletContextHandler = new ServletContextHandler(
				server, "/");
		ServletHolder jerseyServletHolder = new ServletHolder(
				ServletContainer.class);
		jerseyServletHolder.setInitParameter(
				"com.sun.jersey.config.property.resourceConfigClass",
				"com.sun.jersey.api.core.PackagesResourceConfig");
		jerseyServletHolder.setInitParameter(
				"com.sun.jersey.config.property.packages",
				"ca.ulaval.glo4002.centralServer.rest");
		servletContextHandler.addServlet(jerseyServletHolder, "/*");
		server.start();

	}
}
