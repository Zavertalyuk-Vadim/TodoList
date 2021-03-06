package servlet;

import model.Task;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/home"})

public class HelloServlet extends HttpServlet {

    private String counterKey = "counter";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);
        List<Task> taskList = new ArrayList<>();

        if (session.isNew())
            System.out.println("Home servlet session not exist");
        else {
            int listLength = (Integer) session.getAttribute(counterKey);
            System.out.println("Home servlet session exist, listLength = " + listLength);
            for (int i = 1; i <= listLength; i++) {
                Task task = (Task) session.getAttribute(i + "");
                taskList.add(task);
                System.out.println(task.getTitle());
            }

        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        req.setAttribute("taskList", taskList);
        dispatcher.forward(req, resp);
    }
}
