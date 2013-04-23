package ca.ulaval.glo4002.centralServer.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.sun.jersey.spi.container.servlet.ServletContainer;

public class CentralServer {

    private static final int PORT = 9001;
    private static final String PARAM_RESSOURCE_CONFIG_CLASS = "com.sun.jersey.config.property.resourceConfigClass";
    private static final String PARAM_PACKAGE = "com.sun.jersey.config.property.packages";
    private static final String PACKAGE_RESSOURCE_CONFIG = "com.sun.jersey.api.core.PackagesResourceConfig";
    private static final String PACKAGE_NAME = "ca.ulaval.glo4002.centralServer.core.rest";
    private static final String CONTEXT_PATH = "/";
    private static final String PATH_SPEC = "/*";

    private Server server;

    public void startServer() throws Exception {
        server = new Server(PORT);
        ServletContextHandler servletContextHandler = new ServletContextHandler(server, CONTEXT_PATH);
        ServletHolder jerseyServletHolder = createJerseyServletHolder(PACKAGE_NAME);

        servletContextHandler.addServlet(jerseyServletHolder, PATH_SPEC);
        server.start();
    }

    public ServletHolder createJerseyServletHolder(String packageName) {
        ServletHolder jerseyServletHolder = new ServletHolder(ServletContainer.class);
        jerseyServletHolder.setInitParameter(PARAM_RESSOURCE_CONFIG_CLASS, PACKAGE_RESSOURCE_CONFIG);
        jerseyServletHolder.setInitParameter(PARAM_PACKAGE, packageName);
        return jerseyServletHolder;
    }

    public void stopServer() throws Exception {
        server.stop();
    }

}
