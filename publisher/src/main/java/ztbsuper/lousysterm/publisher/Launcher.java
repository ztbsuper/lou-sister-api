package ztbsuper.lousysterm.publisher;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;


public class Launcher {
    public static void main(String[] args) throws Exception {
        String webapp = "./publisher/src/main/webapp";

        Server server = new Server(8888);

        WebAppContext context = new WebAppContext();
        context.setDescriptor(webapp + "/WEB-INF/web.xml");

        context.setResourceBase(webapp);
        context.setContextPath("/");
        context.setClassLoader(
                Thread.currentThread().getContextClassLoader());

        server.setHandler(context);

        server.start();
        server.join();
    }
}
