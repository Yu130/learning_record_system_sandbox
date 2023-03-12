package controllers.history;

import java.io.IOException;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;

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
 * Servlet implementation class HistoryNextMonthIndexServlet
 */
@WebServlet("/history/nextMonthIndex")
public class HistoryNextMonthIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryNextMonthIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
        }

        YearMonth current_ym = (YearMonth) request.getSession().getAttribute("current_ym");
        YearMonth next_ym = current_ym.plus(1, ChronoUnit.MONTHS);
        String ym = next_ym.toString();

        List<History> history = em.createNamedQuery("getMonthlyLearningHistory", History.class)
                .setParameter("ym", ym)
                .setFirstResult(15 * (page - 1))
                .setMaxResults(15)
                .getResultList();

        long history_count = (long) em.createNamedQuery("getMonthlyHistoryCount", Long.class)
                .setParameter("ym", ym)
                .getSingleResult();

        em.close();

        int year = next_ym.getYear();
        int month = next_ym.getMonthValue();

        request.getSession().setAttribute("current_ym", next_ym);
        request.setAttribute("year", year);
        request.setAttribute("month", month);
        request.setAttribute("history", history);
        request.setAttribute("history_count", history_count);
        request.setAttribute("page", page);
        if (request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/history/index.jsp");
        rd.forward(request, response);
    }
}