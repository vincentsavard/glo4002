package ca.ulaval.glo4002.centralServer.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.sun.jersey.spi.container.servlet.ServletContainer;

public class CentralServer {

    private int PORT = 8080;
    private String PARAM_RESSOURCE_CONFIG_CLASS = "com.sun.jersey.config.property.resourceConfigClass";
    private String PARAM_PACKAGE = "com.sun.jersey.config.property.packages";
    private String packageRessourceConfig = "com.sun.jersey.api.core.PackagesResourceConfig";
    private String packageName = "ca.ulaval.glo4002.centralServer.rest";
    private String contextPath = "/";
    private String pathSpec = "/*";

    private Server server;

    public void startServer() throws Exception {
        server = new Server(PORT);
        ServletContextHandler servletContextHandler;
        ServletHolder jerseyServletHolder;

        servletContextHandler = new ServletContextHandler(server, contextPath);
        jerseyServletHolder = createJerseyServletHolder(packageName);
        servletContextHandler.addServlet(jerseyServletHolder, pathSpec);
        server.start();
    }

    public ServletHolder createJerseyServletHolder(String packageName) {
        ServletHolder jerseyServletHolder = new ServletHolder(
                ServletContainer.class);
        jerseyServletHolder.setInitParameter(PARAM_RESSOURCE_CONFIG_CLASS,
                packageRessourceConfig);
        jerseyServletHolder.setInitParameter(PARAM_PACKAGE, packageName);
        return jerseyServletHolder;
    }

    public void stopServer() throws Exception {
        server.stop();
    }
}
