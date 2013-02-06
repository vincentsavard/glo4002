package ca.ulaval.glo4002.centralServer.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.sun.jersey.spi.container.servlet.ServletContainer;

public class CentralServer {

	private static int PORT = 8080;
	private static String packageName = "ca.ulaval.glo4002.centralServer.rest";
	private static String contextPath = "/";
	private static String pathSpec = "/*";
	
	private static Server server;

	public static void startServer() throws Exception {
		server = new Server(PORT);
		ServletContextHandler servletContextHandler;
		ServletHolder jerseyServletHolder;
		
		servletContextHandler = new ServletContextHandler(server, contextPath);
		jerseyServletHolder = createJerseyServletHolder(packageName);
		servletContextHandler.addServlet(jerseyServletHolder, pathSpec);
		server.start();
	}
	
	public static ServletHolder createJerseyServletHolder(String packageName) {
		ServletHolder jerseyServletHolder = new ServletHolder(
				ServletContainer.class);
		jerseyServletHolder.setInitParameter(
				"com.sun.jersey.config.property.resourceConfigClass",
				"com.sun.jersey.api.core.PackagesResourceConfig");
		jerseyServletHolder.setInitParameter(
				"com.sun.jersey.config.property.packages",
				packageName);
		return jerseyServletHolder;
	}

	public static void stopServer() throws Exception {
		server.stop();
	}
}
