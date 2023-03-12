package controllers.history;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.History;

/**
 * Servlet implementation class HistoryNewServlet
 */
@WebServlet("/history/new")
public class HistoryNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());

        History h = new History();

        h.setLearned_date(new Date(System.currentTimeMillis()));
        request.setAttribute("history", h);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/history/new.jsp");
        rd.forward(request, response);
    }

}