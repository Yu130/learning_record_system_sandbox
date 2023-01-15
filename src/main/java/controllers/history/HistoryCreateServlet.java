package controllers.history;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.History;
import models.User;
import models.validators.HistoryValidator;
import utils.DBUtil;

/**
 * Servlet implementation class HistoryCreateServlet
 */
@WebServlet("/history/create")
public class HistoryCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryCreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String _token = (String) request.getParameter("_token");
        if (_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            History h = new History();

            h.setUser((User) request.getSession().getAttribute("login_user"));

            Date learned_date = new Date(System.currentTimeMillis());
            String ld_str = request.getParameter("learned_date");
            if (ld_str != null && !ld_str.equals("")) {
                learned_date = Date.valueOf(request.getParameter("learned_date"));
            }

            Timestamp started_at = new Timestamp(System.currentTimeMillis());
            started_at = Timestamp.valueOf(request.getParameter("started_at"));

            Timestamp finished_at = new Timestamp(System.currentTimeMillis());
            finished_at = Timestamp.valueOf(request.getParameter("finished_at"));

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
            String ym = dateFormat.format(learned_date);
            System.out.println("ym:" + ym);

            h.setLearned_date(learned_date);
            h.setLearned_year_month(ym);
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

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/history/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(h);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/history/index");
            }
        }
    }

}