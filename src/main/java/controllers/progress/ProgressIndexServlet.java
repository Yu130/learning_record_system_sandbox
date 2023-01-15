package controllers.progress;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class ProgressIndexServlet
 */
@WebServlet("/progress/index")
public class ProgressIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProgressIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        User u = (User) request.getSession().getAttribute("login_user");
        request.getSession().setAttribute("user_id", u.getUser_id());
        User login_user = em.find(User.class, request.getSession().getAttribute("user_id"));

        request.setAttribute("user", u);

        YearMonth year_month = YearMonth.now();
        String ym = year_month.toString();
        long m_sum = (long) em.createNamedQuery("getTotalMonthlyLearningTime", Long.class)
                .setParameter("ym", ym)
                .getSingleResult();

        long a_sum = (long) em.createNamedQuery("getTotalLearningTime", Long.class)
                .getSingleResult();

        em.close();

        double monthly_sum = (double) m_sum / 60;
        String monthly_sum_str = String.format("%.1f", monthly_sum);
        double monthly_sum_h = Double.parseDouble(monthly_sum_str);

        double annual_sum = (double) a_sum / 60;
        String annual_sum_str = String.format("%.1f", annual_sum);
        double annual_sum_h = Double.parseDouble(annual_sum_str);

        LocalDate ld = LocalDate.now();
        int year = ld.getYear();
        int month = ld.getMonthValue();
        String ym_j = year + "年" + month + "月";

        int monthly_target_time = login_user.getMonthly_target_time();
        double monthly_pr = ((double) monthly_sum_h / monthly_target_time) * 100;
        String monthly_pr_str = String.format("%.1f", monthly_pr);
        double monthly_progress_rate = Double.parseDouble(monthly_pr_str);

        int annual_target_time = login_user.getAnnual_target_time();
        double annual_pr = ((double) annual_sum_h / annual_target_time) * 100;
        String annual_pr_str = String.format("%.1f", annual_pr);
        double annual_progress_rate = Double.parseDouble(annual_pr_str);

        request.setAttribute("monthly_target_time", monthly_target_time);
        request.setAttribute("annual_target_time", annual_target_time);
        request.setAttribute("monthly_sum_h", monthly_sum_h);
        request.setAttribute("annual_sum_h", annual_sum_h);
        request.setAttribute("ym_j", ym_j);
        request.setAttribute("year", year);
        request.setAttribute("monthly_progress_rate", monthly_progress_rate);
        request.setAttribute("annual_progress_rate", annual_progress_rate);
        if (request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/progress/index.jsp");
        rd.forward(request, response);
    }
}