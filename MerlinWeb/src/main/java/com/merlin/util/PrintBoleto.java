package com.merlin.util;

import com.merlin.data.DataBase;
import com.merlin.data.dto.CobrancaDTO;
import com.merlin.data.dto.MensagemDTO;
import com.merlin.data.managers.CobrancaManager;
import com.merlin.data.managers.MensagemManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

public class PrintBoleto extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ano = request.getParameter("ano");
        String mes = request.getParameter("mes");
        String mensagem = request.getParameter("mensagem");
        String apartamento = request.getParameter("apartamento");
        CobrancaManager cobrancaManager = new CobrancaManager();
        MensagemManager mensagemManager = new MensagemManager();
        int a = Integer.parseInt(ano);
        int m = Integer.parseInt(mes);
        int msg = Integer.parseInt(mensagem);
        int ap = -1;
        try {
            ap = Integer.parseInt(apartamento);
        } catch (NumberFormatException e) {
        }
        List cobrancas = cobrancaManager.select(a, m, ap);

        List detalhe = new ArrayList();
        for (int i = 0; i < cobrancas.size(); i++) {
            detalhe.addAll(((CobrancaDTO) cobrancas.get(i)).getDescricao());
        }

        MensagemDTO mensagemDTO = mensagemManager.select(msg);
        JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(cobrancas);
        JRBeanCollectionDataSource detalhedatasource = new JRBeanCollectionDataSource(detalhe);
        ServletContext context = this.getServletConfig().getServletContext();

        String boleto = DataBase.path + "/WEB-INF/rpt/boleto.jrxml";
        String detalherpt = DataBase.path + "/WEB-INF/rpt/detalhe.jrxml";
        System.out.println("Caminho: " + boleto);
        String server = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath(); // "http://localhost:8080/MerlinWeb";

        try {
            JasperReport relatorio = JasperCompileManager.compileReport(boleto);
            JasperReport jrdetalhe = JasperCompileManager.compileReport(detalherpt);
            Map parametros = new HashMap();
            parametros.put("logo", server + "/imagens/logobradesco.png");
            parametros.put("servletCod", server + "/codeservlet");
            parametros.put("agencia", "" + Config.AGENCIA);
            parametros.put("codigocedente", "" + Config.CONTA);
            parametros.put("msg", mensagemDTO.getTextoMensagem());
            parametros.put("detalhe", jrdetalhe);
            parametros.put("detalheDataSource", detalhedatasource);

            byte[] bytes = JasperRunManager.runReportToPdf(relatorio, parametros, datasource);
            FileOutputStream fo = new FileOutputStream(new File(System.getProperty("user.home").concat(System.getProperty("file.separator")).concat("boleto.pdf")));
            fo.write(bytes);
            fo.close();

            // response.setHeader("Pragma","no-cache");
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline;filename=boleto.pdf");
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
