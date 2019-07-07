package edu.pss.ui.ctrl00;

import com.liuvei.common.RandFun;
import com.liuvei.common.ValidateCodeFun;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(UIConst.AREAPATH +"/ValidateCode")
public class ValidateCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        javax.servlet.http.HttpSession session = request.getSession();

        response.setHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires",0 );
        response.setContentType("image/jpeg");


        String strCode = RandFun.rand4Num().toString();

        session.setAttribute(UIConst.BG_VALIDATE_CODE_KEY, strCode);

        java.awt.image.BufferedImage image = ValidateCodeFun.generalImage(strCode);

        javax.servlet.ServletOutputStream outStream = response.getOutputStream();
        javax.imageio.ImageIO.write(image, "jpeg", outStream);
        outStream.close();
        response.flushBuffer();
    }
}
