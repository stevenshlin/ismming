package com.ismming.api.config;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;

public class JettyConfig implements JettyServerCustomizer {

    private String maxThreads;
    private String minThreads;
    private String idleTimeout;

    public JettyConfig(String maxThreads, String minThreads, String idleTimeout) {
        this.maxThreads = maxThreads;
        this.minThreads = minThreads;
        this.idleTimeout = idleTimeout;
    }

    @Override
    public void customize(final Server server) {
        final QueuedThreadPool threadPool = server.getBean(QueuedThreadPool.class);
        threadPool.setMaxThreads(Integer.valueOf(maxThreads));
        threadPool.setMinThreads(Integer.valueOf(minThreads));
        threadPool.setIdleTimeout(Integer.valueOf(idleTimeout));
    }

}