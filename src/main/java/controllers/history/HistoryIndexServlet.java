package controllers.history;

import java.io.IOException;
import java.time.YearMonth;
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
 * Servlet implementation class IndexServlet
 */
@WebServlet("/history/index")
public class HistoryIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryIndexServlet() {
        super();
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

        YearMonth year_month = YearMonth.now();
        YearMonth current_ym = (YearMonth) request.getSession().getAttribute("current_ym");
        String ym = null;
        if (current_ym != null) {
            ym = current_ym.toString();
        } else {
            ym = year_month.toString();
        }

        List<History> history = em.createNamedQuery("getMonthlyLearningHistory", History.class)
                .setParameter("ym", ym)
                .setFirstResult(15 * (page - 1))
                .setMaxResults(15)
                .getResultList();

        long history_count = (long) em.createNamedQuery("getMonthlyHistoryCount", Long.class)
                .setParameter("ym", ym)
                .getSingleResult();

        em.close();

        int year;
        int month;
        if (current_ym != null) {
            year = current_ym.getYear();
            month = current_ym.getMonthValue();
        } else {
            year = year_month.getYear();
            month = year_month.getMonthValue();
        }

        request.getSession().setAttribute("current_ym", year_month);
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