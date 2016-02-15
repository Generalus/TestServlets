package ru.thesn.test_server;


import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.thesn.test_server.servlet.TestPage;

public class Start {
    public static void main(String[] args) throws Exception {
        TestPage testPage = new TestPage();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.addServlet(new ServletHolder(testPage), "/test");

        Server server = new Server(8080);
        HandlerList handlers = new HandlerList( );
        handlers.setHandlers( new Handler[] { context } );
        server.setHandler(handlers);
        server.start();
        server.join();
    }
}