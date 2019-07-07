package servlets;

import dataaccess.UserDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        int min = 0;
        int max = 9999;
        try {
            if (session.getAttribute("login") == null) {
                resp.sendRedirect("login.jsp");
                return;
            } else {
                Random random = new Random();
                session.setAttribute("hiddenNumber", random.nextInt(max - min + 1) + min);
                session.setAttribute("moves", new ArrayList());
                session.setAttribute("results", new ArrayList());
                session.setAttribute("isGuessed", false);
                resp.sendRedirect("game.jsp");
            }
        } catch (IOException e) {

        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try{
            HttpSession session = req.getSession();
            if (session.getAttribute("login") == null) {
                resp.sendRedirect("login.jsp");
                return;
            } else {
                int move = Integer.valueOf(req.getParameter("move"));
                ArrayList moves = (ArrayList) session.getAttribute("moves");
                moves.add(move);
                ArrayList results = (ArrayList) session.getAttribute("results");
                int hiddenNumber = (int)session.getAttribute("hiddenNumber");
                boolean isGuessed = (boolean)session.getAttribute("isGuessed");
                int lastMove = (int)moves.get(moves.size() - 1);
                if (lastMove == hiddenNumber) {
                    isGuessed = true;
                }
                if (isGuessed) {
                    UserDAO dao = new UserDAO();
                    dao.editStats((String) session.getAttribute("login"), 1, moves.size());
                }
                ArrayList regsLastNumber = getRegistersOfNumber(lastMove);
                ArrayList regsHiddenNumber = getRegistersOfNumber(hiddenNumber);
                int bull = getBulls(regsLastNumber, regsHiddenNumber);
                int cow = getCows(regsLastNumber, regsHiddenNumber);
                String bullCowResult = String.format("%dБ%dК", bull, cow);
                results.add(bullCowResult);
                session.setAttribute("results", results);
                session.setAttribute("isGuessed", isGuessed);
                resp.sendRedirect("/game.jsp");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList getRegistersOfNumber(int num) {
        ArrayList registers = new ArrayList();
        for (int regPointer = 0; regPointer < 4; regPointer++) {
            registers.add(num % 10);
            num /= 10;
            if (num == 0)
                break;
        }
        return registers;
    }

    private int getBulls(ArrayList regsLastNumber, ArrayList regsHiddenNumber) {
        int bull = 0;
        for (int regPointer = 0; regPointer < regsLastNumber.size(); regPointer++) {
            if (regPointer >= regsHiddenNumber.size())
                break;
            if (regsLastNumber.get(regPointer) == regsHiddenNumber.get(regPointer)) {
                bull++;
            }
        }
        return bull;
    }

    private int getCows(ArrayList regsLastNumber, ArrayList regsHiddenNumber) {
        int cow = 0;
        for (int hiddenNumberPointer = 0; hiddenNumberPointer < regsHiddenNumber.size(); hiddenNumberPointer++) {
            for (int lastNumberPointer = 0; lastNumberPointer < regsLastNumber.size(); lastNumberPointer++) {
                if (regsLastNumber.get(lastNumberPointer) == regsHiddenNumber.get(hiddenNumberPointer)) {
                    cow++;
                    break;
                }
            }
        }
        return cow;
    }
}
