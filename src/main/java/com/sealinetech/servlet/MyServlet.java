package com.sealinetech.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Created by zpj-pc on 2017-08-19.
 */
public class MyServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        resp.getWriter().write("<H1>Hello word</H1><br><br>");
        ServletConfig servletConfig = getServletConfig();
        ServletContext servletContext = getServletContext();
        Object obj = servletContext.getAttribute("global");
        Integer count=1;
        if(obj!=null){
            count=Integer.parseInt(obj.toString())+1;
        }
        servletContext.setAttribute("global",count);
        HttpSession httpSession = req.getSession();
        Object local = httpSession.getAttribute("local");
        Integer localCount=1;
        if(local!=null){
            localCount=Integer.parseInt(local.toString())+1;
        }
        httpSession.setAttribute("local",localCount);

        Cookie[] cookies = req.getCookies();
        Integer cookieCount=1;
        if(cookies!=null){
            for (Cookie cookie : cookies){
                if(cookie.getName().equals("cookie")){
                    cookieCount=Integer.parseInt(cookie.getValue())+1;
                }
            }
        }
        resp.addCookie(new Cookie("cookie",cookieCount.toString()));

        resp.getWriter().write("<H1>application:"+count+"</H1><br><br>");
        resp.getWriter().write("<H1>session:"+localCount+"</H1><br><br>");
        String globalOne=servletContext.getInitParameter("global-one");
        resp.getWriter().write("<H1>"+globalOne+"</H1><br><br>");
        String localOne=servletConfig.getInitParameter("local-one");
        resp.getWriter().write("<H1>"+localOne+"</H1><br><br>");
        String url=getServletContext().getContextPath()+"WEB-INF/onepage.jsp";
        System.out.println(url);
        //req.getRequestDispatcher(url).forward(req, resp);
        */
        String servletPath=req.getServletPath();
        String methodName=servletPath.substring(1);
        methodName=methodName.substring(0, methodName.indexOf('.'));

        try {
            Method method= getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this, req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void login(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, Exception{
        String name=request.getParameter("userName");
        name = new String(name.getBytes("ISO-8859-1"), "utf-8");
        String password=request.getParameter("password");
        String checkcode=request.getParameter("check").toLowerCase();
        System.out.println(checkcode);
        HttpSession session =request.getSession();
        String verificationCode = (String)session.getAttribute("verificationCode");
        System.out.println(verificationCode);
        if(verificationCode==null || !checkcode.equals(verificationCode.toLowerCase())){
            request.setAttribute("msg", "验证码错误，请重新录入！");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        System.out.println(password);
        String passwordmd= getPasswordMD(password);
        System.out.println(passwordmd);

        Integer count = 1;
        if(count<=0){
            request.setAttribute("msg", "用户名或密码错误，请重新录入！");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        session.setAttribute("userName", name);
        //response.sendRedirect("index.jsp");
        //request.setAttribute("userName", name);
        request.getRequestDispatcher(getServletContext().getContextPath()+"WEB-INF/onepage.jsp").forward(request, response);
    }
    /**
     * 获取MD5密码
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    private String getPasswordMD(String password) throws NoSuchAlgorithmException {
        // 生成一个MD5加密计算摘要
        MessageDigest md = MessageDigest.getInstance("MD5");
        // 计算md5函数
        md.update(password.getBytes());
        // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
        return new BigInteger(1, md.digest()).toString(16);
    }

    private void image(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        BufferedImage bfi = new BufferedImage(80,25,BufferedImage.TYPE_INT_RGB);
        Graphics g = bfi.getGraphics();
        g.fillRect(0, 0, 80, 25);

        //验证码字符范围
        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        Random r = new Random();
        int index;
        StringBuffer sb = new StringBuffer(); //保存字符串
        for(int i=0; i<4; i++){
            index = r.nextInt(ch.length);
            g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
            Font font = new Font("宋体", 30, 20);
            g.setFont(font);
            g.drawString(ch[index]+"", (i*20)+2, 23);
            sb.append(ch[index]);
        }

        // 添加噪点
        int area = (int) (0.02 * 80 * 25);
        for(int i=0; i<area; ++i){
            int x = (int)(Math.random() * 80);
            int y = (int)(Math.random() * 25);
            bfi.setRGB(x, y, (int)(Math.random() * 255));
        }

        //设置验证码中的干扰线
        for (int i = 0; i < 3; i++) {
            //随机获取干扰线的起点和终点
            int xstart = (int)(Math.random() * 80);
            int ystart = (int)(Math.random() * 25);
            int xend = (int)(Math.random() * 80);
            int yend = (int)(Math.random() * 25);
            g.setColor(interLine(1, 255));
            g.drawLine(xstart, ystart, xend, yend);
        }
        HttpSession session = request.getSession();  //保存到session
        session.setAttribute("verificationCode", sb.toString());
        ImageIO.write(bfi, "JPG", response.getOutputStream());  //写到输出流
    }

    private static Color interLine(int Low, int High){
        if(Low > 255)
            Low = 255;
        if(High > 255)
            High = 255;
        if(Low < 0)
            Low = 0;
        if(High < 0)
            High = 0;
        int interval = High - Low;
        int r = Low + (int)(Math.random() * interval);
        int g = Low + (int)(Math.random() * interval);
        int b = Low + (int)(Math.random() * interval);
        return new Color(r, g, b);
    }
}
