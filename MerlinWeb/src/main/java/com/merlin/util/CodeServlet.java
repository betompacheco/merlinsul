package com.merlin.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CodeServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigo = request.getParameter("codigo");

        BufferedImage image = new BufferedImage(660, 50, BufferedImage.TYPE_INT_RGB); // 123 wide, 123 tall

        Graphics2D graphics2D = image.createGraphics();
        // graphics2D.setBackground(Color.blue);
        graphics2D.fillRect(0, 0, 660, 120);

        BarCodeGenerator bc = new BarCodeGenerator();
        bc.setValue(codigo);
        bc.render(graphics2D, 0, 0, 50);
        graphics2D.dispose();

        response.setContentType("image/jpeg");
        OutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "jpeg", outputStream);
        outputStream.close();

    }
}
