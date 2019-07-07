package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import dataaccess.UserDAO;

// Сервлет регистрации.
public class SignUpServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login != null && password != null) {
            UserDAO dao = new UserDAO();
            boolean wasAddedWithSuccess = dao.set(login, password);
            if (wasAddedWithSuccess) {
                try {
                    resp.sendRedirect("successRegisteredPage.jsp");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    resp.sendRedirect("cantSignUp.jsp");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                resp.sendRedirect("login.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
