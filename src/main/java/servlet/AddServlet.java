package servlet;

import model.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet({"/newTask"})
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String counterKey = "counter";
        int counter = 1;

        if (session.isNew()) {
            System.out.println("Task servlet session not exist");
            session.setAttribute(counterKey, counter);
        } else {
            System.out.println("Task servlet session exist");
            try {
                counter = (Integer) session.getAttribute(counterKey) + 1;
            } catch (NullPointerException e) {
                session.setAttribute(counterKey, 1);
            }
        }
        session.setAttribute("counter", counter);
        session.setAttribute(counter + "", new Task(req.getParameter("name")));
        resp.sendRedirect("/home");
    }
}
