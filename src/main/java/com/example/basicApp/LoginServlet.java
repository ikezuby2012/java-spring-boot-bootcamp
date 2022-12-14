package com.example.basicApp;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        PrintWriter out = response.getWriter();
//        String name = request.getParameter("name");
//        System.out.println(name);
//        out.println("<html>");
//        out.println("");
//        out.println("<head>");
//        out.println("<title>hello my new page</title>");
//        out.println("</head>");
//        out.println("</html>");
    }
}
