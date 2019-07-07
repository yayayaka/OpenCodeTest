package servlets;

import dataaccess.UserDAO;

import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Сервлет авторизации.
public class AuthServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        String login = (String)session.getAttribute("login");
        String password = (String)session.getAttribute("password");
        if (login != null && password != null) {
            try {
                resp.sendRedirect("/game");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            resp.sendRedirect("login.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login != null && password != null) {
            try {
                UserDAO dao = new UserDAO();
                boolean isUserExists = dao.isExists(login);
                if (isUserExists) {
                    session.setAttribute("login", login);
                    session.setAttribute("password", password);
                    resp.sendRedirect("/game");
                    return;
                } else {
                    resp.sendRedirect("userdoesnotexist.jsp");
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            resp.sendRedirect("login.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
