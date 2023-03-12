package controllers.Measuring;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.YearMonth;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.History;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class FinishMeasuringServlet
 */
@WebServlet("/measuring/finish")
public class FinishMeasuringServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinishMeasuringServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String _token = (String) request.getParameter("_token");
        System.out.println("token:" + _token);
        if (_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            History h = new History();

            h.setUser((User) request.getSession().getAttribute("login_user"));

            Timestamp started_at = (Timestamp) request.getSession().getAttribute("started_at");
            SimpleDateFormat srt_hh = new SimpleDateFormat("HH");
            SimpleDateFormat srt_mm = new SimpleDateFormat("mm");
            String srt_hh_s = srt_hh.format(started_at);
            String srt_mm_s = srt_mm.format(started_at);
            int srt_hh_i = Integer.parseInt(srt_hh_s);
            int start_hh = srt_hh_i * 60;
            int start_mm = Integer.parseInt(srt_mm_s);
            int srt = start_hh + start_mm;

            Timestamp finished_at = new Timestamp(System.currentTimeMillis());
            SimpleDateFormat fin_hh = new SimpleDateFormat("HH");
            SimpleDateFormat fin_mm = new SimpleDateFormat("mm");
            String fin_hh_s = fin_hh.format(finished_at);
            String fin_mm_s = fin_mm.format(finished_at);
            int fin_hh_i = Integer.parseInt(fin_hh_s);
            int finish_hh = fin_hh_i * 60;
            int finish_mm = Integer.parseInt(fin_mm_s);
            int fin = finish_hh + finish_mm;

            int learning_time = fin - srt;
            if (learning_time < 0) {
                learning_time += 1440;
            }

            Date learned_date = (Date) (request.getSession().getAttribute("learned_date"));

            YearMonth year_month = YearMonth.now();
            String ym = year_month.toString();

            String title = (String) (request.getSession().getAttribute("title"));
            String content = (String) (request.getSession().getAttribute("content"));

            h.setLearned_date(learned_date);
            h.setLearned_year_month(ym);
            h.setTitle(title);
            h.setLearning_time(learning_time);
            h.setStarted_at(started_at);
            h.setFinished_at(finished_at);
            h.setContent(content);

            em.getTransaction().begin();
            em.persist(h);
            em.getTransaction().commit();
            em.close();
            request.getSession().setAttribute("flush", "登録が完了しました。");

            request.getSession().removeAttribute("learned_date");
            request.getSession().removeAttribute("title");
            request.getSession().removeAttribute("started_at");
            request.getSession().removeAttribute("content");
            request.getSession().removeAttribute("h");

            String r_title = (String) request.getSession().getAttribute("title");
            System.out.println("title:" + r_title);
            String r_content = (String) request.getSession().getAttribute("content");
            System.out.println("content:" + r_content);

            response.sendRedirect(request.getContextPath() + "/history/index");
        }
        //        }
    }
}