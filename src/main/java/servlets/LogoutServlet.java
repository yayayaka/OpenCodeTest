package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse responce) {
        HttpSession session = request.getSession();
        session.removeAttribute("login");
        session.removeAttribute("password");
        session.removeAttribute("hiddenNumber");
        session.removeAttribute("moves");
        session.removeAttribute("results");
        session.removeAttribute("isGuessed");
        try {
            responce.sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
