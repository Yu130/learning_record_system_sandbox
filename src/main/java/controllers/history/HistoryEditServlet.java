package controllers.history;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.History;
import utils.DBUtil;

/**
 * Servlet implementation class HistoryEditServlet
 */
@WebServlet("/history/edit")
public class HistoryEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        History h = em.find(History.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("history", h);
        request.setAttribute("_token", request.getSession().getId());
        request.getSession().setAttribute("history_id", h.getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/history/edit.jsp");
        rd.forward(request, response);
    }

}