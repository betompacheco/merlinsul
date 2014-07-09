package com.merlin.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.hsqldb.Server;

public class Startup implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        String path = context.getRealPath("/");
        com.merlin.data.DataBase.path = path;
        String agencia = context.getInitParameter("agencia");
        String conta = context.getInitParameter("conta");
        String banco = context.getInitParameter("banco");
        String moeda = context.getInitParameter("moeda");
        String carteira = context.getInitParameter("carteira");

        Config.AGENCIA = Integer.parseInt(agencia);
        Config.CONTA = Integer.parseInt(conta);
        Config.BANCO = Integer.parseInt(banco);
        Config.MOEDA = Integer.parseInt(moeda);
        Config.CARTEIRA = carteira;

        Server server = new Server();
        server.setDatabaseName(0, "banco");
        server.setDatabasePath(0, path + "banco/banco");
        server.start();
        server.setSilent(true);
        event.getServletContext().setAttribute("server", server);

    }

    public void contextDestroyed(ServletContextEvent event) {
        Server server = (Server) event.getServletContext().getAttribute("server");
        if (server != null) {
            server.stop();

        }
    }
}
