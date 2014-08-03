/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merlin.util;

import com.merlin.data.managers.RemessaManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemessaDownload extends HttpServlet {

    private static final Logger logger = Logger.getLogger(RemessaDownload.class.getName());
    private static final long serialVersionUID = 1L;

    private static final int BYTES_DOWNLOAD = 1024;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.log(Level.INFO, "Escrevendo o arquivo para download.");

        int ano = Integer.parseInt(req.getParameter("ano"));
        int mes = Integer.parseInt(req.getParameter("mes"));
        boolean isForTest = Boolean.parseBoolean(req.getParameter("isForTest"));

        RemessaManager rm = new RemessaManager();

        resp.setContentType("text/plain");
        resp.setHeader("Content-Disposition", "attachment;filename=remessa.txt");
        ServletContext ctx = getServletContext();
        InputStream is = ctx.getResourceAsStream("/testing.txt");

        int read = 0;
        byte[] bytes = new byte[BYTES_DOWNLOAD];
        OutputStream os = resp.getOutputStream();

        os.write(rm.montaRemessa().getBytes());

//        while ((read = is.read(bytes)) != -1) {
//            os.write(bytes, 0, read);
//        }
        os.flush();
        os.close();
    }

}
