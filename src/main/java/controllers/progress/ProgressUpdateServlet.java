package controllers.progress;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class ProgressUpdateServlet
 */
@WebServlet("/progress/update")
public class ProgressUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProgressUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        //        User u = (User) request.getSession().getAttribute("user");
        User u = em.find(User.class, request.getSession().getAttribute("user_id"));

        u.setMonthly_target_time(Integer.parseInt(request.getParameter("monthly_target_time")));
        u.setAnnual_target_time(Integer.parseInt(request.getParameter("annual_target_time")));

        em.getTransaction().begin();
        em.getTransaction().commit();
        em.close();

        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("user_id");

        request.getSession().setAttribute("flush", "更新が完了しました。");
        response.sendRedirect(request.getContextPath() + "/progress/index");
    }
}