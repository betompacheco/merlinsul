package com.merlin.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.merlin.data.DataBase;

public class PrintReport extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List rptDados = (List) request.getSession().getAttribute("relatorio");
        String rptname = DataBase.path + "/WEB-INF/rpt/" + rptDados.get(0);
        List lista = (List) rptDados.get(1);
        Map parametros = (Map) rptDados.get(2);
        JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(lista);
        ServletContext context = this.getServletConfig().getServletContext();

        String server = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath(); // "http://localhost:8080/MerlinWeb";

        try {
            JasperReport relatorio = JasperCompileManager.compileReport(rptname);

            byte[] bytes = JasperRunManager.runReportToPdf(relatorio, parametros, datasource);

            // response.setHeader("Pragma","no-cache");
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline;filename=relatorio.pdf");
            response.setHeader("Cache-Control", "must-revalidate, max_age=360");
            // response.setHeader("Expires", "160");

            response.setContentLength(bytes.length);
            ServletOutputStream ouputStream = response.getOutputStream();
            ouputStream.write(bytes, 0, bytes.length);
            // response.flushBuffer();
            ouputStream.flush();
            ouputStream.close();

        } catch (JRException e) {
            e.printStackTrace();
        }

    }
}
