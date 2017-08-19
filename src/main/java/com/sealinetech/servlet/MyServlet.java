package com.sealinetech.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        resp.getWriter().write("<H1>Hello word</H1><br><br>");
        ServletConfig servletConfig = getServletConfig();
        ServletContext servletContext = getServletContext();
        Object obj = servletContext.getAttribute("global");
        Integer count=1;
        if(obj!=null){
            count=Integer.parseInt(obj.toString())+1;
        }

        servletContext.setAttribute("global",count);
        resp.getWriter().write("<H1>"+count+"</H1><br><br>");
        String globalOne=servletContext.getInitParameter("global-one");
        resp.getWriter().write("<H1>"+globalOne+"</H1><br><br>");
        String localOne=servletConfig.getInitParameter("local-one");
        resp.getWriter().write("<H1>"+localOne+"</H1><br><br>");
        String url=getServletContext().getContextPath()+"WEB-INF/onepage.jsp";
        System.out.println(url);
        req.getRequestDispatcher(url).forward(req, resp);
    }
}
