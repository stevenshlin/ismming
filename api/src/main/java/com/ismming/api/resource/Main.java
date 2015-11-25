package com.ismming.api.resource;

import com.ismming.api.filter.NoCacheControlFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.glassfish.jersey.servlet.ServletContainer;

public class Main {

    private static final int DEFAULT_PORT = 8080;

    private int serverPort;

    public Main(int serverPort) throws Exception {
        this.serverPort = serverPort;
        Server server = configureServer();
        server.start();
        server.join();
    }

    private Server configureServer() {
        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.register(RequestContextFilter.class);
        resourceConfig.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        resourceConfig.register(JacksonFeature.class);
//        resourceConfig.register(GenericExceptionMapper.class);
        resourceConfig.register(NoCacheControlFilter.class);
        resourceConfig.register(CategoryResource.class);
        ServletContainer servletContainer = new ServletContainer(resourceConfig);
        ServletHolder servletHolder = new ServletHolder(servletContainer);
        Server server = new Server(serverPort);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.addServlet(servletHolder, "/api/*");
        server.setHandler(context);
        return server;
    }

    public static void main(String[] args) throws Exception {
        int serverPort = DEFAULT_PORT;
        if (args.length >= 1) {
            try {
                serverPort = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        new Main(serverPort);
    }
}
