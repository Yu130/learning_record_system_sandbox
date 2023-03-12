package controllers.history;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.History;
import models.validators.HistoryValidator;
import utils.DBUtil;

/**
 * Servlet implementation class HistoryUpdateServlet
 */
@WebServlet("/history/update")
public class HistoryUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryUpdateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if (_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            History h = em.find(History.class, (Integer) (request.getSession().getAttribute("history_id")));

            Timestamp started_at = new Timestamp(System.currentTimeMillis());
            started_at = Timestamp.valueOf(request.getParameter("started_at"));

            Timestamp finished_at = new Timestamp(System.currentTimeMillis());
            finished_at = Timestamp.valueOf(request.getParameter("finished_at"));

            h.setLearned_date(Date.valueOf(request.getParameter("learned_date")));
            h.setTitle(request.getParameter("title"));
            h.setLearning_time(Integer.parseInt(request.getParameter("learning_time")));
            h.setStarted_at(started_at);
            h.setFinished_at(finished_at);
            h.setContent(request.getParameter("content"));
            List<String> errors = HistoryValidator.validate(h);
            if (errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("history", h);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/history/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("history_id");

                response.sendRedirect(request.getContextPath() + "/history/index");
            }
        }
    }

}